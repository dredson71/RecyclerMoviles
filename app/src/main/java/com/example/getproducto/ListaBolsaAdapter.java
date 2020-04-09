package com.example.getproducto;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.example.getproducto.retrofit.Bolsa;
import com.example.getproducto.retrofit.Probolsa;

public class ListaBolsaAdapter extends RecyclerView.Adapter<ListaBolsaAdapter.ViewHolder> {

    private ArrayList<Bolsa> dataset;
    private Context context;
    private OnNoteListener canonNoteListener;

    public ListaBolsaAdapter(Context context)
    {
        this.context=context;
        dataset= new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent , int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bolsalist,parent,false);

        return new ViewHolder(view,canonNoteListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){

        Bolsa c = dataset.get(position);
        holder.txt_Bolsa.setText(Integer.toString(c.getCodigo()));
        holder.txt_RecojoFecha.setText(c.getRecojoFecha().toString());
        holder.txt_CreadoFecha.setText(c.getCreadoFecha().toString());

    }

    @Override
    public int getItemCount(){
        return dataset.size();
    }


    public void adicionarListaCancion(ArrayList<Bolsa> listaCancion,OnNoteListener canonNoteListener){
        dataset.addAll(listaCancion);
        notifyDataSetChanged();
        this.canonNoteListener=canonNoteListener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView txt_Bolsa;
        private TextView txt_RecojoFecha;
        private TextView txt_CreadoFecha;
        OnNoteListener onNoteListener;

        public ViewHolder(View itemView ,OnNoteListener onNoteListener ){
            super(itemView);
            txt_Bolsa=(TextView) itemView.findViewById(R.id.txtBolsa);
            txt_CreadoFecha=(TextView) itemView.findViewById(R.id.txtCreadoFecha);
            txt_RecojoFecha=(TextView) itemView.findViewById(R.id.txtRecoojoFecha);
            this.onNoteListener=onNoteListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.oneNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void oneNoteClick(int position);
    }
}