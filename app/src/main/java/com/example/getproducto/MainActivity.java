package com.example.getproducto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.getproducto.retrofit.JsonPlaceHolderApi;
import com.example.getproducto.retrofit.Producto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="PRODUCTO";
    private Retrofit retrofit;
    private TextView txtNombre;
    private TextView txtPeso;
    private TextView txtAbreviatura;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtNombre = findViewById(R.id.txtNombre);
        txtPeso = findViewById(R.id.txtPeso);
        txtAbreviatura = findViewById(R.id.txtAbreviatura);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://recyclerapiresttdp.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //ACA SE INDICARIA EL BARCODE EN VEZ DE UNO
        obtenerDatos("883929552351");
    }

    public void NextView(View view){
        Intent intent = new Intent(getApplicationContext(),ListBolsas.class);
        startActivity(intent);
    }

    private void obtenerDatos(String position){
        JsonPlaceHolderApi jsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi.class);
        Call<Producto> call=jsonPlaceHolderApi.getProductoById("producto/barcode/"+position);
        call.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                if(response.isSuccessful()) {

                    Producto productos = response.body();
                    String nombre=productos.getNombre();
                    String barcode=productos.getBarcode();
                    Double peso=productos.getPeso();
                    Double contenido=productos.getContenido();
                    String categoria_nombre = productos.getCategoria().getNombre();
                    String abreviatura = productos.getTipo_Contenido().getAbreviatura();
                    txtNombre.setText(nombre);
                    txtPeso.setText(peso.toString());
                    txtAbreviatura.setText(abreviatura);

                }else{
                    Log.e(TAG,"onResponse:" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {
                Log.e(TAG,"onFailure:" + t.getMessage());
            }
        });
    }
}
