package br.zprint.dto;

import br.zprint.model.Endereco;
import br.zprint.model.Produto;
import br.zprint.model.Usuario;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class ProdutoDTO implements Serializable {

    private static final long serialVersionUID = 2019276707435285890L;

    private Long id;
    private String nome;
    private String ean;
    private Long tipo;
    private Double preco;
    private Integer prazo;

    public ProdutoDTO(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.ean = produto.getEan();
        this.tipo = produto.getTipo();
        this.preco = produto.getPreco();
        this.prazo = produto.getPrazo();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public Long getTipo() {
        return tipo;
    }

    public void setTipo(Long tipo) {
        this.tipo = tipo;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getPrazo() {
        return prazo;
    }

    public void setPrazo(Integer prazo) {
        this.prazo = prazo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoDTO that = (ProdutoDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(nome, that.nome) &&
                Objects.equals(ean, that.ean) &&
                Objects.equals(tipo, that.tipo) &&
                Objects.equals(preco, that.preco) &&
                Objects.equals(prazo, that.prazo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, ean, tipo, preco, prazo);
    }

    @Override
    public String toString() {
        return "ProdutoDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", ean='" + ean + '\'' +
                ", tipo=" + tipo +
                ", preco=" + preco +
                ", prazo=" + prazo +
                '}';
    }
}
