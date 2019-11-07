package com.br.senac.openongandroid.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable
public class Beneficiado implements Serializable {
    @DatabaseField(allowGeneratedIdInsert = true, generatedId = true)
    private Integer id;
    @DatabaseField(canBeNull = false)
    private String nome;
    @DatabaseField
    private String email;
    @DatabaseField
    private String telefone;
    @DatabaseField
    private String celular;
    @DatabaseField
    private String cpf;
    @DatabaseField
    private String site;

    public Beneficiado() {
    }

    public Beneficiado(String nome, String email, String telefone, String celular, String cpf, String site) {
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

    @Override
    public String toString() {
        return id + " - " + nome + " (" + cpf + ")";
    }
}
