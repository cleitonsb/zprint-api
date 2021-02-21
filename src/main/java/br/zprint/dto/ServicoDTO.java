package br.zprint.dto;

import br.zprint.model.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ServicoDTO implements Serializable {

    private static final long serialVersionUID = 2019276707435285890L;

    private Long id;
    private Timestamp data;
    private Timestamp previsao;
    private String relato;
    private String acessorios;
    private Double total;
    private Double desconto;
    private UsuarioOpDTO usuario;
    private UsuarioOpDTO responsavel;
    private PessoaDTO pessoa;
    private List<ServicoItem> itensServico;
    private List<Conta> contas = new ArrayList<>();
    private EquipamentoDTO equipamento;

    public ServicoDTO(Servico servico) {
        this.id = servico.getId();
        this.data = servico.getData();
        this.previsao = servico.getPrevisao();
        this.relato = servico.getRelato();
        this.acessorios = servico.getAcessorios();
        this.total = servico.getTotal();
        this.desconto = servico.getDesconto();
        this.usuario = new UsuarioOpDTO(servico.getUsuario());
        this.responsavel = new UsuarioOpDTO(servico.getResponsavel());
        this.pessoa = new PessoaDTO(servico.getPessoa());
        this.itensServico = servico.getItensServico();
        this.contas = servico.getContas();
        this.equipamento = new EquipamentoDTO(servico.getEquipamento());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public Timestamp getPrevisao() {
        return previsao;
    }

    public void setPrevisao(Timestamp previsao) {
        this.previsao = previsao;
    }

    public String getRelato() {
        return relato;
    }

    public void setRelato(String relato) {
        this.relato = relato;
    }

    public String getAcessorios() {
        return acessorios;
    }

    public void setAcessorios(String acessorios) {
        this.acessorios = acessorios;
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

    public UsuarioOpDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioOpDTO usuario) {
        this.usuario = usuario;
    }

    public UsuarioOpDTO getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(UsuarioOpDTO responsavel) {
        this.responsavel = responsavel;
    }

    public PessoaDTO getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaDTO pessoa) {
        this.pessoa = pessoa;
    }

    public List<ServicoItem> getItensServico() {
        return itensServico;
    }

    public void setItensServico(List<ServicoItem> itensServico) {
        this.itensServico = itensServico;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }

    public EquipamentoDTO getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(EquipamentoDTO equipamento) {
        this.equipamento = equipamento;
    }
}
