package br.zprint.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Permissao implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    public Permissao() {
    }

    public Permissao(String nome, String url, String metodo) {
        this.nome = nome;
        this.url = url;
        this.metodo = metodo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;
    private String url;
    private String metodo;
    private String nomeRegra;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getNomeRegra() {
        return nomeRegra;
    }

    public void setNomeRegra(String nomeRegra) {
        this.nomeRegra = nomeRegra;
    }

    @Override
    public String  getAuthority() {
        return this.nomeRegra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permissao permissao = (Permissao) o;
        return Objects.equals(id, permissao.id) &&
                Objects.equals(nome, permissao.nome) &&
                Objects.equals(url, permissao.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, url);
    }

    @Override
    public String toString() {
        return "Permissao{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
