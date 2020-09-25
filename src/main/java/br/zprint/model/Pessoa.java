package br.zprint.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "pessoas")
public class Pessoa implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;
    private String razaoSocial;
    private String cnpj;
    private String inscricao;

    @Column(unique = true)
    private String email;

    private String celular;
    private String telefone;

    @Column(columnDefinition = "boolean default true")
    private Boolean situacao = true;

    public Pessoa(){};

    public Pessoa(Long id, String nome, String razaoSocial, String cnpj, String inscricao, String email, String celular, String telefone, Boolean situacao) {
        this.id = id;
        this.nome = nome;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.inscricao = inscricao;
        this.email = email;
        this.celular = celular;
        this.telefone = telefone;
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

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricao() {
        return inscricao;
    }

    public void setInscricao(String inscricao) {
        this.inscricao = inscricao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(id, pessoa.id) &&
                Objects.equals(nome, pessoa.nome) &&
                Objects.equals(razaoSocial, pessoa.razaoSocial) &&
                Objects.equals(cnpj, pessoa.cnpj) &&
                Objects.equals(inscricao, pessoa.inscricao) &&
                Objects.equals(email, pessoa.email) &&
                Objects.equals(celular, pessoa.celular) &&
                Objects.equals(telefone, pessoa.telefone) &&
                Objects.equals(situacao, pessoa.situacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, razaoSocial, cnpj, inscricao, email, celular, telefone, situacao);
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", razaoSocial='" + razaoSocial + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", inscricao='" + inscricao + '\'' +
                ", email='" + email + '\'' +
                ", celular='" + celular + '\'' +
                ", telefone='" + telefone + '\'' +
                ", situacao=" + situacao +
                '}';
    }
}
