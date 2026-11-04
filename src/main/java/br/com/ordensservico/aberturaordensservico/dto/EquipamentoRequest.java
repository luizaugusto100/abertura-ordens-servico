package br.com.ordensservico.aberturaordensservico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EquipamentoRequest {

    @NotBlank (message = "O nome do equipamento é obrigatório")
    @NotNull (message = "O nome do equipamento não pode ser nulo")
    private String nome;

    @NotBlank (message = "O número de patrimônio é obrigatório")
    @NotNull (message = "O número de patrimônio não pode ser nulo")
    private String numeroPatrimonio;

    @NotNull (message = "O setor não pode ser nulo")
    private Integer setorId;

    public EquipamentoRequest() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNumeroPatrimonio() {
        return numeroPatrimonio;
    }

    public void setNumeroPatrimonio(String numeroPatrimonio) {
        this.numeroPatrimonio = numeroPatrimonio;
    }

    public Integer getSetorId() {
        return setorId;
    }

}
