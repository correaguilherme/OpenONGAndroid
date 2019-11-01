package com.br.senac.openongandroid.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.br.senac.openongandroid.R;
import com.br.senac.openongandroid.control.MainControl;

public class MainActivity extends AppCompatActivity {
    MainControl control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        control = new MainControl(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        control.carregarParceiros();
    }
}
