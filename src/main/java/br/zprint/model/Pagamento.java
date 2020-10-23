package br.zprint.model;

import br.zprint.enums.TipoPagamento;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "pagamentos")
public class Pagamento implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoPagamento tipoPagamento;

    private Number parcelas;
    private Double valor;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "conta_id", nullable = true)
    private Conta conta;

    @Column(columnDefinition = "boolean default true")
    private Boolean situacao = true;

    public Pagamento() {
    }

    public Pagamento(Long id, TipoPagamento tipoPagamento, Number parcelas, Double valor, Conta conta, Boolean situacao) {
        this.id = id;
        this.tipoPagamento = tipoPagamento;
        this.parcelas = parcelas;
        this.valor = valor;
        this.conta = conta;
        this.situacao = situacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Boolean getSituacao() {
        return situacao;
    }

    public void setSituacao(Boolean situacao) {
        this.situacao = situacao;
    }

    public Number getParcelas() {
        return parcelas;
    }

    public void setParcelas(Number parcelas) {
        this.parcelas = parcelas;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return Objects.equals(id, pagamento.id) &&
                tipoPagamento == pagamento.tipoPagamento &&
                Objects.equals(parcelas, pagamento.parcelas) &&
                Objects.equals(valor, pagamento.valor) &&
                Objects.equals(conta, pagamento.conta) &&
                Objects.equals(situacao, pagamento.situacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipoPagamento, parcelas, valor, conta, situacao);
    }

    @Override
    public String toString() {
        return "Pagamento{" +
                "id=" + id +
                ", tipoPagamento=" + tipoPagamento +
                ", parcelas=" + parcelas +
                ", valor=" + valor +
                ", conta=" + conta +
                ", situacao=" + situacao +
                '}';
    }
}
