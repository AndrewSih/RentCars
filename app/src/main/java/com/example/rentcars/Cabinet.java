package com.example.rentcars;

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
        lv = (ListView) findViewById(R.id.listView);
        Intent i = getIntent();
        String mEmail = i.getStringExtra("email");
        Query query = FirebaseDatabase.getInstance().getReference().child("Клиент").
                child(mEmail).child("rent");

        FirebaseListOptions<Cars> options = new FirebaseListOptions.Builder<Cars>()
                .setLayout(R.layout.cars)
                .setLifecycleOwner(Cabinet.this)
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
        Intent register = new Intent(Cabinet.this,CarsShow.class );
        Intent i = getIntent();
        String mEmail = i.getStringExtra("email");
        register.putExtra("email",String.valueOf(mEmail));
        startActivity(register);
    }

}
