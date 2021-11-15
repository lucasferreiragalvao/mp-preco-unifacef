package com.unifacef.grupo1.apiprecounifacef.controllers;

import com.unifacef.grupo1.apiprecounifacef.dtos.request.RequestPrecoDto;
import com.unifacef.grupo1.apiprecounifacef.dtos.response.ResponsePrecoDto;
import com.unifacef.grupo1.apiprecounifacef.entities.Preco;
import com.unifacef.grupo1.apiprecounifacef.mappers.PrecoMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Validated
@RestController
public class PrecoController {

    private List<Preco> precos = new ArrayList<>();

    @PostMapping
    @ApiOperation("Cadastra preço no porduto informado")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno"),
    })
    public ResponsePrecoDto cadastrarPreco(@Valid @RequestBody RequestPrecoDto requestPreco){
        Long index = precos.size() > 0 ? (precos.size()+1) : 1L;
        Preco preco = PrecoMapper.toDomain(requestPreco);
        preco.setId(index);
        precos.add(preco);
        return PrecoMapper.fromDomain(preco);
    }
}
