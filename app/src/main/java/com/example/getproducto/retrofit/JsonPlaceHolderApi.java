package com.example.getproducto.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface JsonPlaceHolderApi {
    @GET
    Call<Producto> getProductoById(@Url String url);

    @GET
    Call<List<Bolsa>> getBolsasByUsuario(@Url String url);

    @GET
    Call<List<Probolsa>> getProductoByIdBolsa(@Url String url);

    @GET
    Call<List<Probolsa>> getBolsasByDate(@Url String url);
}
