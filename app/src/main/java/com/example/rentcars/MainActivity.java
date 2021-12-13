package com.example.rentcars;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText email, pass;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);
        ref = FirebaseDatabase.getInstance().getReference().child("Клиент");
    }
    String mPass;
    public void btnLogin_Click(View view) {
        String mEmail = email.getText().toString();
        mPass = pass.getText().toString();

        ref.child(mEmail).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                NewClient newClient = snapshot.getValue(NewClient.class);
               if (mEmail.equals("admin") & mPass.equals("admin")){
                   Toast.makeText(MainActivity.this, "Привет Админ!!",
                        Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(MainActivity.this, CarsShowChange.class);

                    startActivity(i);


                }

                 else if ( mPass.equals(newClient.getPassword())){

                    Intent i = new Intent(MainActivity.this, Cabinet.class);
                    i.putExtra("email", mEmail);

                    startActivity(i);
                   Toast.makeText(MainActivity.this, "Привет " + mEmail,
                           Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Не верный ID/пароль",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }) ;


    }

    public void btnRegister_Click(View view) {
        Intent register = new Intent(MainActivity.this, SignUp.class );
        startActivity(register);
    }

}