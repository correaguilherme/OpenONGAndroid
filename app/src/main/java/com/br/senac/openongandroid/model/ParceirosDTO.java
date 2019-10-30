package com.br.senac.openongandroid.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ParceirosDTO implements Serializable {

    private List<ParceiroDeNegocioDTO> parceiros;

    public List<ParceiroDeNegocioDTO> getParceiros() {
        return parceiros;
    }

    public void setParceiros(List<ParceiroDeNegocioDTO> parceiros) {
        this.parceiros = parceiros;
    }

    public List<ParceiroDeNegocio> getParceirosDeNegocio(){
        List<ParceiroDeNegocio> pns = new ArrayList<>();
        for(ParceiroDeNegocioDTO pDTO : parceiros){
            pns.add(pDTO.getParceiroDeNegocio());
        }
        return pns;
    }
}
