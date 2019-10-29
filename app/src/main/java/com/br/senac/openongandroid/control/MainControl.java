package com.br.senac.openongandroid.control;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.br.senac.openongandroid.R;
import com.br.senac.openongandroid.dao.ParceiroDeNegocioDao;
import com.br.senac.openongandroid.model.ParceiroDeNegocio;
import com.br.senac.openongandroid.model.ParceirosDTO;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainControl {
    private Activity activity;
    private ListView lvPn;
    private ArrayAdapter<ParceiroDeNegocio> adapterPn;
    private List<ParceiroDeNegocio> listPns;

    private ParceiroDeNegocio pn;
    private ParceiroDeNegocioDao pnDao;


    public MainControl(Activity activity) {
        this.activity = activity;
        pnDao = new ParceiroDeNegocioDao(activity);

        initComponents();
    }

    private void initComponents() {
        lvPn = activity.findViewById(R.id.lvParceiros);
        configListView();
        carregarParceiros();
    }

    private void configListView() {

        //try {
            //listPns = pnDao.getDao().queryForAll();
        //} catch (SQLException e) {
        //    e.printStackTrace();
        //}

        //adapterPn = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, listPns);
        //lvPn.setAdapter(adapterPn);
    }

    private void cliqueCurto() {
        lvPn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pn = adapterPn.getItem(position);
                openEditForm(pn);
            }
        });
    }

    private void carregarParceiros() {
        AsyncHttpClient client = new AsyncHttpClient();
        String URL = "http://localhost:8084/backend/api/parceirodenegocio";
        client.get(URL, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                Toast.makeText(activity, "Iniciando requisição", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRetry(int retryNo) {
                super.onRetry(retryNo);
                Toast.makeText(activity, "Tentativa " + retryNo, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String pnsJSON = new String(bytes);

                Type listType = new TypeToken<ArrayList<ParceirosDTO>>() {
                }.getType();
                ParceirosDTO pns = new Gson().fromJson(pnsJSON, listType);
                carregarListView(pns);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(activity, "Falhou.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void carregarListView(ParceirosDTO pns) {
        adapterPn = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, pns.getParceirosDeNegocio());
        lvPn.setAdapter(adapterPn);
        cliqueCurto();
    }

    private void openEditForm(ParceiroDeNegocio pn) {

    }
}
