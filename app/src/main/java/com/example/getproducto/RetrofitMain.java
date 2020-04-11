package com.example.getproducto;

import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import com.example.getproducto.retrofit.Bolsa;
import com.example.getproducto.retrofit.JsonPlaceHolderApi;
import com.example.getproducto.retrofit.Probolsa;
import com.example.getproducto.retrofit.Producto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitMain {

    private retrofit2.Retrofit retrofit;
    private static final String TAG ="APP";
    private ArrayList<Bolsa> dataset = new ArrayList<>();
    private int plasticoCount,pesoPlastico,puntosPlastico;
    private int vidrioCount,pesoVidrio,puntosVidrio;
    private int papelCartonCount,pesoPapelCarton,puntosPapelCarton;
    private int metalCount,pesoMetal,puntosMetal;
    private int [] datosweek= {0,0,0,0,0,0,0};
    private int [] datosmonth= {0,0,0,0,0,0,0};
    private int [] datosyear= {0,0,0,0,0,0,0,0,0,0,0,0};


    public RetrofitMain(){
        retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("https://recyclerapiresttdp.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void obtenerProductoByBarcode(String barcode, TextView txtNombre,TextView txtPeso,TextView txtAbreviatura){
        JsonPlaceHolderApi jsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi.class);
        Call<Producto> call=jsonPlaceHolderApi.getProductoById("producto/barcode/"+barcode);
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

    public void obtenerDatosProductByBolsa(String usuarioID, ListaProductoByBolsaAdapter listProdBolsasAdapter,ListaProductoByBolsaAdapter.OnNoteListener prodbyBolsa){
        JsonPlaceHolderApi jsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Probolsa>> call=jsonPlaceHolderApi.getProductoByIdBolsa("probolsa/bolsa/"+usuarioID);
        call.enqueue(new Callback<List<Probolsa>>() {
            @Override
            public void onResponse(Call<List<Probolsa>> call, Response<List<Probolsa>> response) {
                if(response.isSuccessful()) {

                    List<Probolsa> probolsas = response.body();
                    ArrayList<Probolsa> listaprodbolsas = (ArrayList) probolsas;
                    listProdBolsasAdapter.adicionarListaCancion(listaprodbolsas,prodbyBolsa);
                }else{
                    Log.e(TAG,"onResponse:" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Probolsa>> call, Throwable t) {
                Log.e(TAG,"onFailure:" + t.getMessage());
            }
        });
    }

    public ArrayList<Bolsa> obtenerDatosBolsas(String usuarioID, ListaBolsaAdapter listBolsasAdapter,ListaBolsaAdapter.OnNoteListener bolsaNoteListener){
        JsonPlaceHolderApi jsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Bolsa>> call=jsonPlaceHolderApi.getBolsasByUsuario("bolsa/usuario/"+usuarioID);
        call.enqueue(new Callback<List<Bolsa>>() {
            @Override
            public void onResponse(Call<List<Bolsa>> call, Response<List<Bolsa>> response) {
                if(response.isSuccessful()) {

                    List<Bolsa> probolsas = response.body();
                    ArrayList<Bolsa> listaprodbolsas = (ArrayList) probolsas;
                    dataset=(ArrayList) listaprodbolsas;
                    listBolsasAdapter.adicionarListaCancion(listaprodbolsas,bolsaNoteListener);
                }else{
                    Log.e(TAG,"onResponse:" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Bolsa>> call, Throwable t) {
                Log.e(TAG,"onFailure:" + t.getMessage());
            }
        });
        return dataset;
    }

    public int [] obtenerBolsasByTime(String urlDate,String usuarioID,ArrayList<TextView> textViewsList){
        plasticoCount=0;
        pesoPlastico=0;
        puntosPlastico=0;
        vidrioCount=0;
        pesoVidrio=0;
        puntosVidrio=0;
        papelCartonCount=0;
        pesoPapelCarton=0;
        puntosPapelCarton=0;
        metalCount=0;
        pesoMetal=0;
        puntosMetal=0;
        JsonPlaceHolderApi jsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Probolsa>> call=jsonPlaceHolderApi.getBolsasByDate("probolsa/"+urlDate+usuarioID);
        call.enqueue(new Callback<List<Probolsa>>() {
            @Override
            public void onResponse(Call<List<Probolsa>> call, Response<List<Probolsa>> response) {
                if(response.isSuccessful()) {

                    List<Probolsa> bolsasbydate = response.body();
                    for(int i=0;i< bolsasbydate.size();i++){
                        if (bolsasbydate.get(i).getBolsa().getRecojoFecha() != null) {

                            if(urlDate.equals("bolsasWeek/") || urlDate.equals("bolsasMonth/")) {
                                Date dia = bolsasbydate.get(i).getBolsa().getRecojoFecha();
                                SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");


                                if (simpleDateformat.format(dia).equals("Monday"))
                                    datosweek[0] += 1;
                                else if (simpleDateformat.format(dia).equals("Tuesday"))
                                    datosweek[1] += 1;
                                else if (simpleDateformat.format(dia).equals("Wednesday"))
                                    datosweek[2] += 1;
                                else if (simpleDateformat.format(dia).equals("Thursday"))
                                    datosweek[3] += 1;
                                else if (simpleDateformat.format(dia).equals("Friday"))
                                    datosweek[4] += 1;
                                else if (simpleDateformat.format(dia).equals("Saturday"))
                                    datosweek[5] += 1;
                                else
                                    datosweek[6] += 1;

                            }else   {
                                Date dia = bolsasbydate.get(i).getBolsa().getRecojoFecha();
                                Calendar cal = Calendar.getInstance();
                                cal.setTime(dia);
                                if (cal.get(Calendar.MONTH) == 1)
                                    datosyear[0] += 1;
                                else if (cal.get(Calendar.MONTH) == 2)
                                    datosyear[1] += 1;
                                else if (cal.get(Calendar.MONTH) == 3)
                                    datosyear[2] += 1;
                                    else if (cal.get(Calendar.MONTH) == 4)
                                    datosyear[3] += 1;
                                else if (cal.get(Calendar.MONTH) == 5)
                                    datosyear[4] += 1;
                                else if (cal.get(Calendar.MONTH) == 6)
                                    datosyear[5] += 1;
                                else if (cal.get(Calendar.MONTH) == 7)
                                    datosyear[6] += 1;
                                else if (cal.get(Calendar.MONTH) == 8)
                                    datosyear[7] += 1;
                                else if (cal.get(Calendar.MONTH) == 9)
                                    datosyear[8] += 1;
                                else if (cal.get(Calendar.MONTH) == 10)
                                    datosyear[9] += 1;
                                else if (cal.get(Calendar.MONTH) == 11)
                                    datosyear[10] += 1;
                                else if (cal.get(Calendar.MONTH) == 12)
                                    datosyear[11] += 1;

                            }

                            if (bolsasbydate.get(i).getProducto().getCategoria().getNombre().equals("Plastico")) {
                                pesoPlastico += bolsasbydate.get(i).getProducto().getPeso();
                                puntosPlastico += bolsasbydate.get(i).getPuntuacion();
                                plasticoCount++;
                                textViewsList.get(0).setText(Integer.toString(plasticoCount));
                                textViewsList.get(4).setText(Integer.toString(pesoPlastico));
                                textViewsList.get(8).setText(Integer.toString(puntosPlastico));
                            } else if (bolsasbydate.get(i).getProducto().getCategoria().getNombre().equals("Vidrio")) {
                                pesoVidrio += bolsasbydate.get(i).getProducto().getPeso();
                                puntosVidrio += bolsasbydate.get(i).getPuntuacion();
                                plasticoCount++;
                                textViewsList.get(1).setText(Integer.toString(plasticoCount));
                                textViewsList.get(5).setText(Integer.toString(pesoVidrio));
                                textViewsList.get(9).setText(Integer.toString(puntosVidrio));
                            } else if (bolsasbydate.get(i).getProducto().getCategoria().getNombre().equals("Papel/Carton")) {
                                pesoPapelCarton += bolsasbydate.get(i).getProducto().getPeso();
                                puntosPapelCarton += bolsasbydate.get(i).getPuntuacion();
                                plasticoCount++;
                                textViewsList.get(2).setText(Integer.toString(plasticoCount));
                                textViewsList.get(6).setText(Integer.toString(pesoPapelCarton));
                                textViewsList.get(10).setText(Integer.toString(puntosPapelCarton));
                            } else if (bolsasbydate.get(i).getProducto().getCategoria().getNombre().equals("Metal")) {
                                pesoMetal += bolsasbydate.get(i).getProducto().getPeso();
                                puntosMetal += bolsasbydate.get(i).getPuntuacion();
                                plasticoCount++;
                                textViewsList.get(3).setText(Integer.toString(plasticoCount));
                                textViewsList.get(7).setText(Integer.toString(pesoMetal));
                                textViewsList.get(11).setText(Integer.toString(puntosMetal));
                            }
                        }


                        }
                }else{
                    Log.e(TAG,"onResponse:" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Probolsa>> call, Throwable t) {
                Log.e(TAG,"onFailure:" + t.getMessage());
            }
        });
        if (urlDate.equals("bolsasWeek/") || urlDate.equals("bolsasMonth/")) {
            return datosweek;
        }
        else
            return datosyear;
    }


}
