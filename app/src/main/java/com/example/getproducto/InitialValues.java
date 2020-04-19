package com.example.getproducto;

import android.app.Application;

public class InitialValues extends Application {
    private String idUsuario = "1";

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
}
