package com.example.getproducto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.getproducto.retrofit.Bolsa;
import com.example.getproducto.retrofit.JsonPlaceHolderApi;
import com.example.getproducto.retrofit.Probolsa;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListBolsas extends AppCompatActivity implements ListaBolsaAdapter.OnNoteListener {
    private static final String TAG ="BOLSAS";
    private ArrayList<Bolsa> dataset = new ArrayList<>();
    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private ListaBolsaAdapter listBolsasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bolsas);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        listBolsasAdapter = new ListaBolsaAdapter(this);
        recyclerView.setAdapter(listBolsasAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://recyclerapiresttdp.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        obtenerDatos("1");

    }


    private void obtenerDatos(String usuarioID){
        JsonPlaceHolderApi jsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Bolsa>> call=jsonPlaceHolderApi.getBolsasByUsuario("bolsa/usuario/"+usuarioID);
        call.enqueue(new Callback<List<Bolsa>>() {
            @Override
            public void onResponse(Call<List<Bolsa>> call, Response<List<Bolsa>> response) {
                if(response.isSuccessful()) {

                    List<Bolsa> probolsas = response.body();
                    ArrayList<Bolsa> listaprodbolsas = (ArrayList) probolsas;
                    dataset=(ArrayList) probolsas;
                    listBolsasAdapter.adicionarListaCancion(listaprodbolsas,ListBolsas.this::oneNoteClick);
                }else{
                    Log.e(TAG,"onResponse:" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Bolsa>> call, Throwable t) {
                Log.e(TAG,"onFailure:" + t.getMessage());
            }
        });
    }

    public void NextView(View view){
        Intent intent = new Intent(getApplicationContext(),TendenciaActivity.class);
        startActivity(intent);
    }

    @Override
    public void oneNoteClick(int position) {
        Intent intent = new Intent(getApplicationContext(),ProductByBolsa.class);
        intent.putExtra("posicion",dataset.get(position).getCodigo());
        startActivity(intent);
    }
}
