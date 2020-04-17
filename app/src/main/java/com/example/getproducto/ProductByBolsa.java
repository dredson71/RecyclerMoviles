package com.example.getproducto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.getproducto.retrofit.Probolsa;

import java.util.ArrayList;

public class ProductByBolsa extends AppCompatActivity implements ListaProductoByBolsaAdapter.OnNoteListener  {
    private static final String TAG ="PRODUCTOS";
    private ArrayList<Probolsa> dataset = new ArrayList<>();
    private RetrofitMain retrofit;
    private RecyclerView recyclerView;
    private ListaProductoByBolsaAdapter listBolsasAdapter;
    private ArrayList<TextView> textList = new ArrayList<>();
    private TextView txt_RecojoFecha;
    private TextView txt_CreadoFecha;
    private TextView txt_PesoTotal;
    private TextView txt_PuntosTotal;
    private RelativeLayout item_product;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_by_bolsa);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerProductos);
        listBolsasAdapter = new ListaProductoByBolsaAdapter(this);
        recyclerView.setAdapter(listBolsasAdapter);
        recyclerView.setHasFixedSize(true);
        item_product= findViewById(R.id.relativeProductByBolsa);
        txt_CreadoFecha = findViewById(R.id.txtCreadoFecha);
        txt_RecojoFecha = findViewById(R.id.txtRecoojoFecha);
        txt_PesoTotal = findViewById(R.id.txtPesoTotales);
        txt_PuntosTotal = findViewById(R.id.txtPuntosTotales);
        textList.add(txt_CreadoFecha);
        textList.add(txt_RecojoFecha);
        textList.add(txt_PesoTotal);
        textList.add(txt_PuntosTotal);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        Bundle datos= getIntent().getExtras();
        item_product.setAnimation(AnimationUtils.loadAnimation(this,R.anim.fade_scale_2));
        int position= datos.getInt("posicion");
        retrofit = new RetrofitMain();

        retrofit.obtenerDatosProductByBolsa(String.valueOf(position),listBolsasAdapter,ProductByBolsa.this::oneNoteClick,textList);
    }




    @Override
    public void oneNoteClick(int position) {
    }
}
