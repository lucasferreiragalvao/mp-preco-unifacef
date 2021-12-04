package com.unifacef.grupo1.apiprecounifacef.controllers;

import com.unifacef.grupo1.apiprecounifacef.dtos.request.RequestPrecoDto;
import com.unifacef.grupo1.apiprecounifacef.dtos.response.ResponsePrecoDto;
import com.unifacef.grupo1.apiprecounifacef.entities.Preco;
import com.unifacef.grupo1.apiprecounifacef.mappers.PrecoMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Validated
@RestController
@RequestMapping(path = "${v1API}/prices")
public class PrecoController {

    private List<Preco> precos = new ArrayList<>();

    @PostMapping
    @ApiOperation("Cadastra preço no porduto informado")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno"),
    })
    public ResponsePrecoDto cadastrarPreco(@ApiParam(name = "codeseller", example = "1", required = true) @PathVariable("codeseller") Long idSeller,
                                           @ApiParam(name = "codeproduct", example = "1", required = true) @PathVariable("codeproduct") Long idProduct,
                                           @Valid @RequestBody RequestPrecoDto requestPreco){

        if(precos.stream().anyMatch(i -> i.getSellerId() == idSeller && i.getProductId() == idProduct)){
            throw new RuntimeException("Já existe um produto cadastrado para esse fornecedor");
        }

        Long index = precos.size() > 0 ? (precos.size()+1) : 1L;
        Preco preco = PrecoMapper.toDomain(requestPreco);
        preco.setId(index);
        preco.setSellerId(idSeller);
        preco.setProductId(idProduct);
        precos.add(preco);
        return PrecoMapper.fromDomain(preco);
    }

    @PutMapping
    @ApiOperation("Altera o preço de um produto já cadastrado")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno"),
    })
    public ResponsePrecoDto alteraPreco(@ApiParam(name = "codeseller", example = "1", required = true) @PathVariable("codeseller") Long idSeller,
                             @ApiParam(name = "codeproduct", example = "1", required = true) @PathVariable("codeproduct") Long idProduct,
                             @Valid @RequestBody RequestPrecoDto requestPreco) {

        Long idPreco = null;
        for (Preco item : precos) {
            if (item.getSellerId() == idSeller && item.getProductId() == idProduct) {
                item.setFrom(requestPreco.getFrom());
                item.setTo(requestPreco.getTo());
                idPreco = item.getId();
                break;
            }
        }

        if ( idPreco == null ) {
            throw new RuntimeException("Produto ou fornecedor não encontrado");
        }

        Preco preco = PrecoMapper.toDomain(requestPreco);
        preco.setId( idPreco );

        return PrecoMapper.fromDomain(preco);
    }

    @GetMapping
    @ApiOperation("Consulta um preço")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Recurso não encontrato"),
            @ApiResponse(code = 500, message = "Erro interno"),
    })
    public ResponsePrecoDto consultaPreco(@PathVariable("codeseller") Long idSeller, @PathVariable("codeproduct") Long idProduct) {

        Preco preco = null;

        for (Preco item : precos) {
            if (item.getSellerId() == idSeller && item.getProductId() == idProduct) {
                preco = item;
                break;
            }
        }

        if ( preco == null ) {
            throw new RuntimeException("Produto ou fornecedor não encontrado");
        }

        return PrecoMapper.fromDomain(preco);
    }

}
