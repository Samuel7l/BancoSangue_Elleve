package com.example.samuel.bancosangue;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PedidosCadastrados extends AppCompatActivity {

    private ListView listaCadastros;
    private ArrayAdapter<String> usuariosAdapter;
    private BancoTela bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos_cadastrados);

        bd = new BancoTela(this);
        listaCadastros = (ListView) findViewById(R.id.listViewCadastros);
        showLista();


        listaCadastros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
    }

    private void showLista(){
        usuariosAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bd.getListaCadastrosBD());
        listaCadastros.setAdapter(usuariosAdapter);

    }
}
