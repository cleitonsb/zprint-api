package br.zprint.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "equipamentos")
public class Equipamento implements Serializable {

    private static final long serialVersionUID = -3095059815606580245L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String marca;
    private String modelo;
    private String serie;
    private String descricao;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;

    @Column(columnDefinition = "boolean default true")
    private Boolean situacao = true;

    public Equipamento() {}

    public Equipamento(Long id, String marca, String modelo, String serie, String descricao, Pessoa pessoa, Boolean situacao) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.serie = serie;
        this.descricao = descricao;
        this.pessoa = pessoa;
        this.situacao = situacao;
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

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Boolean getSituacao() {
        return situacao;
    }

    public void setSituacao(Boolean situacao) {
        this.situacao = situacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipamento that = (Equipamento) o;
        return Objects.equals(id, that.id) && Objects.equals(marca, that.marca) && Objects.equals(modelo, that.modelo) && Objects.equals(serie, that.serie) && Objects.equals(descricao, that.descricao) && Objects.equals(pessoa, that.pessoa) && Objects.equals(situacao, that.situacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, marca, modelo, serie, descricao, pessoa, situacao);
    }

    @Override
    public String toString() {
        return "Equipamento{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", serie='" + serie + '\'' +
                ", descricao='" + descricao + '\'' +
                ", pessoa=" + pessoa +
                ", situacao=" + situacao +
                '}';
    }
}
