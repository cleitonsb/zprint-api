package br.zprint.model;

import br.zprint.enums.TipoConta;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "contas")
public class Conta implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Timestamp data;
    private Timestamp dataVencimento;
    private Timestamp dataPagamento;

    @Column(columnDefinition = "Decimal(10,2) default '0'")
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "compra_id", referencedColumnName = "id", nullable = true)
    private Compra compra;

    @Enumerated(EnumType.STRING)
    private TipoConta tipoConta;

    @ManyToOne
    @JoinColumn(name = "plano_conta_id", referencedColumnName = "id", nullable = false)
    private PlanoConta planoConta;

    private String descricao;

    @Column(columnDefinition = "boolean default true")
    private Boolean situacao = true;

    public Conta(){}

    public Conta(Long id, Timestamp data, Timestamp dataVencimento, Timestamp dataPagamento, Double valor, Usuario usuario, Compra compra, TipoConta tipoConta, PlanoConta planoConta, String descricao, Boolean situacao) {
        this.id = id;
        this.data = data;
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
        this.valor = valor;
        this.usuario = usuario;
        this.compra = compra;
        this.tipoConta = tipoConta;
        this.planoConta = planoConta;
        this.descricao = descricao;
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

    public Timestamp getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Timestamp dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Timestamp getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Timestamp dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public PlanoConta getPlanoConta() {
        return planoConta;
    }

    public void setPlanoConta(PlanoConta planoConta) {
        this.planoConta = planoConta;
    }

    public Boolean getSituacao() {
        return situacao;
    }

    public void setSituacao(Boolean situacao) {
        this.situacao = situacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
