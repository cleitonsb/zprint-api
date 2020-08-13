package br.zprint.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "enderecos")
public class Endereco implements Serializable {
    private static final Long serialVerionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String apelido;
    private String cep;
    private String logradouro;
    private String bairro;
    private String numero;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "cidade_id", nullable = false)
    private Cidade cidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return Objects.equals(id, endereco.id) &&
                Objects.equals(apelido, endereco.apelido) &&
                Objects.equals(cep, endereco.cep) &&
                Objects.equals(logradouro, endereco.logradouro) &&
                Objects.equals(bairro, endereco.bairro) &&
                Objects.equals(numero, endereco.numero) &&
                Objects.equals(usuario, endereco.usuario) &&
                Objects.equals(cidade, endereco.cidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, apelido, cep, logradouro, bairro, numero, usuario, cidade);
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "id=" + id +
                ", apelido='" + apelido + '\'' +
                ", cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", bairro='" + bairro + '\'' +
                ", numero='" + numero + '\'' +
                ", usuario=" + usuario +
                ", cidade=" + cidade +
                '}';
    }
}
