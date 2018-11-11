package com.example.dellpc.notetaker;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonrRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignin;
//    private ProgressDialog progressDialog = new ProgressDialog(this);
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonrRegister = (Button) findViewById(R.id.buttonRegister);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewSignin = (TextView) findViewById(R.id.textViewSignin);
        buttonrRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
    }
    private void registerUser(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            Toast.makeText(this,"Enter username or password",Toast.LENGTH_SHORT).show();
            return;
        }
//        progressDialog.setMessage("Registering User ...");
//        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Registerd Successfully",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(MainActivity.this, "Could not register",Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
    @Override
    public void onClick(View view){
        if(view == buttonrRegister){
            registerUser();
        }
        if(view == textViewSignin){
            finish();
            startActivity(new Intent(this,loginUser.class));
        }
    }
}
