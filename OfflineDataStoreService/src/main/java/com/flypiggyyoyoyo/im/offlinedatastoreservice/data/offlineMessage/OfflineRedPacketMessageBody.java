package com.flypiggyyoyoyo.im.offlinedatastoreservice.data.offlineMessage;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class OfflineRedPacketMessageBody extends OfflineMessageBody {
    private String redPacketWrapperText;
}
