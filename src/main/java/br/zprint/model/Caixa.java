package br.zprint.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "caixas")
public class Caixa implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Timestamp dataAbertura;

    private Timestamp dataFechamento;

    @Column(columnDefinition = "Decimal(10,2) default '0'")
    private Double fundo;

    @Column(columnDefinition = "Decimal(10,2) default '0'")
    private Double quebra;

    @Column(columnDefinition = "boolean default true")
    private Boolean situacao = true;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private Usuario usuario;

    @JsonIgnore
    @OneToMany(mappedBy = "caixa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Venda> vendas;

    public Caixa() {
    }

    public Caixa(Long id, Timestamp dataAbertura, Timestamp dataFechamento, Double fundo, Double quebra, Boolean situacao, Usuario usuario, List<Venda> vendas) {
        this.id = id;
        this.dataAbertura = dataAbertura;
        this.dataFechamento = dataFechamento;
        this.fundo = fundo;
        this.quebra = quebra;
        this.situacao = situacao;
        this.usuario = usuario;
        this.vendas = vendas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Timestamp dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Timestamp getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(Timestamp dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Double getFundo() {
        return fundo;
    }

    public void setFundo(Double fundo) {
        this.fundo = fundo;
    }

    public Double getQuebra() {
        return quebra;
    }

    public void setQuebra(Double quebra) {
        this.quebra = quebra;
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

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Caixa caixa = (Caixa) o;
        return Objects.equals(id, caixa.id) &&
                Objects.equals(dataAbertura, caixa.dataAbertura) &&
                Objects.equals(dataFechamento, caixa.dataFechamento) &&
                Objects.equals(fundo, caixa.fundo) &&
                Objects.equals(quebra, caixa.quebra) &&
                Objects.equals(situacao, caixa.situacao) &&
                Objects.equals(usuario, caixa.usuario) &&
                Objects.equals(vendas, caixa.vendas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataAbertura, dataFechamento, fundo, quebra, situacao, usuario, vendas);
    }

    @Override
    public String toString() {
        return "Caixa{" +
                "id=" + id +
                ", dataAbertura=" + dataAbertura +
                ", dataFechamento=" + dataFechamento +
                ", fundo=" + fundo +
                ", quebra=" + quebra +
                ", situacao=" + situacao +
                ", usuario=" + usuario +
                ", vendas=" + vendas +
                '}';
    }
}
