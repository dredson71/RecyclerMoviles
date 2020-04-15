package com.example.getproducto;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.example.getproducto.retrofit.Bolsa;
import com.example.getproducto.retrofit.Probolsa;

public class ListaBolsaAdapter extends RecyclerView.Adapter<ListaBolsaAdapter.ViewHolder> {

    private ArrayList<Bolsa> dataset;
    private Context context;
    private int countBolsas;
    private Dialog myDialog;
    private int pos;

    public ListaBolsaAdapter(Context context)
    {
        this.context=context;
        dataset= new ArrayList<>();
        countBolsas=0;
        pos = 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent , int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bolsalist,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.item_bolsas.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(),ProductByBolsa.class);
            intent.putExtra("posicion",dataset.get(viewHolder.getAdapterPosition()).getCodigo());
            context.startActivity(intent);
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        countBolsas++;
        Bolsa c = dataset.get(position);
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String recojoFecha = formatter.format(c.getRecojoFecha());
        String creadoFecha = formatter.format(c.getCreadoFecha());
        holder.txt_Bolsa.setText("Bolsa "+Integer.toString(countBolsas));
        holder.txt_RecojoFecha.setText("Creada : "+recojoFecha);
        holder.txt_CreadoFecha.setText("Recogida : "+creadoFecha);

    }

    @Override
    public int getItemCount(){
        return dataset.size();
    }


    public void adicionarListaCancion(ArrayList<Bolsa> listaCancion){
        dataset.addAll(listaCancion);
        notifyDataSetChanged();
    }


    public int getPos() {
        return pos;
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        private RelativeLayout item_bolsas;
        private TextView txt_Bolsa;
        private TextView txt_RecojoFecha;
        private TextView txt_CreadoFecha;
        OnNoteListener onNoteListener;

        public ViewHolder(View itemView  ){
            super(itemView);
            item_bolsas = (RelativeLayout) itemView.findViewById(R.id.item_LastBolsa);
            txt_Bolsa=(TextView) itemView.findViewById(R.id.txtBolsa);
            txt_CreadoFecha=(TextView) itemView.findViewById(R.id.txtCreadoFecha);
            txt_RecojoFecha=(TextView) itemView.findViewById(R.id.txtRecoojoFecha);

        }

    }

    public interface OnNoteListener{
        void oneNoteClick(int position);
    }
}