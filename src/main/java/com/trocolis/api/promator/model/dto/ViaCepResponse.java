package com.trocolis.api.promator.model.dto;

import com.trocolis.api.promator.model.domain.BrazilUF;
import lombok.Data;

@Data
public class ViaCepResponse {
    // Fields returned by the API ViaCep
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private BrazilUF uf;
    private Long ibge;
    private String gia;
    private Integer ddd;
    private String siafi;

}
