package com.example.rentcars;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CategoryScreen extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

    }

    public void rentCars(View view) {
        Intent register = new Intent(CategoryScreen.this, CarsShowChange.class);
        Intent i = getIntent();
        String mEmail = i.getStringExtra("email");
        register.putExtra("email", String.valueOf(mEmail));
        startActivity(register);
    }

    public void photoRent(View view) {
        Intent register = new Intent(CategoryScreen.this, PhotoShowChange.class);
        Intent i = getIntent();
        String mEmail = i.getStringExtra("email");
        register.putExtra("email", String.valueOf(mEmail));
        startActivity(register);
    }

    public void psRent(View view) {
        Intent register = new Intent(CategoryScreen.this, PlayStationShowChange.class);
        Intent i = getIntent();
        String mEmail = i.getStringExtra("email");
        register.putExtra("email", String.valueOf(mEmail));
        startActivity(register);
    }

    public void btnMobile(View view) {
        Intent register = new Intent(CategoryScreen.this, MobilePhoneShowChange.class);
        Intent i = getIntent();
        String mEmail = i.getStringExtra("email");
        register.putExtra("email", String.valueOf(mEmail));
        startActivity(register);
    }
    public void btnExit(View view) {
        Intent register = new Intent(CategoryScreen.this, MainActivity.class);
        Intent i = getIntent();
        String mEmail = i.getStringExtra("email");
        register.putExtra("email", String.valueOf(mEmail));
        startActivity(register);
    }
}
