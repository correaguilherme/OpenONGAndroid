package com.br.senac.openongandroid.dao;

import android.content.Context;

import com.br.senac.openongandroid.dao.helpers.DaoHelper;
import com.br.senac.openongandroid.model.Beneficiado;

public class BeneficiadoDao extends DaoHelper<Beneficiado> {

    public BeneficiadoDao(Context c) {
        super(c, Beneficiado.class);
    }
}
