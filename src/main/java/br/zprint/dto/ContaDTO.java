package br.zprint.dto;

import br.zprint.enums.TipoConta;
import br.zprint.model.*;
import java.io.Serializable;
import java.sql.Timestamp;

public class ContaDTO implements Serializable {

    private static final long serialVersionUID = -5559666776263948832L;

    private Long id;
    private Timestamp data = new Timestamp(System.currentTimeMillis());
    private Timestamp dataVencimento;
    private Timestamp dataPagamento;
    private Double valor;
    private TipoConta tipoConta;
    private PlanoConta planoConta;
    private String descricao;
    private Double troco;

    public ContaDTO(Conta conta) {
        this.id = conta.getId();
        this.data = conta.getData();
        this.dataVencimento = conta.getDataVencimento();
        this.dataPagamento = conta.getDataPagamento();
        this.valor = conta.getValor();
        this.tipoConta = conta.getTipoConta();
        this.planoConta = conta.getPlanoConta();
        this.descricao = conta.getDescricao();
        this.troco = conta.getTroco();
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getTroco() {
        return troco;
    }

    public void setTroco(Double troco) {
        this.troco = troco;
    }
}
