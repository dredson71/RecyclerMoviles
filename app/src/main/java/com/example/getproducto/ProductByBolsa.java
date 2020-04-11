package com.example.getproducto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.getproducto.retrofit.Probolsa;

import java.util.ArrayList;

public class ProductByBolsa extends AppCompatActivity implements ListaProductoByBolsaAdapter.OnNoteListener  {
    private static final String TAG ="PRODUCTOS";
    private ArrayList<Probolsa> dataset = new ArrayList<>();
    private RetrofitMain retrofit;
    private RecyclerView recyclerView;
    private ListaProductoByBolsaAdapter listBolsasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_by_bolsa);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerProductos);
        listBolsasAdapter = new ListaProductoByBolsaAdapter(this);
        recyclerView.setAdapter(listBolsasAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        Bundle datos= getIntent().getExtras();

        int position= datos.getInt("posicion");
        retrofit = new RetrofitMain();

        retrofit.obtenerDatosProductByBolsa(String.valueOf(position),listBolsasAdapter,ProductByBolsa.this::oneNoteClick);
    }




    @Override
    public void oneNoteClick(int position) {
    }
}
