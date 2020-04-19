package com.example.getproducto;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.view.LineChartView;


public class YearFragment extends Fragment {
    View view;
    private RetrofitMain retrofit;
    private ArrayList<TextView> textList = new ArrayList<>();
    TextView txtPlasticoCount,txtPlasticoPuntos,txtPlasticoPeso;
    TextView txtVidrioCount,txtVidrioPuntos,txtVidrioPeso;
    TextView txtMetalesCount,txtMetalesPuntos,txtMetalesPeso;
    TextView txtPapelCartonCount,txtPapelCartonPuntos,txtPapelCartonPeso,txtResiduosCount,txtPesoResiduos,txtPuntajeResiduos;
    LineChartView lineChartView;
    String[] axisData = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept",
            "Oct", "Nov", "Dec"};
    int [] yAxisData = {0,0,0,0,0,0,0,0,0,0,0,0};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_year,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        retrofit = new RetrofitMain();
        txtPlasticoCount =view.findViewById(R.id.txtPlasticoCantidad) ;
        txtPlasticoPuntos =view.findViewById(R.id.txtPlasticoPuntos) ;
        txtPlasticoPeso =view.findViewById(R.id.txtPlasticoPeso) ;
        txtVidrioCount =view.findViewById(R.id.txtVidrioCantidad) ;
        txtVidrioPuntos =view.findViewById(R.id.txtVidrioPuntos) ;
        txtVidrioPeso =view.findViewById(R.id.txtVidrioPeso) ;
        txtMetalesCount =view.findViewById(R.id.txtMetalesCantidad) ;
        txtMetalesPuntos =view.findViewById(R.id.txtMetalesPuntos) ;
        txtMetalesPeso =view.findViewById(R.id.txtMetalesPeso);
        txtPapelCartonCount =view.findViewById(R.id.txtPapelCartonCantidad) ;
        txtPapelCartonPuntos =view.findViewById(R.id.txtPapelCartonPuntos) ;
        txtPapelCartonPeso =view.findViewById(R.id.txtPapelCartonPeso) ;
        lineChartView = view.findViewById(R.id.chart);
        txtResiduosCount = getView().findViewById(R.id.txtCantBolsasHoy);
        txtPesoResiduos = getView().findViewById(R.id.txtPesoBolsasHoy);
        txtPuntajeResiduos = getView().findViewById(R.id.txtPtosBolsasHoy);
        textList.add(txtPlasticoCount);
        textList.add(txtVidrioCount);
        textList.add(txtPapelCartonCount);
        textList.add(txtMetalesCount);
        textList.add(txtPlasticoPeso);
        textList.add(txtVidrioPeso);
        textList.add(txtPapelCartonPeso);
        textList.add(txtMetalesPeso);
        textList.add(txtPlasticoPuntos);
        textList.add(txtVidrioPuntos);
        textList.add(txtPapelCartonPuntos);
        textList.add(txtMetalesPuntos);
        textList.add(txtResiduosCount);
        textList.add(txtPesoResiduos);
        textList.add(txtPuntajeResiduos);
        String usuarioID = ((InitialValues)this.getActivity().getApplication()).getIdUsuario();
        retrofit.obtenerBolsasByYear("bolsasYear/",usuarioID,textList,lineChartView);
    }



}
