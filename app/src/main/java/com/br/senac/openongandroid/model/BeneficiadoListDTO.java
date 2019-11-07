package com.br.senac.openongandroid.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BeneficiadoListDTO implements Serializable {

    private List<BeneficiadoDTO> beneficiados;

    public BeneficiadoListDTO() { }

    public BeneficiadoListDTO(List<BeneficiadoDTO> beneficiados) {
        this.beneficiados = beneficiados;
    }

    public List<BeneficiadoDTO> getBeneficiados() {
        return beneficiados;
    }

    public void setBeneficiados(List<BeneficiadoDTO> beneficiados) {
        this.beneficiados = beneficiados;
    }

    public BeneficiadoList getBeneficiadosDeNegocio(){
        BeneficiadoList pnlist = new BeneficiadoList();

        List<Beneficiado> pns = new ArrayList<>();

        for (BeneficiadoDTO pn: beneficiados) {
            pns.add(pn.getBeneficiado());
        }

        pnlist.setBeneficiados(pns);

        return pnlist;
    }
}
