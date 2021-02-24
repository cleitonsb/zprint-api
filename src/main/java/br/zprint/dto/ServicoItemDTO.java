package br.zprint.dto;

import br.zprint.model.Produto;
import java.io.Serializable;

public class ServicoItemDTO implements Serializable {

    private static final long serialVersionUID = 2019276707435285890L;

    private Long id;
    private Double preco;
    private Double custo;
    private Double qt;
    private Produto produto;
}
