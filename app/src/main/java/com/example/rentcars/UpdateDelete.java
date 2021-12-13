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

public class UpdateDelete extends AppCompatActivity {
EditText chMark, chModel,  chColor, chCost;
TextView chNum;
DatabaseReference ref;
//TextView key1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        chMark = (EditText) findViewById(R.id.chMark);
        chModel = (EditText) findViewById(R.id.chModel);
        chNum = (TextView) findViewById(R.id.chNum);
        chColor = (EditText) findViewById(R.id.chColor);
        chCost = (EditText) findViewById(R.id.chCost);
       String key= getIntent().getExtras().get("key").toString();
        ref = FirebaseDatabase.getInstance().getReference().child("Car").child(key);
//        key1 = (TextView)findViewById(R.id.key); key1.setText(key);
        chMark.setText(getIntent().getStringExtra("markCar"));
        chModel.setText(getIntent().getStringExtra("modelCar"));
        chNum.setText(getIntent().getStringExtra("numCar"));
        chColor.setText(getIntent().getStringExtra("colorCar"));
        chCost.setText(getIntent().getStringExtra("costCar"));
    }

    public void btnDel(View view) {
ref.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
    @Override
    public void onComplete(@NonNull Task<Void> task) {
        if (task.isSuccessful())
        {
            Toast.makeText(UpdateDelete.this, "Удалено!",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(UpdateDelete.this, CarsShow.class);
            startActivity(i);
        }

    }
});
    }

    public void btnSave(View view) {
ref.addListenerForSingleValueEvent(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        snapshot.getRef().child("modelCar").setValue(chModel.getText().toString());
        snapshot.getRef().child("markCar").setValue(chMark.getText().toString());
        snapshot.getRef().child("numCar").setValue(chNum.getText().toString());
        snapshot.getRef().child("colorCar").setValue(chColor.getText().toString());
        snapshot.getRef().child("costCar").setValue(chCost.getText().toString());
        Toast.makeText(UpdateDelete.this, "Успешно изменено!!"
                ,Toast.LENGTH_SHORT).show();
        Intent i = new Intent(UpdateDelete.this, CarsShow.class);
        startActivity(i);

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});
    }
}