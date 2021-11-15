package com.unifacef.grupo1.apiprecounifacef.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Preco {

    private Long id;
    private Long sellerId;
    private Long productId;
    private Double from;
    private Double to;
}
