package com.flypiggyyoyoyo.im.realtimecommunicationservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageDTO {

    private Integer type;

    private Object data;
}
