package br.zprint.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Perfil implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(joinColumns = { @JoinColumn(name = "perfil_id") }, inverseJoinColumns = { @JoinColumn(name = "permissao_id") })
    private List<Permissao> permissoes = new ArrayList<Permissao>();

    @OneToMany(mappedBy = "perfil", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Usuario> usuarios;

    public Perfil() {
    }

    public Perfil(Long id, String nome, List<Permissao> permissoes, List<Usuario> usuarios) {
        this.id = id;
        this.nome = nome;
        this.permissoes = permissoes;
        this.usuarios = usuarios;
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

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Perfil perfil = (Perfil) o;
        return Objects.equals(id, perfil.id) &&
                Objects.equals(nome, perfil.nome) &&
                Objects.equals(permissoes, perfil.permissoes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, permissoes);
    }

    @Override
    public String toString() {
        return "Perfil{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", permissoes=" + permissoes +
                '}';
    }
}
