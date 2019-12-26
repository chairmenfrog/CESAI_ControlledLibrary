package com.example.cesai.bean;

import lombok.*;

@AllArgsConstructor
@Data
@Builder(toBuilder = true)
public class ResultInfo {
    private String code;
    private String msg;
    private Object data;

    public static  ResultInfo success(){
        return ResultInfo.builder().code("0").msg("ok").build();
    }

    public static  ResultInfo failure(){
        return ResultInfo.builder().code("-1").msg("failure").build();
    }

}
