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
import com.example.aplicativoos.Model.UserModel;
import com.example.aplicativoos.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registro extends AppCompatActivity {

    /*Variaveis dos componentes da tela de registro*/
    private EditText edt_nome_register;
    private EditText edt_sobrenome_register;
    private EditText edt_email_register;
    private EditText edt_senha_register;
    private EditText edt_confirmar_senha_register;
    private Button btn_registrar_register;
    private Button btn_login_register;
    private ProgressBar loginProgressBar_register;
    private CheckBox ckb_mostrar_senha_register;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        /*Relacionando a Variavel com o componente*/
        mAuth = FirebaseAuth.getInstance() ;
        edt_nome_register = findViewById(R.id.edt_nome_register);
        edt_sobrenome_register = findViewById(R.id.edt_sobrenome_register);
        edt_email_register = findViewById(R.id.edt_email_register);
        edt_senha_register = findViewById(R.id.edt_senha_register);
        edt_confirmar_senha_register = findViewById(R.id.edt_confirmar_senha_register);
        btn_login_register = findViewById(R.id.btn_login_register);
        btn_registrar_register = findViewById(R.id.btn_registrar_register);
        loginProgressBar_register = findViewById(R.id.loginProgressBar_register);
        ckb_mostrar_senha_register = findViewById(R.id.ckb_mostrar_senha_register);

        /*Mostrar senha*/
        ckb_mostrar_senha_register.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    edt_senha_register.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    edt_confirmar_senha_register.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    edt_senha_register.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    edt_confirmar_senha_register.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        /* Efetuando o cadastro */
        btn_registrar_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserModel userModel = new UserModel();

                userModel.setEmail(edt_email_register.getText().toString());
                userModel.setNome(edt_nome_register.getText().toString());
                userModel.setSobrenome(edt_sobrenome_register.getText().toString());
                String senha = edt_senha_register.getText().toString();
                String confirmarsenha = edt_confirmar_senha_register.getText().toString();

                if (!TextUtils.isEmpty(userModel.getNome()) || !TextUtils.isEmpty(userModel.getSobrenome()) || !TextUtils.isEmpty(userModel.getEmail()) || !TextUtils.isEmpty(senha) || !TextUtils.isEmpty(confirmarsenha)){
                    if (senha.equals(confirmarsenha)){
                        loginProgressBar_register.setVisibility(View.VISIBLE);

                        mAuth.createUserWithEmailAndPassword(userModel.getEmail(),senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    userModel.setId(mAuth.getUid());
                                    userModel.salvar();
                                    abrirTelaPrincipal();
                                }else{
                                    String error = task.getException().getMessage();
                                    Toast.makeText(Registro.this, ""+error, Toast.LENGTH_SHORT).show();
                                }
                                loginProgressBar_register.setVisibility(View.INVISIBLE);
                            }
                        });


                    }else{
                        Toast.makeText(Registro.this, "A senha deve ser a mesma em ambos os campos !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        /*redirecionando para a tela de login*/
        btn_login_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registro.this,Login.class);
                startActivity(intent);
                finish();
            }
        });

    }

    /*metodo abrir tela principal*/
    private void abrirTelaPrincipal() {
        Intent intent = new Intent(Registro.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}