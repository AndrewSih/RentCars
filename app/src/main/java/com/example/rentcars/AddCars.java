package com.example.rentcars;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCars extends AppCompatActivity {
   EditText edModel, edMark, edNumCar, edColor, edCost;
   private Cars cars;
    FirebaseDatabase database;
    DatabaseReference ref;
    private Button btnExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cars);
        init();
    }
    private void init()
    {
        edModel = (EditText)findViewById(R.id.edModel);
        edMark =  (EditText)findViewById(R.id.edMark);
        edNumCar =  (EditText)findViewById(R.id.edNumCar);
        edColor = (EditText) findViewById(R.id.edColor);
        edCost =  (EditText)findViewById(R.id.edCost);
        cars = new Cars();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference().child("Car");
        btnExit = findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCars.this, CarsShowChange.class);
                startActivity(intent);

            }
        });

    }
    public void carSave (View view)
    {
      cars.setModelCar(edModel.getText().toString());
      cars.setMarkCar(edMark.getText().toString());
      cars.setNumCar(edNumCar.getText().toString());
      cars.setColorCar(edColor.getText().toString());
      cars.setCostCar(edCost.getText().toString());
      ref.child(cars.getNumCar()).setValue(cars).addOnCompleteListener(new OnCompleteListener<Void>() {
          @Override
          public void onComplete(@NonNull Task<Void> task) {
              if( task.isSuccessful()){
                  Toast.makeText(AddCars.this, "Авто добавлено", Toast.LENGTH_SHORT).show();
                  Intent next = new Intent(AddCars.this, CarsShow.class);
                  startActivity(next);
              }
              else
              {
                  Toast.makeText(AddCars.this, "Ошибка", Toast.LENGTH_SHORT).show();
                  Intent next = new Intent(AddCars.this, AddCars.class);
                  startActivity(next);
              }
          }
      });


    }


}