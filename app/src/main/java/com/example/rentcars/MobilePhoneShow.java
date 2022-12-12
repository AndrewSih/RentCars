package com.example.rentcars;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class MobilePhoneShow extends AppCompatActivity {

    ListView lv;
    FirebaseListAdapter adapter;
    FirebaseDatabase database;
    DatabaseReference ref;
    private RentObject cars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_object_show);
        lv = findViewById(R.id.listView);
        Query query = FirebaseDatabase.getInstance().getReference().child("mobile");
        FirebaseListOptions<RentObject> options = new FirebaseListOptions.Builder<RentObject>()
                .setLayout(R.layout.rent_object)
                .setLifecycleOwner(MobilePhoneShow.this)
                .setQuery(query, RentObject.class).build();
        adapter = new FirebaseListAdapter(options) {
            @SuppressLint("SetTextI18n")
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                TextView modelRent = v.findViewById(R.id.model);
                TextView description = v.findViewById(R.id.description);
                TextView city = v.findViewById(R.id.city);
                TextView mobileNo = v.findViewById(R.id.mobileNo);
                TextView cost = v.findViewById(R.id.cost);
                ImageView image = v.findViewById(R.id.imageView);
                RentObject crs = (RentObject) model;
                modelRent.setText("Марка : " + crs.getModel());
                description.setText("Описание : " + crs.getDescription());
                city.setText("Город : " + crs.getCity());
                mobileNo.setText("Номер : " + crs.getMobileNo());
                cost.setText("Стоимость : " + crs.getCost());
                Picasso.get().load(crs.getImage()).into(image);
            }
        };
        lv.setAdapter(adapter);
        lv.setOnItemClickListener((parent, view, position, id) -> {
            cars = new RentObject();
            Intent updateDelete = new Intent(MobilePhoneShow.this, Cabinet.class);
            RentObject c = (RentObject) parent.getItemAtPosition(position);
            updateDelete.putExtra("model", c.getModel());
            updateDelete.putExtra("description", c.getDescription());
            updateDelete.putExtra("city", c.getCity());
            updateDelete.putExtra("key", c.getModel());
            updateDelete.putExtra("mobileNo", c.getMobileNo());
            updateDelete.putExtra("cost", c.getCost());
            cars.setModel(updateDelete.getStringExtra("model"));
            cars.setDescription(updateDelete.getStringExtra("description"));
            cars.setCity(updateDelete.getStringExtra("city"));
            cars.setMobileNo(updateDelete.getStringExtra("mobileNo"));
            cars.setCost(updateDelete.getStringExtra("cost"));
            Intent register = getIntent();
            String mEmail = register.getStringExtra("email");
            database = FirebaseDatabase.getInstance();

            ref = database.getReference().child("Клиент").
                    child(mEmail).child("rent");
            ref.push().setValue(cars);

            Toast.makeText(MobilePhoneShow.this, "Успешно добавлено!",
                    Toast.LENGTH_SHORT).show();
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
}
