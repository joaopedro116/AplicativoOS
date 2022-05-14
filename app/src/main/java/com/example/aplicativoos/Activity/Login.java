package com.example.aplicativoos.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.aplicativoos.MainActivity;
import com.example.aplicativoos.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    /*Variaveis dos componentes da tela de login*/
    private EditText edt_email;
    private EditText edt_senha;
    private Button btn_login;
    private Button btn_registrar;
    private FirebaseAuth mAuth;
    private ProgressBar loginProgressBar;
    private CheckBox ckb_mostrar_senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*Relacionando a Variavel com o componente*/
        mAuth = FirebaseAuth.getInstance() ;
        edt_email = findViewById(R.id.edt_email);
        edt_senha = findViewById(R.id.edt_senha);
        btn_login = findViewById(R.id.btn_login);
        btn_registrar = findViewById(R.id.btn_registrar);
        loginProgressBar = findViewById(R.id.loginProgressBar);
        ckb_mostrar_senha = findViewById(R.id.ckb_mostrar_senha);

        /*criando metodo ao Clicar*/
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginEmail = edt_email.getText().toString();
                String loginSenha = edt_senha.getText().toString();

                /*verificação de campos vazios*/
                if (!TextUtils.isEmpty(loginEmail) || !TextUtils.isEmpty(loginSenha)){

                    /*iniciar barra de progresso*/
                    loginProgressBar.setVisibility(View.VISIBLE);

                    /*verificação firebase de usuario existente*/
                    mAuth.signInWithEmailAndPassword(loginEmail,loginSenha)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                                /*redirecionamento ou mensagem de erro*/
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()) {
                                        abrirTelaPrincipal();
                                    }else{
                                        String error = task.getException().getMessage();
                                        Toast.makeText(Login.this, ""+error, Toast.LENGTH_SHORT).show();

                                        /*fechando barra de progresso*/
                                        loginProgressBar.setVisibility(View.INVISIBLE);
                                    }
                                }
                            });
                }
            }
        });

        /*chamando tela de cadastro*/
        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Registro.class);
                startActivity(intent);
                finish();
            }
        });

        /*Mostrar senha*/
        ckb_mostrar_senha.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    edt_senha.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    edt_senha.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

    }

    /*metodo abrir tela principal*/
    private void abrirTelaPrincipal() {
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}