package br.zprint.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "compras")
public class Compra implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Timestamp data;

    @Column(columnDefinition = "Decimal(10,2) default '0'")
    private Double desconto;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "compra", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CompraItem> itensCompra;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = true)
    private Pessoa pessoa;

    @Column(columnDefinition = "boolean default true")
    private Boolean situacao = true;

    public Compra(){}

    public Compra(Long id, Timestamp data, Double desconto, Usuario usuario, List<CompraItem> itensCompra, Pessoa pessoa, Boolean situacao) {
        this.id = id;
        this.data = data;
        this.desconto = desconto;
        this.usuario = usuario;
        this.itensCompra = itensCompra;
        this.pessoa = pessoa;
        this.situacao = situacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<CompraItem> getItensCompra() {
        return itensCompra;
    }

    public void setItensCompra(List<CompraItem> itensCompra) {
        this.itensCompra = itensCompra;
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
        Compra compra = (Compra) o;
        return Objects.equals(id, compra.id) &&
                Objects.equals(data, compra.data) &&
                Objects.equals(desconto, compra.desconto) &&
                Objects.equals(usuario, compra.usuario) &&
                Objects.equals(itensCompra, compra.itensCompra) &&
                Objects.equals(pessoa, compra.pessoa) &&
                Objects.equals(situacao, compra.situacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, data, desconto, usuario, itensCompra, pessoa, situacao);
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", data=" + data +
                ", desconto=" + desconto +
                ", usuario=" + usuario +
                ", itensCompra=" + itensCompra +
                ", pessoa=" + pessoa +
                ", situacao=" + situacao +
                '}';
    }
}
