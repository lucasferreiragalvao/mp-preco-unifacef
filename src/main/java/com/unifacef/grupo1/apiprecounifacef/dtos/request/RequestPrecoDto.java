package com.unifacef.grupo1.apiprecounifacef.dtos.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class RequestPrecoDto {

    private Double from;

    @NotNull
    @Min(1)
    private Double to;
}
