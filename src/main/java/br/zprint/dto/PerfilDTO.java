package br.zprint.dto;

import br.zprint.model.Perfil;
import br.zprint.model.Permissao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PerfilDTO implements Serializable {

    private static final long serialVersionUID = -5559666776263948832L;

    private Long id;
    private String nome;
    private List<PermissaoDTO> permissoes = new ArrayList<PermissaoDTO>();


    public PerfilDTO(Perfil perfil) {

        for (Permissao permissao : perfil.getPermissoes()) {
            this.permissoes.add(new PermissaoDTO(permissao));
        }

        this.id = perfil.getId();
        this.nome = perfil.getNome();
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

    public List<PermissaoDTO> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<PermissaoDTO> permissoes) {
        this.permissoes = permissoes;
    }
}
