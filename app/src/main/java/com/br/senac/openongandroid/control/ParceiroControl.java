package com.br.senac.openongandroid.control;

import android.app.Activity;
import android.content.Intent;
import android.widget.EditText;

import com.br.senac.openongandroid.R;
import com.br.senac.openongandroid.dao.ParceiroDeNegocioDao;
import com.br.senac.openongandroid.model.ParceiroDeNegocio;
import com.br.senac.openongandroid.util.Constantes;
import com.br.senac.openongandroid.view.MainActivity;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpResponse;

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

    public void enviarAction() {
        getDadosForm();
        if (valida(pn)) {

            AsyncHttpClient client = new AsyncHttpClient();
            String URL = "http://10.0.2.2:8084/backend/api/parceirodenegocio/" + pn.getId();
            Gson gson = new Gson();
            String json = gson.toJson(pn);
            RequestParams params = new RequestParams("dado", json);
            client.put(URL, params, new ResponseHandlerInterface() {
                @Override
                public void sendResponseMessage(HttpResponse httpResponse) throws IOException {

                }

                @Override
                public void sendStartMessage() {

                }

                @Override
                public void sendFinishMessage() {

                }

                @Override
                public void sendProgressMessage(long l, long l1) {

                }

                @Override
                public void sendCancelMessage() {

                }

                @Override
                public void sendSuccessMessage(int i, Header[] headers, byte[] bytes) {

                }

                @Override
                public void sendFailureMessage(int i, Header[] headers, byte[] bytes, Throwable throwable) {

                }

                @Override
                public void sendRetryMessage(int i) {

                }

                @Override
                public URI getRequestURI() {
                    return null;
                }

                @Override
                public void setRequestURI(URI uri) {

                }

                @Override
                public Header[] getRequestHeaders() {
                    return new Header[0];
                }

                @Override
                public void setRequestHeaders(Header[] headers) {

                }

                @Override
                public boolean getUseSynchronousMode() {
                    return false;
                }

                @Override
                public void setUseSynchronousMode(boolean b) {

                }

                @Override
                public boolean getUsePoolThread() {
                    return false;
                }

                @Override
                public void setUsePoolThread(boolean b) {

                }

                @Override
                public void onPreProcessResponse(ResponseHandlerInterface responseHandlerInterface, HttpResponse httpResponse) {

                }

                @Override
                public void onPostProcessResponse(ResponseHandlerInterface responseHandlerInterface, HttpResponse httpResponse) {

                }

                @Override
                public Object getTag() {
                    return null;
                }

                @Override
                public void setTag(Object o) {

                }
            });

            try {
                pnDao.getDao().update(pn);
            } catch (SQLException e) {
            }

            Intent it = new Intent(activity, MainActivity.class);

            it.putExtra(Constantes.Parametros.PARCEIRO, pn);
            activity.setResult(activity.RESULT_OK, it);
            activity.finish();
        }
    }

    private boolean valida(ParceiroDeNegocio pn) {
        return true;
    }
}
