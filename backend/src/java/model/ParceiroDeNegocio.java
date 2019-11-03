package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class ParceiroDeNegocio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 100, nullable = false)
    private String nome;
    @Column(length = 100)
    private String email;
    @Column(length = 30)
    private String telefone;
    @Column(length = 30)
    private String celular;
    @Column(length = 30)
    private String cpf;
    @Lob
    private String observacoes;
    @Column(length = 200)
    private String site;

    private static final long serialVersionUID = 1L;

    public ParceiroDeNegocio() {
    }

    public ParceiroDeNegocio(String nome, String email, String telefone, String celular, String cpf, String observacoes, String site, String cnpj) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.celular = celular;
        this.cpf = cpf;
        this.observacoes = observacoes;
        this.site = site;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
