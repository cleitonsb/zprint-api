package br.zprint.model;

import br.zprint.dto.UsuarioDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
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

    @ManyToOne
    @JoinColumn(name = "caixa_id", nullable = true)
    private Caixa caixa;

    @OneToMany(mappedBy = "venda", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Pagamento> pagamentos;

    @Column(columnDefinition = "Decimal(10,2) default '0'")
    private Double troco;

    public Venda() {
    }

    public Venda(Long id, String nome, Timestamp data, Double total, Double desconto, Boolean situacao, Usuario usuario, List<VendaItem> itensVenda, Caixa caixa, List<Pagamento> pagamentos, Double troco) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.total = total;
        this.desconto = desconto;
        this.situacao = situacao;
        this.usuario = usuario;
        this.itensVenda = itensVenda;
        this.caixa = caixa;
        this.pagamentos = pagamentos;
        this.troco = troco;
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

    public Usuario getUsuario() {
        return usuario;
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

    public Caixa getCaixa() {
        return caixa;
    }

    public void setCaixa(Caixa caixa) {
        this.caixa = caixa;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    public Double getTroco() {
        return troco;
    }

    public void setTroco(Double troco) {
        this.troco = troco;
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
                Objects.equals(itensVenda, venda.itensVenda) &&
                Objects.equals(caixa, venda.caixa) &&
                Objects.equals(pagamentos, venda.pagamentos) &&
                Objects.equals(troco, venda.troco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, data, total, desconto, situacao, usuario, itensVenda, caixa, pagamentos, troco);
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
                ", caixa=" + caixa +
                ", pagamentos=" + pagamentos +
                ", troco=" + troco +
                '}';
    }
}
