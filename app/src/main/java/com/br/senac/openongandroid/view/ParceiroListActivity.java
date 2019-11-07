package com.br.senac.openongandroid.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.br.senac.openongandroid.R;
import com.br.senac.openongandroid.control.ParceiroListControl;

public class ParceiroListActivity extends AppCompatActivity {
    ParceiroListControl control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parceiro_list);
        control = new ParceiroListControl(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        control.carregarParceiros();
    }
}
