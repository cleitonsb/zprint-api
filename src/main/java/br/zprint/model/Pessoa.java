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
    private String cnpjCpf;
    private String inscricaoRg;

    @Column(unique = true)
    private String email;
    private String celular;
    private String telefone;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(joinColumns = { @JoinColumn(name = "pessoa_id") }, inverseJoinColumns = { @JoinColumn(name = "endereco_id") })
    private List<Endereco> enderecos = new ArrayList<>();

    @Column(columnDefinition = "boolean default true")
    private Boolean situacao = true;

    @Column(columnDefinition = "boolean default false")
    private Boolean cliente = false;

    @Column(columnDefinition = "boolean default false")
    private Boolean fornecedor = false;

    @OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Equipamento> equipamentos;

    public Pessoa(){};

    public Pessoa(Long id, String nome, String razaoSocial, String cnpjCpf, String inscricaoRg, String email, String celular, String telefone, List<Endereco> enderecos, Boolean situacao, Boolean cliente, Boolean fornecedor, List<Equipamento> equipamentos) {
        this.id = id;
        this.nome = nome;
        this.razaoSocial = razaoSocial;
        this.cnpjCpf = cnpjCpf;
        this.inscricaoRg = inscricaoRg;
        this.email = email;
        this.celular = celular;
        this.telefone = telefone;
        this.enderecos = enderecos;
        this.situacao = situacao;
        this.cliente = cliente;
        this.fornecedor = fornecedor;
        this.equipamentos = equipamentos;
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

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Boolean getCliente() {
        return cliente;
    }

    public void setCliente(Boolean cliente) {
        this.cliente = cliente;
    }

    public Boolean getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Boolean fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getCnpjCpf() {
        return cnpjCpf;
    }

    public void setCnpjCpf(String cnpjCpf) {
        this.cnpjCpf = cnpjCpf;
    }

    public String getInscricaoRg() {
        return inscricaoRg;
    }

    public void setInscricaoRg(String inscricaoRg) {
        this.inscricaoRg = inscricaoRg;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(id, pessoa.id) &&
                Objects.equals(nome, pessoa.nome) &&
                Objects.equals(razaoSocial, pessoa.razaoSocial) &&
                Objects.equals(cnpjCpf, pessoa.cnpjCpf) &&
                Objects.equals(inscricaoRg, pessoa.inscricaoRg) &&
                Objects.equals(email, pessoa.email) &&
                Objects.equals(celular, pessoa.celular) &&
                Objects.equals(telefone, pessoa.telefone) &&
                Objects.equals(situacao, pessoa.situacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, razaoSocial, cnpjCpf, inscricaoRg, email, celular, telefone, situacao);
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", razaoSocial='" + razaoSocial + '\'' +
                ", cnpjCpf='" + cnpjCpf + '\'' +
                ", inscricaoRg='" + inscricaoRg + '\'' +
                ", email='" + email + '\'' +
                ", celular='" + celular + '\'' +
                ", telefone='" + telefone + '\'' +
                ", situacao=" + situacao +
                '}';
    }
}
