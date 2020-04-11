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
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;


public class MonthFragment extends Fragment {
    private RetrofitMain retrofit;
    private ArrayList<TextView> textList = new ArrayList<>();
    TextView txtPlasticoCount,txtPlasticoPuntos,txtPlasticoPeso;
    TextView txtVidrioCount,txtVidrioPuntos,txtVidrioPeso;
    TextView txtMetalesCount,txtMetalesPuntos,txtMetalesPeso;
    TextView txtPapelCartonCount,txtPapelCartonPuntos,txtPapelCartonPeso,txtTest;
    LineChartView lineChartView;
    String[] axisData = {"Lun", "Mar", "Mie", "Jue", "Vie", "Sab", "Dom"};
    int [] yAxisData = {0,0,0,0,0,0,0};
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
        txtTest = getView().findViewById(R.id.txtTest);
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
        textList.add(txtTest);

        yAxisData = retrofit.obtenerBolsasByTime("bolsasMonth/","1",textList);
        lineChartView = getView().findViewById(R.id.chart);
        List yAxisValues = new ArrayList();
        List axisValues = new ArrayList();

        Line line = new Line(yAxisValues).setColor(Color.parseColor("#9C27B0"));
        for (int i = 0; i < axisData.length; i++) {
            axisValues.add(i, new AxisValue(i).setLabel(axisData[i]));
        }

        for (int i = 0; i < yAxisData.length; i++) {
            yAxisValues.add(new PointValue(i, yAxisData[i]));
        }
        List lines = new ArrayList();
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);

        Axis axis = new Axis();
        axis.setValues(axisValues);
        axis.setTextSize(16);
        axis.setTextColor(Color.parseColor("#03A9F4"));
        data.setAxisXBottom(axis);

        Axis yAxis = new Axis();
        yAxis.setName("Cantidad");
        yAxis.setTextColor(Color.parseColor("#03A9F4"));
        yAxis.setTextSize(16);
        data.setAxisYLeft(yAxis);

        lineChartView.setLineChartData(data);
        Viewport viewport = new Viewport(lineChartView.getMaximumViewport());
        viewport.top = 10;
        lineChartView.setMaximumViewport(viewport);
        lineChartView.setCurrentViewport(viewport);

        getView().findViewById(R.id.btnWeek).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                WeekFragment weekFragment = new WeekFragment();
                manager.beginTransaction()
                        .setCustomAnimations(R.anim.enter_left_right,R.anim.exit_left_right,
                                R.anim.enter_right_left,R.anim.exit_left_right).replace(R.id.frameDate,weekFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            }
        });

        getView().findViewById(R.id.btnYear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                YearFragment yearFragment = new YearFragment();
                manager.beginTransaction()
                        .setCustomAnimations(R.anim.enter_right_left,R.anim.exit_right_left,
                                R.anim.enter_left_right,R.anim.exit_left_right).replace(R.id.frameDate,yearFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            }
        });
    }
}
