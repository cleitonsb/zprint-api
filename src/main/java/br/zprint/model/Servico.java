package br.zprint.model;

import br.zprint.dto.UsuarioOpDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "servicos")
public class Servico implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Timestamp data;
    private Timestamp previsao;
    private String relato;
    private String acessorios;

    @Column(columnDefinition = "Decimal(10,2) default '0'")
    private Double total;

    @Column(columnDefinition = "Decimal(10,2) default '0'")
    private Double desconto;

    @Column(columnDefinition = "boolean default true")
    private Boolean situacao = true;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "responsavel_id", nullable = true)
    private Usuario responsavel;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;

    @OneToMany(mappedBy = "servico", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ServicoItem> itensServico;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(joinColumns = { @JoinColumn(name = "servico_id") }, inverseJoinColumns = { @JoinColumn(name = "conta_id") })
    private List<Conta> contas = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "equipamento_id", nullable = true)
    private Equipamento equipamento;

    public Servico(){}

    public Servico(Long id, Timestamp data, Timestamp previsao, String relato, String acessorios, Double total, Double desconto, Boolean situacao, Usuario usuario, Usuario responsavel, Pessoa pessoa, List<ServicoItem> itensServico, List<Conta> contas, Equipamento equipamento) {
        this.id = id;
        this.data = data;
        this.previsao = previsao;
        this.relato = relato;
        this.acessorios = acessorios;
        this.total = total;
        this.desconto = desconto;
        this.situacao = situacao;
        this.usuario = usuario;
        this.responsavel = responsavel;
        this.pessoa = pessoa;
        this.itensServico = itensServico;
        this.contas = contas;
        this.equipamento = equipamento;
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
        return new UsuarioOpDTO(usuario);
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UsuarioOpDTO getResponsavel() {
        return new UsuarioOpDTO(usuario);
    }

    public void setResponsavel(Usuario responsavel) {
        this.responsavel = responsavel;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
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

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Servico servico = (Servico) o;
        return Objects.equals(id, servico.id) && Objects.equals(data, servico.data) && Objects.equals(previsao, servico.previsao) && Objects.equals(relato, servico.relato) && Objects.equals(acessorios, servico.acessorios) && Objects.equals(total, servico.total) && Objects.equals(desconto, servico.desconto) && Objects.equals(situacao, servico.situacao) && Objects.equals(usuario, servico.usuario) && Objects.equals(responsavel, servico.responsavel) && Objects.equals(pessoa, servico.pessoa) && Objects.equals(itensServico, servico.itensServico) && Objects.equals(contas, servico.contas) && Objects.equals(equipamento, servico.equipamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, data, previsao, relato, acessorios, total, desconto, situacao, usuario, responsavel, pessoa, itensServico, contas, equipamento);
    }

    @Override
    public String toString() {
        return "Servico{" +
                "id=" + id +
                ", data=" + data +
                ", previsao=" + previsao +
                ", relato='" + relato + '\'' +
                ", acessorios='" + acessorios + '\'' +
                ", total=" + total +
                ", desconto=" + desconto +
                ", situacao=" + situacao +
                ", usuario=" + usuario +
                ", responsavel=" + responsavel +
                ", pessoa=" + pessoa +
                ", itensServico=" + itensServico +
                ", contas=" + contas +
                ", equipamento=" + equipamento +
                '}';
    }
}
