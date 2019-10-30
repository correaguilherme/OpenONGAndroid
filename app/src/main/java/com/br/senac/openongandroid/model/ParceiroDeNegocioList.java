package com.br.senac.openongandroid.model;

import java.io.Serializable;
import java.util.List;

public class ParceiroDeNegocioList implements Serializable {
    private List<ParceiroDeNegocio> parceiros;

    public ParceiroDeNegocioList() {
    }

    public ParceiroDeNegocioList(List<ParceiroDeNegocio> parceiros) {
        this.parceiros = parceiros;
    }

    public List<ParceiroDeNegocio> getParceiros() {
        return parceiros;
    }

    public void setParceiros(List<ParceiroDeNegocio> parceiros) {
        this.parceiros = parceiros;
    }
}
