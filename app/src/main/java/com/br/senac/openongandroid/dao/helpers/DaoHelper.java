package com.br.senac.openongandroid.dao.helpers;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public class DaoHelper<T> {
    protected Dao<T, Integer> dao;
    protected Class className;
    public static MyORMLiteHelper myInstance = null;

    public DaoHelper(Context c, Class className) {
        this.className = className;
        if (myInstance == null) myInstance = new MyORMLiteHelper(c.getApplicationContext());
    }

    public Dao<T, Integer> getDao() {
        try {
            return myInstance.getDao(className);
        } catch (SQLException e) {
            return null;
        }
    }
}
