package com.unifacef.grupo1.apiprecounifacef.mappers;

import com.unifacef.grupo1.apiprecounifacef.dtos.request.RequestPrecoDto;
import com.unifacef.grupo1.apiprecounifacef.dtos.response.ResponsePrecoDto;
import com.unifacef.grupo1.apiprecounifacef.entities.Preco;

public class PrecoMapper {

    public static Preco toDomain(RequestPrecoDto dto){
        Preco preco = new Preco();
        preco.setFrom(dto.getFrom());
        preco.setTo(dto.getTo());
        return preco;
    }

    public static ResponsePrecoDto fromDomain(Preco preco){
        ResponsePrecoDto response = new ResponsePrecoDto();
        response.setId(preco.getId());
        response.setFrom(preco.getFrom());
        response.setTo(preco.getTo());
        return response;
    }
}
