package br.zprint.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "vendaitens")
public class VendaItem implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double preco;
    private Double qt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "venda_id", nullable = false)
    private Venda venda;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(columnDefinition = "boolean default true")
    private Boolean situacao = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Number getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Number getQt() {
        return qt;
    }

    public void setQt(Double qt) {
        this.qt = qt;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
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
        VendaItem vendaItem = (VendaItem) o;
        return Objects.equals(id, vendaItem.id) &&
                Objects.equals(preco, vendaItem.preco) &&
                Objects.equals(qt, vendaItem.qt) &&
                Objects.equals(venda, vendaItem.venda) &&
                Objects.equals(produto, vendaItem.produto) &&
                Objects.equals(situacao, vendaItem.situacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, preco, qt, venda, produto, situacao);
    }

    @Override
    public String toString() {
        return "VendaItem{" +
                "id=" + id +
                ", preco=" + preco +
                ", qt=" + qt +
                ", venda=" + venda +
                ", produto=" + produto +
                ", situacao=" + situacao +
                '}';
    }
}
