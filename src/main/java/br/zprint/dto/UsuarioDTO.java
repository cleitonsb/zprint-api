package br.zprint.dto;

import br.zprint.model.Endereco;
import br.zprint.model.Perfil;
import br.zprint.model.Usuario;

import java.io.Serializable;
import java.util.List;

public class UsuarioDTO implements Serializable {

    private static final long serialVersionUID = 2019276707435285890L;

    private Long id;
    private String nome;
    private String email;
    private String celular;
    private String telefone;
    private List<Endereco> enderecos;
    private PerfilDTO perfil;

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.celular = usuario.getCelular();
        this.telefone = usuario.getTelefone();
        this.enderecos = usuario.getEnderecos();
        this.perfil = new PerfilDTO(usuario.getPerfil());
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

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public PerfilDTO getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilDTO perfil) {
        this.perfil = perfil;
    }
}
