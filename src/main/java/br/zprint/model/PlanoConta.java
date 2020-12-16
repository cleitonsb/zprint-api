package br.zprint.model;

import br.zprint.enums.TipoConta;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "plano_contas")
public class PlanoConta implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoConta tipoConta;

    @Column(columnDefinition = "boolean default true")
    private Boolean situacao = true;

    public PlanoConta(){}

    public PlanoConta(Long id, String nome, TipoConta tipoConta, Boolean situacao) {
        this.id = id;
        this.nome = nome;
        this.tipoConta = tipoConta;
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

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public Boolean getSituacao() {
        return situacao;
    }

    public void setSituacao(Boolean situacao) {
        this.situacao = situacao;
    }
}
