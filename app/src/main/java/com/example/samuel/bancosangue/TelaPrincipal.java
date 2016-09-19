package com.example.samuel.bancosangue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class TelaPrincipal extends AppCompatActivity {

    private EditText edtHospital;
    private EditText edtPaciente;
    private Spinner edtTipoSangue;
    private Button btnCadastrar;
    private Button btnVerLista;


    private BancoTela bd;
    private String[] arraySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        edtHospital = (EditText) findViewById(R.id.editText);
        edtPaciente = (EditText) findViewById(R.id.editText2);
        edtTipoSangue = (Spinner) findViewById(R.id.spinner);
        btnCadastrar = (Button) findViewById(R.id.button);
        btnVerLista = (Button) findViewById(R.id.button2);
        bd = new BancoTela(this);



        this.arraySpinner = new String[] {
                "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"
        };
        Spinner s = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        s.setAdapter(adapter);
        s.setDropDownHorizontalOffset(android.R.layout.simple_spinner_dropdown_item);



        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((edtHospital.getText().toString().equals("")) || (edtPaciente.getText().toString().equals("")))
                    Toast.makeText(getApplicationContext(), "Preencha todos os dados!", Toast.LENGTH_SHORT).show();
                else
                {
                    boolean ok = bd.inserirCadastroBD(edtHospital.getText().toString(), edtPaciente.getText().toString(), edtTipoSangue.getSelectedItem().toString());
                    if (ok)
                        Toast.makeText(getApplicationContext(), "Inserido com sucesso!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnVerLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent it = new Intent(TelaPrincipal.this, PedidosCadastrados.class);
                    startActivity(it);

            }
        });

    }
}
