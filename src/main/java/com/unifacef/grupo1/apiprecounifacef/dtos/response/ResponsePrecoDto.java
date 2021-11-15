package com.unifacef.grupo1.apiprecounifacef.dtos.response;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponsePrecoDto {

    private Long id;
    private Double from;
    private Double to;
}
