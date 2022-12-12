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
        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        ref = FirebaseDatabase.getInstance().getReference().child("Клиент");
    }

    String mPass;
    String mEmail;

    public void btnLogin_Click(View view) {
        mEmail = email.getText().toString();
        mPass = pass.getText().toString();

        ref.child(mEmail).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                NewClient newClient = snapshot.getValue(NewClient.class);
                try {
                    if (mEmail.equals("admin") & mPass.equals("admin")) {
                        Toast.makeText(MainActivity.this, "Привет Админ!!",
                                Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(MainActivity.this, CategoryScreen.class);

                        startActivity(i);


                    } else if (mEmail.equals(newClient.getEmail()) && mPass.equals(newClient.getPassword())) {
                        /*Toast.makeText(MainActivity.this, "Привет " + mEmail,
                                Toast.LENGTH_SHORT).show();*/
                        Intent i = new Intent(MainActivity.this, Cabinet.class);
                        i.putExtra("email", mEmail);

                        startActivity(i);

                    }
                } catch (Exception exception) {
                    Intent i = new Intent(MainActivity.this, MainActivity.class);
                    Toast.makeText(MainActivity.this, "Не верный ID/пароль",
                            Toast.LENGTH_SHORT).show();
                    startActivity(i);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                Toast.makeText(MainActivity.this, "Не верный ID/пароль",
                        Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });
    }

    public void btnRegister_Click(View view) {
        Intent register = new Intent(MainActivity.this, SignUp.class);
        startActivity(register);
    }

}