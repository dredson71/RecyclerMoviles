package com.example.getproducto.retrofit;

import java.util.Date;

public class Bolsa {
    private int codigo;
    private Date creadoFecha;
    private Date recojoFecha;
    private int puntuacion;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getCreadoFecha() {
        return creadoFecha;
    }

    public void setCreadoFecha(Date creadoFecha) {
        this.creadoFecha = creadoFecha;
    }

    public Date getRecojoFecha() {
        return recojoFecha;
    }

    public void setRecojoFecha(Date recojoFecha) {
        this.recojoFecha = recojoFecha;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
}
