package br.com.ordensservico.aberturaordensservico.dto;

import jakarta.validation.constraints.NotBlank;

public class EncerramentoOrdemServicoRequest {

    @NotBlank (message = "Informe a solução utilizada para encerrar a ordem de serviço")
    private String solucao;

    public EncerramentoOrdemServicoRequest() {

    }

    public String getSolucao() {
        return solucao;
    }

    public void setSolucao(String solucao) {
        this.solucao = solucao;
    }
    
}
