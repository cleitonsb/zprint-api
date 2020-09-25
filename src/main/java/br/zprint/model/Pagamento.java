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

    private Double valor;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "venda_id", nullable = true)
    private Venda venda;

    @Column(columnDefinition = "boolean default true")
    private Boolean situacao = true;

    public Pagamento() {
    }

    public Pagamento(Long id, TipoPagamento tipoPagamento, Double valor, Venda venda, Boolean situacao) {
        this.id = id;
        this.tipoPagamento = tipoPagamento;
        this.valor = valor;
        this.venda = venda;
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

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return Objects.equals(id, pagamento.id) &&
                tipoPagamento == pagamento.tipoPagamento &&
                Objects.equals(valor, pagamento.valor) &&
                Objects.equals(venda, pagamento.venda) &&
                Objects.equals(situacao, pagamento.situacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipoPagamento, valor, venda, situacao);
    }

    @Override
    public String toString() {
        return "Pagamento{" +
                "id=" + id +
                ", tipoPagamento=" + tipoPagamento +
                ", valor=" + valor +
                ", venda=" + venda +
                ", situacao=" + situacao +
                '}';
    }
}
