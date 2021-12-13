package com.example.rentcars;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class CarsShow extends AppCompatActivity {
ListView lv;
FirebaseListAdapter adapter;
FirebaseDatabase database;
DatabaseReference ref;
    private Cars cars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars_show);
        lv = (ListView) findViewById(R.id.listView);
        Query query = FirebaseDatabase.getInstance().getReference().child("Car");
        FirebaseListOptions<Cars> options = new FirebaseListOptions.Builder<Cars>()
                .setLayout(R.layout.cars)
               .setLifecycleOwner(CarsShow.this)
                .setQuery(query,Cars.class).build();
        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                TextView markCar = v.findViewById(R.id.markCar);
                TextView modelCar = v.findViewById(R.id.modelCar);
                TextView numCar = v.findViewById(R.id.numCar);
                TextView colorCar = v.findViewById(R.id.colorCar);
                TextView costCar = v.findViewById(R.id.costCar);
                ImageView image = v.findViewById(R.id.imageView);
                Cars crs = (Cars) model;
                markCar.setText("Марка : " + crs.getMarkCar().toString());
                modelCar.setText("Модель : " + crs.getModelCar().toString());
                numCar.setText("Гос номер : " + crs.getNumCar().toString());
                colorCar.setText("Цвет : " + crs.getColorCar().toString());
                costCar.setText("Стоимость : "+ crs.getCostCar().toString());
                Picasso.get().load(crs.getImage()).into(image);
            }
        };
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cars = new Cars();
                Intent updateDelete = new Intent(CarsShow. this, Cabinet.class);
                Cars c = (Cars) parent.getItemAtPosition(position);
                updateDelete.putExtra("markCar",c.getMarkCar());
                updateDelete.putExtra("modelCar",c.getModelCar());
                updateDelete.putExtra("numCar",c.getNumCar());
                updateDelete.putExtra("key",c.getNumCar());
                updateDelete.putExtra("colorCar",c.getColorCar());
                updateDelete.putExtra("costCar",c.getCostCar());
                cars.setModelCar(updateDelete.getStringExtra("modelCar"));
                cars.setMarkCar(updateDelete.getStringExtra("markCar"));
                cars.setNumCar(updateDelete.getStringExtra("numCar"));
                cars.setColorCar(updateDelete.getStringExtra("colorCar"));
                cars.setCostCar(updateDelete.getStringExtra("costCar"));
                Intent register = getIntent();
                String mEmail = register.getStringExtra("email");
                database = FirebaseDatabase.getInstance();

                ref = database.getReference().child("Клиент").
                        child(mEmail).child("rent");
                ref.push().setValue(cars);
//                ref.child(cars.getNumCar()).setValue(cars);

                startActivity(updateDelete);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
