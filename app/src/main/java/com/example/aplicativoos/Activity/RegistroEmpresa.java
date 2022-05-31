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

import java.util.HashMap;
import java.util.Map;

public class RegistroEmpresa extends AppCompatActivity {

    public static final Object EMPRESA = "nome";
    private static final Object EMAIL = "email";
    private static final Object SENHA = "password";
    private static final Object CNPJ = "cnpj";

    private DocumentReference mDocRef = FirebaseFirestore.getInstance().document("company");

//    FirebaseFirestore db = FirebaseFirestore.getInstance();

    /*Variaveis dos componentes da tela de registro de empresa*/
//    private EditText edt_nome_empresa_register;
//    private EditText edt_email_empresa_register;
//    private EditText edt_senha_empresa_register;
//    private EditText edt_confirmar_senha_empresa_register;

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
//        mAuth = FirebaseAuth.getInstance() ;
//        edt_nome_empresa_register = findViewById(R.id.edt_nome_empresa_register);
//        edt_email_empresa_register = findViewById(R.id.edt_email_empresa_register);
//        edt_senha_empresa_register = findViewById(R.id.edt_senha_empresa_register);
//        edt_confirmar_senha_empresa_register = findViewById(R.id.edt_confirmar_senha_empresa_register);
//        btn_login_empresa_register = findViewById(R.id.btn_login_empresa_register);
//        btn_registrar_empresa_register = findViewById(R.id.btn_registrar_empresa_register);
//        loginProgressBar_empresa_register = findViewById(R.id.loginProgressBar_empresa_register);
//        ckb_mostrar_senha_empresa_register = findViewById(R.id.ckb_mostrar_senha_empresa_register);
//
//        /*Mostrar senha*/
//        ckb_mostrar_senha_empresa_register.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (b){
//                    edt_senha_empresa_register.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                    edt_confirmar_senha_empresa_register.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                }else{
//                    edt_senha_empresa_register.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                    edt_confirmar_senha_empresa_register.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                }
//            }
//        });
    }

//    Aqui será criado o registro da empresa
    public void createEmpresa(View view){
        EditText empresaView = findViewById(R.id.edt_nome_empresa_register);
        EditText emailView = findViewById(R.id.edt_email_empresa_register);
        EditText senhaView = findViewById(R.id.edt_senha_empresa_register);
        EditText confirmaSenhaView = findViewById(R.id.edt_confirmar_senha_empresa_register);
        EditText cnpjView = findViewById(R.id.edt_cnpj_empresa_register);

        String empresaText = empresaView.getText().toString();
        String emailText = emailView.getText().toString();
        String senhaText = senhaView.getText().toString();
        String confirmaSenhaText = confirmaSenhaView.getText().toString();
        String cnpjText = cnpjView.getText().toString();

//        Valida se todos os campos estão preenchidos
        if(empresaText.isEmpty() || emailText.isEmpty() || senhaText.isEmpty() || confirmaSenhaText.isEmpty() || cnpjText.isEmpty()){
            Map<String, Object> dataToSave = new HashMap<>();

            dataToSave.put((String) EMPRESA, empresaText);
            dataToSave.put((String) EMAIL, emailText);
            dataToSave.put((String) SENHA, senhaText);
            dataToSave.put((String) CNPJ, cnpjText);
        }
    }


    /*metodo abrir tela principal*/
    private void abrirTelaPrincipal(){
        Intent intent = new Intent(RegistroEmpresa.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}