package br.zprint.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "compraitens")
public class CompraItem implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double preco;
    private Double qt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "compra_id", nullable = false)
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(columnDefinition = "boolean default true")
    private Boolean situacao = true;

    public CompraItem(){}

    public CompraItem(Long id, Double preco, Double qt, Compra compra, Produto produto, Boolean situacao) {
        this.id = id;
        this.preco = preco;
        this.qt = qt;
        this.compra = compra;
        this.produto = produto;
        this.situacao = situacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Double getQt() {
        return qt;
    }

    public void setQt(Double qt) {
        this.qt = qt;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
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
        CompraItem that = (CompraItem) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(preco, that.preco) &&
                Objects.equals(qt, that.qt) &&
                Objects.equals(compra, that.compra) &&
                Objects.equals(produto, that.produto) &&
                Objects.equals(situacao, that.situacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, preco, qt, compra, produto, situacao);
    }

    @Override
    public String toString() {
        return "CompraItem{" +
                "id=" + id +
                ", preco=" + preco +
                ", qt=" + qt +
                ", compra=" + compra +
                ", produto=" + produto +
                ", situacao=" + situacao +
                '}';
    }
}
