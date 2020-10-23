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
    private String marca;
    private String codigo;

    @Column(unique = true)
    private String ean;
    private Long tipo; /** 1 - Produto, 2 - Serviço */

    @Column(columnDefinition = "Decimal(10,2) default '0'")
    private Double preco;

    @Column(columnDefinition = "Decimal(10,2) default '0'")
    private Double custo;

    @Column(columnDefinition = "integer default 0")
    private Integer prazo;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = true)
    private Pessoa pessoa;

    @Column(columnDefinition = "boolean default true")
    private Boolean situacao = true;

    public Produto() {
    }

    public Produto(Long id, String nome, String marca, String codigo, String ean, Long tipo, Double preco, Double custo, Integer prazo, Pessoa pessoa, Boolean situacao) {
        this.id = id;
        this.nome = nome;
        this.marca = marca;
        this.codigo = codigo;
        this.ean = ean;
        this.tipo = tipo;
        this.preco = preco;
        this.custo = custo;
        this.prazo = prazo;
        this.pessoa = pessoa;
        this.situacao = situacao;
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public Double getCusto() {
        return custo;
    }

    public void setCusto(Double custo) {
        this.custo = custo;
    }

    public Integer getPrazo() {
        return prazo;
    }

    public void setPrazo(Integer prazo) {
        this.prazo = prazo;
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
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id) &&
                Objects.equals(nome, produto.nome) &&
                Objects.equals(marca, produto.marca) &&
                Objects.equals(codigo, produto.codigo) &&
                Objects.equals(ean, produto.ean) &&
                Objects.equals(tipo, produto.tipo) &&
                Objects.equals(preco, produto.preco) &&
                Objects.equals(custo, produto.custo) &&
                Objects.equals(prazo, produto.prazo) &&
                Objects.equals(pessoa, produto.pessoa) &&
                Objects.equals(situacao, produto.situacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, marca, codigo, ean, tipo, preco, custo, prazo, pessoa, situacao);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + "'" +
                ", marca='" + marca + "'" +
                ", codigo='" + codigo + "'" +
                ", ean='" + ean + "'" +
                ", tipo=" + tipo +
                ", preco=" + preco +
                ", custo=" + custo +
                ", prazo=" + prazo +
                ", pessoa=" + pessoa +
                ", situacao=" + situacao +
                '}';
    }
}
