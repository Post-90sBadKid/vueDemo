package com.wry.vueservice.dto;

import lombok.Data;

@Data
public class Result {
    //成功标识
    private boolean success;
    //操作结果代码
    private Integer code;
    //操作消息
    private Object message;



}
