package com.kosi.vo;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResultVO<T> {

    private Integer returnCode;
    private String msg;
    private T data;
}
