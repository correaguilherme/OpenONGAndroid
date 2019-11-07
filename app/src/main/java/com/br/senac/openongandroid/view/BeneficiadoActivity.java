package com.br.senac.openongandroid.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.br.senac.openongandroid.R;
import com.br.senac.openongandroid.control.BeneficiadoControl;

public class BeneficiadoActivity extends AppCompatActivity {
    BeneficiadoControl control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beneficiado);
        control = new BeneficiadoControl(this);
    }

    public void enviar(View view) {
        control.salvarAction();
    }

    public void voltar(View view) {
        control.voltarAction();
    }
}
