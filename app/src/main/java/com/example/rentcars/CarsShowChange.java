package com.example.rentcars;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class CarsShowChange extends AppCompatActivity {
    ListView lv;
    FirebaseListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars_show_change);
        lv = (ListView) findViewById(R.id.listView);
        Query query = FirebaseDatabase.getInstance().getReference().child("Car");
        FirebaseListOptions<Cars> options = new FirebaseListOptions.Builder<Cars>()
                .setLayout(R.layout.cars)
                .setLifecycleOwner(CarsShowChange.this)
                .setQuery(query,Cars.class).build();
        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                TextView markCar = v.findViewById(R.id.markCar);
                TextView modelCar = v.findViewById(R.id.modelCar);
                TextView numCar = v.findViewById(R.id.numCar);
                TextView colorCar = v.findViewById(R.id.colorCar);
                TextView costCar = v.findViewById(R.id.costCar);
                Cars crs = (Cars) model;
                markCar.setText("Марка : " + crs.getMarkCar().toString());
                modelCar.setText("Модель : " + crs.getModelCar().toString());
                numCar.setText("Гос номер : " + crs.getNumCar().toString());
                colorCar.setText("Цвет : " + crs.getColorCar().toString());
                costCar.setText("Стоимость : "+ crs.getCostCar().toString());
            }
        };
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent updateDelete = new Intent(CarsShowChange. this, UpdateDelete.class);
                Cars c = (Cars) parent.getItemAtPosition(position);
                updateDelete.putExtra("markCar",c.getMarkCar());
                updateDelete.putExtra("modelCar",c.getModelCar());
                updateDelete.putExtra("numCar",c.getNumCar());
                updateDelete.putExtra("key",c.getNumCar());
                updateDelete.putExtra("colorCar",c.getColorCar());
                updateDelete.putExtra("costCar",c.getCostCar());

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
    public void btnAddCar(View view) {
        Intent register = new Intent(CarsShowChange.this,AddCars.class );
        startActivity(register);
    }
}

