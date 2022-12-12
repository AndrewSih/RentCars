package com.example.rentcars;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MobilePhoneShowChange extends AppCompatActivity {
    ListView lv;
    FirebaseListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars_show_change);
        lv = findViewById(R.id.listView);
        Query query = FirebaseDatabase.getInstance().getReference().child("mobile");
        FirebaseListOptions<RentObject> options = new FirebaseListOptions.Builder<RentObject>()
                .setLayout(R.layout.cars)
                .setLifecycleOwner(MobilePhoneShowChange.this)
                .setQuery(query,RentObject.class).build();
        adapter = new FirebaseListAdapter(options) {
            @SuppressLint("SetTextI18n")
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                TextView markCar = v.findViewById(R.id.markCar);
                TextView modelCar = v.findViewById(R.id.modelCar);
                TextView numCar = v.findViewById(R.id.numCar);
                TextView colorCar = v.findViewById(R.id.colorCar);
                TextView costCar = v.findViewById(R.id.costCar);
                RentObject crs = (RentObject) model;
                markCar.setText("Консоль : " + crs.getModel());
                modelCar.setText("Описание : " + crs.getDescription());
                numCar.setText("Город : " + crs.getCity());
                colorCar.setText("Телефон : " + crs.getMobileNo());
                costCar.setText("Стоимость : "+ crs.getCost());
            }
        };
        lv.setAdapter(adapter);
        lv.setOnItemClickListener((parent, view, position, id) -> {
            Intent updateDelete = new Intent(MobilePhoneShowChange. this, UpdateDeleteMobile.class);
            RentObject c = (RentObject) parent.getItemAtPosition(position);
            updateDelete.putExtra("model",c.getModel());
            updateDelete.putExtra("description",c.getDescription());
            updateDelete.putExtra("city",c.getCity());
            updateDelete.putExtra("key",c.getModel());
            updateDelete.putExtra("mobileNo",c.getMobileNo());
            updateDelete.putExtra("cost",c.getCost());

            startActivity(updateDelete);
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
        Intent register = new Intent(MobilePhoneShowChange.this,AddMobile.class );
        startActivity(register);
    }
    public void btnExit(View view) {
        Intent register = new Intent(MobilePhoneShowChange.this,CategoryScreen.class );
        startActivity(register);
    }
}
