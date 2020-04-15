package com.example.getproducto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;


public class ListBolsas extends AppCompatActivity  {
    private ViewPager viewPager;
    private ViewTrendAdapter adapter;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RetrofitMain retrofitMain;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bolsas);

        tabLayout = (TabLayout) findViewById(R.id.tabLayoutTendencia);
        viewPager = findViewById(R.id.viewPagerTendencia);
        adapter = new ViewTrendAdapter(getSupportFragmentManager());

        adapter.AddFragment(new BolsasUserFragment(),"Registro");
        adapter.AddFragment(new TendenciaActivity(),"Tendencia");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);




    }

}
