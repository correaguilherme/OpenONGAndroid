package com.br.senac.openongandroid.control;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.br.senac.openongandroid.R;
import com.br.senac.openongandroid.dao.BeneficiadoDao;
import com.br.senac.openongandroid.model.Beneficiado;
import com.br.senac.openongandroid.model.BeneficiadoListDTO;
import com.br.senac.openongandroid.util.Constantes;
import com.br.senac.openongandroid.view.BeneficiadoActivity;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.sql.SQLException;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class BeneficiadoListControl {
    private Activity activity;
    private ListView lvBn;
    private ArrayAdapter<Beneficiado> adapterBn;

    private Beneficiado bn;
    private BeneficiadoDao bnDao;


    public BeneficiadoListControl(Activity activity) {
        this.activity = activity;
        bnDao = new BeneficiadoDao(activity);

        initComponents();
    }

    private void initComponents() {
        lvBn = activity.findViewById(R.id.lvBeneficiados);
        carregarBeneficiados();
    }


    private void cliqueCurto() {
        lvBn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bn = adapterBn.getItem(position);
                openEditForm(bn);
            }
        });
    }

    public void carregarBeneficiados() {
        AsyncHttpClient client = new AsyncHttpClient();
        String URL = "http://10.0.2.2:8084/backend/api/beneficiado";
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
                String bnsJSON = new String(bytes);

                BeneficiadoListDTO bnsDTO = new Gson().fromJson(bnsJSON, BeneficiadoListDTO.class);

                carregarListView(bnsDTO.getBeneficiadosDeNegocio().getBeneficiados());
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(activity, "Falhou.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void carregarListView(List<Beneficiado> bns) {

        adicionaBnBancoAndroid(bns);

        try {
            List<Beneficiado> bnsOrm = bnDao.getDao().queryForAll();
            adapterBn = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, bnsOrm);
        } catch (SQLException e) {
            adapterBn = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, bns);
        }

        lvBn.setAdapter(adapterBn);

        adapterBn.notifyDataSetChanged();

        cliqueCurto();
    }

    private void adicionaBnBancoAndroid(List<Beneficiado> bns) {
        for (Beneficiado bn : bns) {
            try {
                bnDao.getDao().createOrUpdate(bn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void openEditForm(Beneficiado bn) {
        Intent it = new Intent(activity, BeneficiadoActivity.class);
        it.putExtra(Constantes.Parametros.BENEFICIADO, bn);
        activity.startActivityForResult(it, Constantes.Request.BENEFICIADO);
    }

    public void voltarAction() {
        activity.setResult(activity.RESULT_CANCELED);
        activity.finish();
    }
}
