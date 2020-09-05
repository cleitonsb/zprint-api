package br.zprint.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "produtos")
public class Produto implements Serializable {

    private static final long serialVersionUID = -3095059815606580245L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;

    @Column(unique = true)
    private String ean;
    private Long tipo; // 1 - Produto, 2 - Serviço
    private Double preco;

    @Column(columnDefinition = "integer default 0")
    private Integer prazo;

    @Column(columnDefinition = "boolean default true")
    private Boolean situacao = true;

    public Produto(Long id, String nome, String ean, Long tipo, Double preco, Integer prazo, Boolean situacao) {
        this.id = id;
        this.nome = nome;
        this.ean = ean;
        this.tipo = tipo;
        this.preco = preco;
        this.prazo = prazo;
        this.situacao = situacao;
    }

    public Produto() {
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
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id) &&
                Objects.equals(nome, produto.nome) &&
                Objects.equals(ean, produto.ean) &&
                Objects.equals(tipo, produto.tipo) &&
                Objects.equals(preco, produto.preco) &&
                Objects.equals(prazo, produto.prazo) &&
                Objects.equals(situacao, produto.situacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, ean, tipo, preco, prazo, situacao);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", ean='" + ean + '\'' +
                ", tipo=" + tipo +
                ", preco=" + preco +
                ", prazo=" + prazo +
                ", situacao=" + situacao +
                '}';
    }
}
