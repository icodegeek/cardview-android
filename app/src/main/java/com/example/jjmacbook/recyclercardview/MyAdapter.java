package com.example.jjmacbook.recyclercardview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jjmacbook on 17/3/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    //Lista de películas
    private List<Movie> movies;
    //Layout con el que queremos inflar la vista
    private int layout;
    //Interfaz
    private OnItemClickListener itemClickListener;

    //Constructor Myadapter
    public MyAdapter(List<Movie> movies, int layout, OnItemClickListener listener){
        this.movies = movies;
        this.layout = layout;
        this.itemClickListener = listener;
    }

    //Método que se sobreescribe para inflar la vista con nuestro layout y pasarlo a nuestro consctructor de ViewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Se infla la vista
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        //Se le pasa al viewholder la vista inflada
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    //Método sobreescrito para el volcado de datos
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(movies.get(position), itemClickListener);
    }

    //Método que se sobreescribe para extraer el número de elementos de la lista
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName;
        public ImageView imageViewPoster;

        //Contructor
        public ViewHolder(View v){
            //se le pasa al padre la vista que le ha llegado 'v'
            super(v);

        }

        public void bind(final Movie movie, final OnItemClickListener listener){

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemClick(movie, getAdapterPosition());
                }
            });
        }
    }

    //Se crea una interfaz con un método OnItemClick
    public interface OnItemClickListener {
        void OnItemClick(Movie movie, int position);
    }
}
