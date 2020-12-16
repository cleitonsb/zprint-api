package br.zprint.dto;
import br.zprint.model.Usuario;
import java.io.Serializable;

public class UsuarioOpDTO implements Serializable {

    private static final long serialVersionUID = 2019276707435285890L;

    private Long id;
    private String nome;

    public UsuarioOpDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
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
}
