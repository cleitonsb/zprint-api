package br.zprint.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "servicoitens")
public class ServicoItem implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "Decimal(10,2) default '0'")
    private Double preco;

    @Column(columnDefinition = "Decimal(10,2) default '0'")
    private Double custo;

    private Double qt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "servico_id", nullable = false)
    private Servico servico;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(columnDefinition = "boolean default true")
    private Boolean situacao = true;

    public ServicoItem() {
    }

    public ServicoItem(Long id, Double preco, Double custo, Double qt, Servico servico, Produto produto, Boolean situacao) {
        this.id = id;
        this.preco = preco;
        this.custo = custo;
        this.qt = qt;
        this.servico = servico;
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

    public Double getCusto() {
        return custo;
    }

    public void setCusto(Double custo) {
        this.custo = custo;
    }

    public Double getQt() {
        return qt;
    }

    public void setQt(Double qt) {
        this.qt = qt;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
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
        ServicoItem that = (ServicoItem) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(preco, that.preco) &&
                Objects.equals(custo, that.custo) &&
                Objects.equals(qt, that.qt) &&
                Objects.equals(servico, that.servico) &&
                Objects.equals(produto, that.produto) &&
                Objects.equals(situacao, that.situacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, preco, custo, qt, servico, produto, situacao);
    }

    @Override
    public String toString() {
        return "ServicoItem{" +
                "id=" + id +
                ", preco=" + preco +
                ", custo=" + custo +
                ", qt=" + qt +
                ", servico=" + servico +
                ", produto=" + produto +
                ", situacao=" + situacao +
                '}';
    }
}
