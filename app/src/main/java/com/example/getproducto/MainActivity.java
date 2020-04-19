package com.example.getproducto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private static final String TAG ="PRODUCTO";
    private RetrofitMain retrofit;
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
        retrofit = new RetrofitMain();
        retrofit.obtenerProductoByBarcode("883929669530",txtNombre,txtPeso,txtAbreviatura);
        ((InitialValues)this.getApplication()).setIdUsuario("4");
    }

    public void NextView(View view){
        Intent intent = new Intent(getApplicationContext(),ListBolsas.class);
        startActivity(intent);
    }

}
