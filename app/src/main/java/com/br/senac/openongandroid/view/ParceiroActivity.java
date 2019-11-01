package com.br.senac.openongandroid.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.br.senac.openongandroid.R;
import com.br.senac.openongandroid.control.ParceiroControl;

public class ParceiroActivity extends AppCompatActivity {
    ParceiroControl control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parceiro);
        control = new ParceiroControl(this);
    }

    public void enviar(View view) {
        control.salvarAction();
    }

    public void voltar(View view) {
        control.voltarAction();
    }
}
