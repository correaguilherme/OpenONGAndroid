package com.br.senac.openongandroid.control;

import android.app.Activity;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.br.senac.openongandroid.R;
import com.br.senac.openongandroid.dao.BeneficiadoDao;
import com.br.senac.openongandroid.model.Beneficiado;
import com.br.senac.openongandroid.util.Constantes;
import com.br.senac.openongandroid.view.MainActivity;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.sql.SQLException;

import cz.msebera.android.httpclient.Header;

public class BeneficiadoControl {
    private Activity activity;
    private Beneficiado bn;
    private EditText editNome;
    private EditText editEmail;
    private EditText editTelefone;
    private EditText editCelular;
    private EditText editCPF;
    private EditText editSite;
    private BeneficiadoDao bnDao;

    public BeneficiadoControl(Activity activity) {
        this.activity = activity;
        this.bn = new Beneficiado();
        bnDao = new BeneficiadoDao(activity);
        initComponent();
        carregaForm();
    }

    private void carregaForm() {
        bn = (Beneficiado) activity.getIntent().getSerializableExtra(Constantes.Parametros.BENEFICIADO);
        editNome.setText(bn.getNome());
        editEmail.setText(bn.getEmail());
        editTelefone.setText(bn.getTelefone());
        editCelular.setText(bn.getCelular());
        editCPF.setText(bn.getCpf());
        editSite.setText(bn.getSite());
    }

    private void initComponent() {
        editNome = activity.findViewById(R.id.editNome);
        editEmail = activity.findViewById(R.id.editEmail);
        editTelefone = activity.findViewById(R.id.editTelefone);
        editCelular = activity.findViewById(R.id.editCelular);
        editCPF = activity.findViewById(R.id.editCpf);
        editSite = activity.findViewById(R.id.editSite);
    }

    private Beneficiado getDadosForm() {
        bn.setNome(editNome.getText().toString());
        bn.setEmail(editEmail.getText().toString());
        bn.setTelefone(editTelefone.getText().toString());
        bn.setCelular(editCelular.getText().toString());
        bn.setCpf(editCPF.getText().toString());
        bn.setSite(editSite.getText().toString());
        return bn;
    }

    public void salvarAction() {

        getDadosForm();

        if (valida(bn)) {

            AsyncHttpClient client = new AsyncHttpClient();
            String URL = "http://10.0.2.2:8084/backend/api/beneficiado/" + bn.getId();
            Gson gson = new Gson();
            String json = gson.toJson(bn);
            RequestParams params = new RequestParams("dado", json);
            client.put(URL, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int i, Header[] headers, byte[] bytes) {
                    String retorno = new String(bytes);

                    if (retorno == "true")
                        Toast.makeText(activity, "Beneficiado atualizado com sucesso", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

                }
            });

            try {
                bnDao.getDao().update(bn);
            } catch (SQLException e) { }

            Intent it = new Intent(activity, MainActivity.class);

            it.putExtra(Constantes.Parametros.BENEFICIADO, bn);
            activity.setResult(activity.RESULT_OK, it);
            activity.finish();
        }
    }

    public void voltarAction() {
        activity.setResult(activity.RESULT_CANCELED);
        activity.finish();
    }

    private boolean valida(Beneficiado bn) {
        return true;
    }
}
