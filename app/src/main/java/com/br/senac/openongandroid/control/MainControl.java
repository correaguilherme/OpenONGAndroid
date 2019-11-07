package com.br.senac.openongandroid.control;

import android.app.Activity;
import android.content.Intent;

import com.br.senac.openongandroid.view.BeneficiadoListActivity;
import com.br.senac.openongandroid.view.ParceiroListActivity;

public class MainControl {
    private Activity activity;

    public MainControl(Activity activity) {
        this.activity = activity;
        initComponents();
    }

    private void initComponents() {
    }

    public void ParceiroAction() {
        Intent it = new Intent(activity, ParceiroListActivity.class);
        activity.startActivity(it);
    }

    public void BeneficiadoAction() {
        Intent it = new Intent(activity, BeneficiadoListActivity.class);
        activity.startActivity(it);
    }
}
