package com.example.getproducto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class TendenciaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tendencia);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        WeekFragment fragment = new WeekFragment();
        transaction.add(R.id.frameDate,fragment);
        transaction.commit();


    }

    public void NextView(View view){
        Intent intent = new Intent(getApplicationContext(),ListBolsas.class);
        startActivity(intent);
    }
}
