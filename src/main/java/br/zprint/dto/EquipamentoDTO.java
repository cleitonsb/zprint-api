package br.zprint.dto;

import br.zprint.model.Equipamento;
import java.io.Serializable;

public class EquipamentoDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long id;
    private String marca;
    private String modelo;
    private String serie;
    private String descricao;

    public EquipamentoDTO(Equipamento equipamento) {
        this.id = equipamento.getId();
        this.marca = equipamento.getMarca();
        this.modelo = equipamento.getModelo();
        this.serie = equipamento.getSerie();
        this.descricao = equipamento.getDescricao();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
