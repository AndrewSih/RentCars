package com.example.rentcars;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateDeletePhoto extends AppCompatActivity {
    EditText city, description,  mobileNo, cost;
    TextView model;
    DatabaseReference ref;
//TextView key1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        city = findViewById(R.id.city);
        description = findViewById(R.id.description);
        model = findViewById(R.id.model);
        mobileNo = findViewById(R.id.mobileNo);
        cost = findViewById(R.id.cost);
        String key= getIntent().getExtras().get("key").toString();
        ref = FirebaseDatabase.getInstance().getReference().child("Photo").child(key);
        model.setText(getIntent().getStringExtra("model"));
        description.setText(getIntent().getStringExtra("description"));
        city.setText(getIntent().getStringExtra("city"));
        mobileNo.setText(getIntent().getStringExtra("mobileNo"));
        cost.setText(getIntent().getStringExtra("cost"));
    }

    public void btnDel(View view) {
        ref.removeValue().addOnCompleteListener(task -> {
            if (task.isSuccessful())
            {
                Toast.makeText(UpdateDeletePhoto.this, "Удалено!",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdateDeletePhoto.this, PhotoShowChange.class);
                startActivity(i);
            }

        });
    }

    public void btnSave(View view) {
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                snapshot.getRef().child("model").setValue(model.getText().toString());
                snapshot.getRef().child("description").setValue(description.getText().toString());
                snapshot.getRef().child("city").setValue(city.getText().toString());
                snapshot.getRef().child("mobileNo").setValue(mobileNo.getText().toString());
                snapshot.getRef().child("cost").setValue(cost.getText().toString());
                Toast.makeText(UpdateDeletePhoto.this, "Успешно изменено!!"
                        ,Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdateDeletePhoto.this, PhotoShowChange.class);
                startActivity(i);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
