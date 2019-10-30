package com.br.senac.openongandroid.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ParceiroDeNegocioListDTO implements Serializable {

    private List<ParceiroDeNegocioDTO> parceiros;

    public ParceiroDeNegocioListDTO() { }

    public ParceiroDeNegocioListDTO(List<ParceiroDeNegocioDTO> parceiros) {
        this.parceiros = parceiros;
    }

    public List<ParceiroDeNegocioDTO> getParceiros() {
        return parceiros;
    }

    public void setParceiros(List<ParceiroDeNegocioDTO> parceiros) {
        this.parceiros = parceiros;
    }

    public List<ParceiroDeNegocio> getParceirosDeNegocio(){
        List<ParceiroDeNegocio> pns = new ArrayList<>();

        for (ParceiroDeNegocioDTO pn: parceiros) {
            pns.add(pn.getParceiroDeNegocio());
        }
        return pns;
    }
}
