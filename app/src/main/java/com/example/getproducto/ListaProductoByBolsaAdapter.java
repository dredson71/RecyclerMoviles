package com.example.getproducto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.getproducto.retrofit.Probolsa;

import java.util.ArrayList;

public class ListaProductoByBolsaAdapter extends RecyclerView.Adapter<ListaProductoByBolsaAdapter.ViewHolder> {

    private ArrayList<Probolsa> dataset;
    private Context context;
    private OnNoteListener canonNoteListener;

    public ListaProductoByBolsaAdapter(Context context)
    {
        this.context=context;
        dataset= new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent , int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.productlist,parent,false);

        return new ViewHolder(view,canonNoteListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){

        Probolsa c = dataset.get(position);
        holder.txt_Producto.setText(Integer.toString(c.getCodigo()));
        holder.txt_Contenido.setText(Double.toString(c.getProducto().getContenido()));
        holder.txt_Abreviatura.setText(c.getProducto().getTipo_Contenido().getAbreviatura());

    }

    @Override
    public int getItemCount(){
        return dataset.size();
    }


    public void adicionarListaCancion(ArrayList<Probolsa> listaCancion,OnNoteListener canonNoteListener){
        dataset.addAll(listaCancion);
        notifyDataSetChanged();
        this.canonNoteListener=canonNoteListener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView txt_Producto;
        private TextView txt_Contenido;
        private TextView txt_Abreviatura;
        OnNoteListener onNoteListener;

        public ViewHolder(View itemView ,OnNoteListener onNoteListener ){
            super(itemView);
            txt_Producto=(TextView) itemView.findViewById(R.id.txrProducto_Nombre);
            txt_Contenido=(TextView) itemView.findViewById(R.id.txtCantidadProductoLista);
            txt_Abreviatura=(TextView) itemView.findViewById(R.id.txtAbreviaturaProductoLista);
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
