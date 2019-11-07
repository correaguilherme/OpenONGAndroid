package model.list;

import java.util.List;
import model.Beneficiado;

public class BeneficiadoList {

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
