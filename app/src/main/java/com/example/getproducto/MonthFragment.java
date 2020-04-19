package com.example.getproducto;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import lecho.lib.hellocharts.view.LineChartView;


public class MonthFragment extends Fragment {
    private RetrofitMain retrofit;
    private ArrayList<TextView> textList = new ArrayList<>();
    TextView txtPlasticoCount,txtPlasticoPuntos,txtPlasticoPeso;
    TextView txtVidrioCount,txtVidrioPuntos,txtVidrioPeso;
    TextView txtMetalesCount,txtMetalesPuntos,txtMetalesPeso;
    TextView txtPapelCartonCount,txtPapelCartonPuntos,txtPapelCartonPeso,txtResiduosCount,txtPesoResiduos,txtPuntajeResiduos;
    LineChartView lineChartView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_month,container,false);    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        retrofit = new RetrofitMain();
        txtPlasticoCount =getView().findViewById(R.id.txtPlasticoCantidad) ;
        txtPlasticoPuntos =getView().findViewById(R.id.txtPlasticoPuntos) ;
        txtPlasticoPeso =getView().findViewById(R.id.txtPlasticoPeso) ;
        txtVidrioCount =getView().findViewById(R.id.txtVidrioCantidad) ;
        txtVidrioPuntos =getView().findViewById(R.id.txtVidrioPuntos) ;
        txtVidrioPeso =getView().findViewById(R.id.txtVidrioPeso) ;
        txtMetalesCount =getView().findViewById(R.id.txtMetalesCantidad) ;
        txtMetalesPuntos =getView().findViewById(R.id.txtMetalesPuntos) ;
        txtMetalesPeso =getView().findViewById(R.id.txtMetalesPeso);
        txtPapelCartonCount =getView().findViewById(R.id.txtPapelCartonCantidad) ;
        txtPapelCartonPuntos =getView().findViewById(R.id.txtPapelCartonPuntos) ;
        txtPapelCartonPeso =getView().findViewById(R.id.txtPapelCartonPeso) ;
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
        lineChartView = getView().findViewById(R.id.chart);
        String usuarioID = ((InitialValues)this.getActivity().getApplication()).getIdUsuario();
        retrofit.obtenerBolsasByMonthorWeek("bolsasMonth/",usuarioID,textList,lineChartView);

    }
}
