package com.br.senac.openongandroid.dao;

import android.content.Context;

import com.br.senac.openongandroid.dao.helpers.DaoHelper;
import com.br.senac.openongandroid.model.ParceiroDeNegocio;

public class ParceiroDeNegocioDao extends DaoHelper<ParceiroDeNegocio> {

    public ParceiroDeNegocioDao(Context c) {
        super(c, ParceiroDeNegocio.class);
    }
}
