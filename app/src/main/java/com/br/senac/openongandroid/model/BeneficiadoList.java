package com.br.senac.openongandroid.model;

import java.io.Serializable;
import java.util.List;

public class BeneficiadoList implements Serializable {
    private List<Beneficiado> beneficiados;

    public BeneficiadoList() {
    }

    public BeneficiadoList(List<Beneficiado> beneficiados) {
        this.beneficiados = beneficiados;
    }

    public List<Beneficiado> getBeneficiados() {
        return beneficiados;
    }

    public void setBeneficiados(List<Beneficiado> beneficiados) {
        this.beneficiados = beneficiados;
    }
}
