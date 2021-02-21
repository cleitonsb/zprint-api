package br.zprint.dto;

import br.zprint.model.Endereco;
import br.zprint.model.Pessoa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PessoaDTO implements Serializable {

    private static final long serialVersionUID = 2019276707435285890L;

    private Long id;
    private String nome;
    private String razaoSocial;
    private String cnpjCpf;
    private String inscricaoRg;
    private String email;
    private String celular;
    private String telefone;
    private List<Endereco> enderecos = new ArrayList<>();
    private Boolean cliente = false;
    private Boolean fornecedor = false;
    private List<EquipamentoDTO> equipamentos;

    public PessoaDTO(Pessoa pessoa) {
        this.id = pessoa.getId();
        this.nome = pessoa.getNome();
        this.razaoSocial = pessoa.getRazaoSocial();
        this.cnpjCpf = pessoa.getCnpjCpf();
        this.inscricaoRg = pessoa.getInscricaoRg();
        this.email = pessoa.getEmail();
        this.celular = pessoa.getCelular();
        this.telefone = pessoa.getTelefone();
        this.enderecos = pessoa.getEnderecos();
        this.cliente = pessoa.getCliente();
        this.fornecedor = pessoa.getFornecedor();

        for (int i = 0; i < pessoa.getEquipamentos().size(); i++) {
            this.equipamentos.add(new EquipamentoDTO(pessoa.getEquipamentos().get(i)));
        }
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

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpjCpf() {
        return cnpjCpf;
    }

    public void setCnpjCpf(String cnpjCpf) {
        this.cnpjCpf = cnpjCpf;
    }

    public String getInscricaoRg() {
        return inscricaoRg;
    }

    public void setInscricaoRg(String inscricaoRg) {
        this.inscricaoRg = inscricaoRg;
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

    public Boolean getCliente() {
        return cliente;
    }

    public void setCliente(Boolean cliente) {
        this.cliente = cliente;
    }

    public Boolean getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Boolean fornecedor) {
        this.fornecedor = fornecedor;
    }

    public List<EquipamentoDTO> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<EquipamentoDTO> equipamentos) {
        this.equipamentos = equipamentos;
    }
}
