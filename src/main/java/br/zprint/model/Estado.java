package br.zprint.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "estados")
public class Estado implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String uf;
    private String ibge;

    @OneToMany(mappedBy = "estado", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Cidade> cidades;

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

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getIbge() {
        return ibge;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estado estado = (Estado) o;
        return Objects.equals(id, estado.id) &&
                Objects.equals(nome, estado.nome) &&
                Objects.equals(uf, estado.uf) &&
                Objects.equals(ibge, estado.ibge);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, uf, ibge);
    }

    @Override
    public String toString() {
        return "Estado{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", uf='" + uf + '\'' +
                ", ibge='" + ibge + '\'' +
                '}';
    }
}
