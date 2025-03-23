# IM
An instant messaging project in Java version.

## TODO
- [x] Authentication
- [x] GateWay
- [x] Websocket
- [x] Message
- [ ] Red envelope 

**optimize**
- [ ] Add return information to the frontend
- [ ] Interceptor: add check-logic in JwtHandler 
- [ ] sendSms throws exception
- [ ] check for other exception throwing logic 

**issue**
- [x] The verification code sending function cannot save the verification code to Redis
- [x] netty-userId can not be stored by redis
- [ ] send text-message to friend secceed, but the body of the response is empty