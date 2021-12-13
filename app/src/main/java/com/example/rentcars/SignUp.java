package com.example.rentcars;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    private NewClient newclient;

    FirebaseDatabase database;
    DatabaseReference ref;
    EditText email, password, name, surName, patronymic, passport, address, telephone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        email = (EditText)findViewById(R.id. email);
        password = (EditText)findViewById(R.id. password);
        name = (EditText)findViewById(R.id. name);
        surName = (EditText)findViewById(R.id. surName);
        patronymic = (EditText)findViewById(R.id. patronymic);
        passport = (EditText)findViewById(R.id. passport);
        address = (EditText)findViewById(R.id. address);
        telephone = (EditText)findViewById(R.id. telephone);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference().child("Клиент");
        newclient = new NewClient();
    }

    public void btnRegister_Click(View view) {


        newclient.setEmail(email.getText().toString());
        newclient.setPassword(password.getText().toString());
        newclient.setNameC(name.getText().toString());
        newclient.setSurName(surName.getText().toString());
        newclient.setPatronymic(patronymic.getText().toString());
        newclient.setPassport(passport.getText().toString());
        newclient.setAddress(address.getText().toString());
        newclient.setTelephone(telephone.getText().toString());

        ref.child(newclient.getEmail()).setValue(newclient).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
               if( task.isSuccessful()){
                    Toast.makeText(SignUp.this, "Регистрация прошла успешно", Toast.LENGTH_SHORT).show();
                    Intent next = new Intent(SignUp.this, MainActivity.class);
                    startActivity(next);
                }
               else
               {
                   Toast.makeText(SignUp.this, "Ошибка, повторите регистрацию", Toast.LENGTH_SHORT).show();
                   Intent next = new Intent(SignUp.this, SignUp.class);
                   startActivity(next);
               }
            }
        });}}
