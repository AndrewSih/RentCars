package com.example.rentcars;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class Cabinet extends AppCompatActivity {
    ListView lv;
    FirebaseListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cabinet);
        lv = findViewById(R.id.listView);
        Intent i = getIntent();
        String mEmail = i.getStringExtra("email");
        Query query = FirebaseDatabase.getInstance().getReference().child("Клиент").
                child(mEmail).child("rent");

        FirebaseListOptions<RentObject> options = new FirebaseListOptions.Builder<RentObject>()
                .setLayout(R.layout.cars)
                .setLifecycleOwner(Cabinet.this)
                .setQuery(query, RentObject.class).build();
        adapter = new FirebaseListAdapter(options) {
            @SuppressLint("SetTextI18n")
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                TextView markCar = v.findViewById(R.id.markCar);
                TextView modelCar = v.findViewById(R.id.modelCar);
                TextView numCar = v.findViewById(R.id.numCar);
                TextView colorCar = v.findViewById(R.id.colorCar);
                TextView costCar = v.findViewById(R.id.costCar);
                ImageView image = v.findViewById(R.id.imageView);
                RentObject crs = (RentObject) model;
                markCar.setText("Марка : " + crs.getModel());
                modelCar.setText("Описание : " + crs.getDescription());
                numCar.setText("Город : " + crs.getCity());
                colorCar.setText("Номер : " + crs.getMobileNo());
                costCar.setText("Стоимость : " + crs.getCost());
                Picasso.get().load(crs.getImage()).into(image);
            }
        };
        lv.setAdapter(adapter);
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

    public void search(View view) {
        Intent register = new Intent(Cabinet.this, CarsShow.class);
        Intent i = getIntent();
        String mEmail = i.getStringExtra("email");
        register.putExtra("email", String.valueOf(mEmail));
        startActivity(register);
    }

    public void photoRent(View view) {
        Intent register = new Intent(Cabinet.this, PhotoShow.class);
        Intent i = getIntent();
        String mEmail = i.getStringExtra("email");
        register.putExtra("email", String.valueOf(mEmail));
        startActivity(register);
    }

    public void psRent(View view) {
        Intent register = new Intent(Cabinet.this, PlayStationShow.class);
        Intent i = getIntent();
        String mEmail = i.getStringExtra("email");
        register.putExtra("email", String.valueOf(mEmail));
        startActivity(register);
    }
    public void btnExit(View view) {
        Intent register = new Intent(Cabinet.this, MainActivity.class);
        Intent i = getIntent();
        String mEmail = i.getStringExtra("email");
        register.putExtra("email", String.valueOf(mEmail));
        startActivity(register);
    }

}
