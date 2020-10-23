package br.zprint.dto;

import br.zprint.model.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class VendaDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private String data;
    private Double total;
    private Double desconto;
    private Boolean situacao;
    private UsuarioOpDTO usuario;
    private List<VendaItem> itensVenda;

    public VendaDTO(Venda venda) {
        this.id = venda.getId();
        this.nome = venda.getNome();
        this.total = venda.getTotal();
        this.desconto = venda.getDesconto();
        this.situacao = venda.getSituacao();
        this.usuario = venda.getUsuario();
        this.itensVenda = venda.getItensVenda();

        Timestamp ts = venda.getData();
        Date data = new Date();
        data.setTime(ts.getTime());
        this.data = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(data);
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Boolean getSituacao() {
        return situacao;
    }

    public void setSituacao(Boolean situacao) {
        this.situacao = situacao;
    }

    public UsuarioOpDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioOpDTO usuario) {
        this.usuario = usuario;
    }

    public List<VendaItem> getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(List<VendaItem> itensVenda) {
        this.itensVenda = itensVenda;
    }

}
