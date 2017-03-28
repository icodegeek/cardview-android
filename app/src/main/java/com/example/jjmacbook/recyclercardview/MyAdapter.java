package com.example.jjmacbook.recyclercardview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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
    //Contexto
    private Context context;

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
        //Se obtiene el contexto
        context = parent.getContext();
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

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName;
        public ImageView imageViewPoster;

        //Contructor
        public ViewHolder(View v){
            //Recibe la View completa. Lo pasa al constructor padre y se enlazan referencias UI
            //con nuestras propiedades ViewHolder declarados arriba.
            super(v);
            textViewName = (TextView) itemView.findViewById(R.id.textViewTitle);
            imageViewPoster = (ImageView) itemView.findViewById(R.id.imageViewPoster);
        }

        public void bind(final Movie movie, final OnItemClickListener listener){
            //Se procesan los datos a renderizar
            textViewName.setText(movie.getName());

            //Se trata la imagen con la librería Picasso
            Picasso.with(context).load(movie.getPoster()).fit().into(imageViewPoster);

            //imageViewPoster.setImageResource(movie.getPoster());

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
