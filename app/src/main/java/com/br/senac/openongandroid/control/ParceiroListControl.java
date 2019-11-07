package com.br.senac.openongandroid.control;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.br.senac.openongandroid.R;
import com.br.senac.openongandroid.dao.ParceiroDeNegocioDao;
import com.br.senac.openongandroid.model.ParceiroDeNegocio;
import com.br.senac.openongandroid.model.ParceiroDeNegocioListDTO;
import com.br.senac.openongandroid.util.Constantes;
import com.br.senac.openongandroid.view.ParceiroActivity;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.sql.SQLException;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class ParceiroListControl {
    private Activity activity;
    private ListView lvPn;
    private ArrayAdapter<ParceiroDeNegocio> adapterPn;

    private ParceiroDeNegocio pn;
    private ParceiroDeNegocioDao pnDao;


    public ParceiroListControl(Activity activity) {
        this.activity = activity;
        pnDao = new ParceiroDeNegocioDao(activity);

        initComponents();
    }

    private void initComponents() {
        lvPn = activity.findViewById(R.id.lvParceiros);
        carregarParceiros();
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

    public void carregarParceiros() {
        AsyncHttpClient client = new AsyncHttpClient();
        String URL = "http://10.0.2.2:8084/backend/api/parceirodenegocio";
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

                ParceiroDeNegocioListDTO pnsDTO = new Gson().fromJson(pnsJSON, ParceiroDeNegocioListDTO.class);

                carregarListView(pnsDTO.getParceirosDeNegocio().getParceiros());
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(activity, "Falhou.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void carregarListView(List<ParceiroDeNegocio> pns) {

        adicionaPnBancoAndroid(pns);

        try {
            List<ParceiroDeNegocio> pnsOrm = pnDao.getDao().queryForAll();
            adapterPn = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, pnsOrm);
        } catch (SQLException e) {
            adapterPn = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, pns);
        }

        lvPn.setAdapter(adapterPn);

        adapterPn.notifyDataSetChanged();

        cliqueCurto();
    }

    private void adicionaPnBancoAndroid(List<ParceiroDeNegocio> pns) {
        for (ParceiroDeNegocio pn : pns) {
            try {
                pnDao.getDao().createOrUpdate(pn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void openEditForm(ParceiroDeNegocio pn) {
        Intent it = new Intent(activity, ParceiroActivity.class);
        it.putExtra(Constantes.Parametros.PARCEIRO, pn);
        activity.startActivityForResult(it, Constantes.Request.PARCEIRO);
    }

    public void voltarAction() {
        activity.setResult(activity.RESULT_CANCELED);
        activity.finish();
    }
}