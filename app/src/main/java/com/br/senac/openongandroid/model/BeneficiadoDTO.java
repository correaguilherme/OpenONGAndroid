package com.br.senac.openongandroid.model;

import java.io.Serializable;

public class BeneficiadoDTO implements Serializable {
    private Integer id;
    private String nome;
    private String email;
    private String telefone;
    private String celular;
    private String cpf;
    private String site;
    private String observacoes;

    public BeneficiadoDTO() {
    }

    public BeneficiadoDTO(String nome, String email, String telefone, String celular, String cpf, String site) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.celular = celular;
        this.cpf = cpf;
        this.site = site;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Beneficiado getBeneficiado(){
        Beneficiado p = new Beneficiado();
        p.setId(id);
        p.setNome(nome);
        p.setEmail(email);
        p.setTelefone(telefone);
        p.setCelular(celular);
        p.setCpf(cpf);
        return p;
    }

}