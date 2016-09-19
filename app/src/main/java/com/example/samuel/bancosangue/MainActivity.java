package com.example.samuel.bancosangue;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtUser;
    private EditText edtPassword;
    private Button btnLogin;
    private Button btnRegister;
    private Banco bd;
    final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUser = (EditText) findViewById(R.id.editText);
        edtPassword = (EditText) findViewById(R.id.editText2);
        btnLogin = (Button) findViewById(R.id.button1);
        btnRegister = (Button) findViewById(R.id.button2);
        bd = new Banco(this);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean ok = bd.validateUser(edtUser.getText().toString(), edtPassword.getText().toString());

                if((edtUser.getText().toString().equals("")) || (edtPassword.getText().toString().equals("")))
                    Toast.makeText(getApplicationContext(), "Preencha todos os dados!", Toast.LENGTH_SHORT).show();

                else if (ok){
                        Toast.makeText(getApplicationContext(), "Usuário já cadastrado!", Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean ok2 = bd.inserirUsuarioBD(edtUser.getText().toString(), edtPassword.getText().toString());
                    if (ok2)
                        Toast.makeText(getApplicationContext(), "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean ok = bd.validateUser(edtUser.getText().toString(), edtPassword.getText().toString());
                if(ok) {
                    Toast.makeText(getApplicationContext(), "Bem vindo!", Toast.LENGTH_SHORT).show();
                    Intent it = new Intent(context, TelaPrincipal.class);
                    startActivity(it);
                }
                else
                    Toast.makeText(getApplicationContext(), "Usuário não cadastrado!", Toast.LENGTH_SHORT).show();

            }
        });


    }
}
