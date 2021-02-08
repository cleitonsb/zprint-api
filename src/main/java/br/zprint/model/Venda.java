package br.zprint.model;

import br.zprint.dto.UsuarioDTO;
import br.zprint.dto.UsuarioOpDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "vendas")
public class Venda implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;

    private Timestamp data;

    @Column(columnDefinition = "Decimal(10,2) default '0'")
    private Double total;

    @Column(columnDefinition = "Decimal(10,2) default '0'")
    private Double desconto;

    @Column(columnDefinition = "boolean default true")
    private Boolean situacao = true;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "venda", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<VendaItem> itensVenda;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(joinColumns = { @JoinColumn(name = "venda_id") }, inverseJoinColumns = { @JoinColumn(name = "conta_id") })
    private List<Conta> contas = new ArrayList<>();

    public Venda() {
    }

    public Venda(Long id, String nome, Timestamp data, Double total, Double desconto, Boolean situacao, Usuario usuario, List<VendaItem> itensVenda, List<Conta> contas) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.total = total;
        this.desconto = desconto;
        this.situacao = situacao;
        this.usuario = usuario;
        this.itensVenda = itensVenda;
        this.contas = contas;
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

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Boolean getSituacao() {
        return situacao;
    }

    public void setSituacao(Boolean situacao) {
        this.situacao = situacao;
    }

    public UsuarioOpDTO getUsuario() {
        return new UsuarioOpDTO(usuario);
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<VendaItem> getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(List<VendaItem> itensVenda) {
        this.itensVenda = itensVenda;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venda venda = (Venda) o;
        return Objects.equals(id, venda.id) &&
                Objects.equals(nome, venda.nome) &&
                Objects.equals(data, venda.data) &&
                Objects.equals(total, venda.total) &&
                Objects.equals(desconto, venda.desconto) &&
                Objects.equals(situacao, venda.situacao) &&
                Objects.equals(usuario, venda.usuario) &&
                Objects.equals(itensVenda, venda.itensVenda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, data, total, desconto, situacao, usuario, itensVenda);
    }

    @Override
    public String toString() {
        return "Venda{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", data=" + data +
                ", total=" + total +
                ", desconto=" + desconto +
                ", situacao=" + situacao +
                ", usuario=" + usuario +
                ", itensVenda=" + itensVenda +
                '}';
    }
}
