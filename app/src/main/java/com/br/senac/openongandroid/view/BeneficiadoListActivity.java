package com.br.senac.openongandroid.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.br.senac.openongandroid.R;
import com.br.senac.openongandroid.control.BeneficiadoListControl;

public class BeneficiadoListActivity extends AppCompatActivity {
    BeneficiadoListControl control;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beneficiado_list);
        control = new BeneficiadoListControl(this);
    }

    public void Voltar(View view) {
        control.voltarAction();
    }

    @Override
    protected void onResume() {
        super.onResume();
        control.carregarBeneficiados();
    }
}
