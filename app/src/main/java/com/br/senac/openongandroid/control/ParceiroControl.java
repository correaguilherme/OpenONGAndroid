package com.br.senac.openongandroid.control;

import android.app.Activity;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.br.senac.openongandroid.R;
import com.br.senac.openongandroid.dao.ParceiroDeNegocioDao;
import com.br.senac.openongandroid.model.ParceiroDeNegocio;
import com.br.senac.openongandroid.util.Constantes;
import com.br.senac.openongandroid.view.MainActivity;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.sql.SQLException;

import cz.msebera.android.httpclient.Header;

public class ParceiroControl {
    private Activity activity;
    private ParceiroDeNegocio pn;
    private EditText editNome;
    private EditText editEmail;
    private EditText editTelefone;
    private EditText editCelular;
    private EditText editCPF;
    private EditText editSite;
    private EditText editObservacoes;
    private ParceiroDeNegocioDao pnDao;

    public ParceiroControl(Activity activity) {
        this.activity = activity;
        this.pn = new ParceiroDeNegocio();
        pnDao = new ParceiroDeNegocioDao(activity);
        initComponent();
        carregaForm();
    }

    private void carregaForm() {
        pn = (ParceiroDeNegocio) activity.getIntent().getSerializableExtra(Constantes.Parametros.PARCEIRO);
        editNome.setText(pn.getNome());
        editEmail.setText(pn.getEmail());
        editTelefone.setText(pn.getTelefone());
        editCelular.setText(pn.getCelular());
        editCPF.setText(pn.getCpf());
        editSite.setText(pn.getSite());
        editObservacoes.setText(pn.getObservacoes());
    }

    private void initComponent() {
        editNome = activity.findViewById(R.id.editNome);
        editEmail = activity.findViewById(R.id.editEmail);
        editTelefone = activity.findViewById(R.id.editTelefone);
        editCelular = activity.findViewById(R.id.editCelular);
        editCPF = activity.findViewById(R.id.editCpf);
        editSite = activity.findViewById(R.id.editSite);
        editObservacoes = activity.findViewById(R.id.editObservacoes);
    }

    private ParceiroDeNegocio getDadosForm() {
        pn.setNome(editNome.getText().toString());
        pn.setEmail(editEmail.getText().toString());
        pn.setTelefone(editTelefone.getText().toString());
        pn.setCelular(editCelular.getText().toString());
        pn.setCpf(editCPF.getText().toString());
        pn.setSite(editSite.getText().toString());
        pn.setObservacoes(editObservacoes.getText().toString());
        return pn;
    }

    public void salvarAction() {

        getDadosForm();

        if (valida(pn)) {

            AsyncHttpClient client = new AsyncHttpClient();
            String URL = "http://10.0.2.2:8084/backend/api/parceirodenegocio/" + pn.getId();
            Gson gson = new Gson();
            String json = gson.toJson(pn);
            RequestParams params = new RequestParams("dado", json);
            client.put(URL, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int i, Header[] headers, byte[] bytes) {
                    String retorno = new String(bytes);

                    if (retorno == "true")
                        Toast.makeText(activity, "Parceiro atualizado com sucesso", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

                }
            });

            try {
                pnDao.getDao().update(pn);
            } catch (SQLException e) { }

            Intent it = new Intent(activity, MainActivity.class);

            it.putExtra(Constantes.Parametros.PARCEIRO, pn);
            activity.setResult(activity.RESULT_OK, it);
            activity.finish();
        }
    }

    public void voltarAction() {
        activity.setResult(activity.RESULT_CANCELED);
        activity.finish();
    }

    private boolean valida(ParceiroDeNegocio pn) {
        return true;
    }
}
