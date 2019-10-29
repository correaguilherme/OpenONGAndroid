package com.br.senac.openongandroid.control;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.br.senac.openongandroid.R;
import com.br.senac.openongandroid.dao.ParceiroDeNegocioDao;
import com.br.senac.openongandroid.model.ParceiroDeNegocio;

import java.sql.SQLException;
import java.util.List;

public class MainControl {
    private Activity activity;
    private ListView lvPn;
    private ArrayAdapter<ParceiroDeNegocio> adapterPn;
    private List<ParceiroDeNegocio> listPns;

    private ParceiroDeNegocio pn;
    private ParceiroDeNegocioDao pnDao;


    public MainControl(Activity activity) {
        this.activity = activity;
        pnDao = new ParceiroDeNegocioDao(activity);

        initComponents();
    }

    private void initComponents() {
        lvPn = activity.findViewById(R.id.lvParceiros);
        configListView();
    }

    private void configListView() {

        try {
            listPns = pnDao.getDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        adapterPn = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, listPns);
        lvPn.setAdapter(adapterPn);
        cliqueCurto();
    }

    private void cliqueCurto() {
        lvPn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pn = adapterPn.getItem(position);
                openEditForm(pn);
            }
        });
    }

    private void openEditForm(ParceiroDeNegocio pn) {

    }
}
