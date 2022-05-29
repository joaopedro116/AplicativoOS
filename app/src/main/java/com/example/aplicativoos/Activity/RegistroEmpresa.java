package com.example.aplicativoos.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.aplicativoos.MainActivity;
import com.example.aplicativoos.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegistroEmpresa extends AppCompatActivity {
    private DocumentReference db = FirebaseFirestore.getInstance().document("usuario");

    /*Variaveis dos componentes da tela de registro de empresa*/
    private EditText edt_nome_empresa_register;
    private EditText edt_email_empresa_register;
    private EditText edt_senha_empresa_register;
    private EditText edt_confirmar_senha_empresa_register;
    private Button btn_registrar_empresa_register;
    private Button btn_login_empresa_register;
    private ProgressBar loginProgressBar_empresa_register;
    private CheckBox ckb_mostrar_senha_empresa_register;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_empresa);

        /*Relacionando a Variavel com o componente*/
        mAuth = FirebaseAuth.getInstance() ;

        edt_nome_empresa_register = findViewById(R.id.edt_nome_empresa_register);
        edt_email_empresa_register = findViewById(R.id.edt_email_empresa_register);
        edt_senha_empresa_register = findViewById(R.id.edt_senha_empresa_register);
        edt_confirmar_senha_empresa_register = findViewById(R.id.edt_confirmar_senha_empresa_register);
        ckb_mostrar_senha_empresa_register = findViewById(R.id.ckb_mostrar_senha_empresa_register);

        btn_login_empresa_register = findViewById(R.id.btn_login_empresa_register);
        btn_registrar_empresa_register = findViewById(R.id.btn_registrar_empresa_register);

        loginProgressBar_empresa_register = findViewById(R.id.loginProgressBar_empresa_register);

        /*Mostrar senha*/
        ckb_mostrar_senha_empresa_register.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    edt_senha_empresa_register.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    edt_confirmar_senha_empresa_register.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    edt_senha_empresa_register.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    edt_confirmar_senha_empresa_register.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        /*Criar empresa*/

    }



    /*metodo abrir tela principal*/
    private void abrirTelaPrincipal(){
        Intent intent = new Intent(RegistroEmpresa.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}