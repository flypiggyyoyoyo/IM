package com.flypiggyyoyoyo.im.realtimecommunicationservice.websocket;

import com.alibaba.cloud.nacos.NacosServiceManager;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.NettyRuntime;
import io.netty.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j

// 标记为Spring配置类，用于配置Netty服务器
@Configuration
public class NettyServer {

    // 从配置文件中读取Netty服务器监听的端口号
    @Value("${netty.port}")
    private int port;

    @Value("${netty.name}")
    private String serverName;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private NacosServiceManager nacosServiceManager;

    //服务发现客户端
    private final DiscoveryClient discoveryClient;

    // 用于处理客户端连接请求的EventLoopGroup，通常使用一个线程
    private EventLoopGroup bossGroup = new NioEventLoopGroup(1);

    // 用于处理已连接客户端的读写操作的EventLoopGroup，线程数为可用处理器数量
    private EventLoopGroup workerGroup = new NioEventLoopGroup(NettyRuntime.availableProcessors());

    public NettyServer(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @PostConstruct
    public void start() throws InterruptedException, UnknownHostException, NacosException {
        run();
        NamingService namingService = nacosServiceManager.getNamingService();
        namingService.registerInstance(serverName, InetAddress.getLocalHost().getHostAddress(),this.port);
    }

    // 启动Netty服务器的核心方法
    public void run() throws InterruptedException {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,  128)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new IdleStateHandler(5 * 60, 0, 0));
                        pipeline.addLast(new HttpServerCodec());
                        pipeline.addLast(new ChunkedWriteHandler());
                        pipeline.addLast(new HttpObjectAggregator(8192));
                        pipeline.addLast(new WebSocketTokenAuthHeader());
                        pipeline.addLast(new WebSocketServerProtocolHandler("/api/v1/netty"));
                        pipeline.addLast(new MessageInboundHandler(redisTemplate));
                    }
                });

        // 绑定指定端口并启动服务器，同步等待绑定完成
        serverBootstrap.bind(port).sync();
    }

    @PreDestroy
    //当程序关闭的时候，会调用这个方法
    public void destroy(){
        Future<?> future = bossGroup.shutdownGracefully();
        Future<?> future1 = workerGroup.shutdownGracefully();
        future.syncUninterruptibly();
        future1.syncUninterruptibly();
        log.info("关闭 ws server 成功");
    }
}