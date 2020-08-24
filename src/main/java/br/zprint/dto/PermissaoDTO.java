package br.zprint.dto;

import br.zprint.model.Permissao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PermissaoDTO implements Serializable {

    private static final long serialVersionUID = -8497741368437756627L;

    private String nome;
    private String tipo;
    private String rota;
    private static final Map<String, String> tipos = new HashMap<String, String>() {{
        put("GET", "visualizar");
        put("POST", "cadastrar");
        put("PUT", "editar");
        put("DELETE", "deletar");
    }};

    public PermissaoDTO(Permissao permissao) {

        String tipo = tipos.get(permissao.getMetodo());

        this.nome = permissao.getNome();
        this.tipo = tipo;
        this.rota = permissao.getRota();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRota() {
        return rota;
    }

    public void setRota(String rota) {
        this.rota = rota;
    }
}
