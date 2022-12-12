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
   EditText model, description, city, mobileNo, cost;
   private RentObject cars;
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
        model = findViewById(R.id.model);
        description = findViewById(R.id.description);
        city = findViewById(R.id.city);
        mobileNo = findViewById(R.id.mobileNo);
        cost = findViewById(R.id.cost);
        cars = new RentObject();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference().child("cars");
        btnExit = findViewById(R.id.btnExit);
        btnExit.setOnClickListener(v -> {
            Intent intent = new Intent(AddCars.this, CarsShowChange.class);
            startActivity(intent);
        });
    }
    public void carSave (View view)
    {
      cars.setModel(model.getText().toString());
      cars.setDescription(description.getText().toString());
      cars.setCity(city.getText().toString());
      cars.setMobileNo(mobileNo.getText().toString());
      cars.setCost(cost.getText().toString());
      ref.child(cars.getModel()).setValue(cars).addOnCompleteListener(task -> {
          if( task.isSuccessful()){
              Toast.makeText(AddCars.this, "Авто добавлено", Toast.LENGTH_SHORT).show();
              Intent next = new Intent(AddCars.this, CarsShowChange.class);
              startActivity(next);
          }
          else
          {
              Toast.makeText(AddCars.this, "Ошибка", Toast.LENGTH_SHORT).show();
              Intent next = new Intent(AddCars.this, AddCars.class);
              startActivity(next);
          }
      });
    }
}