package com.example.jjmacbook.recyclercardview.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.jjmacbook.recyclercardview.adapters.MyAdapter;
import com.example.jjmacbook.recyclercardview.R;
import com.example.jjmacbook.recyclercardview.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Movie> movies;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movies = this.getAllMovies();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        //Para utilizar el layout como tipo Linear List
        mLayoutManager = new LinearLayoutManager(this);

        //Para utilizar el layout como tipo Grid
        //mLayoutManager = new GridLayoutManager(this, 2);

        mAdapter = new MyAdapter(movies, R.layout.recycler_view_item, new MyAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(Movie movie, int position) {
                //Toast.makeText(MainActivity.this, name + " - " + position, Toast.LENGTH_LONG).show();
                removeMovie(position);
            }
        });

        //Método para establecer que el layout va a tener un tamaño fijo y mejorar así el rendimiento
        mRecyclerView.setHasFixedSize(true);

        //Método para mostrar animación en el recyclerview
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.add_name:
                this.addMovie(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private List<Movie> getAllMovies(){
        return new ArrayList<Movie>(){{
            add(new Movie("logan", R.drawable.logan));
            add(new Movie("Star Wars", R.drawable.starwar));
            add(new Movie("Warcraft", R.drawable.warcraft));
            add(new Movie("ZNation", R.drawable.znation));
        }};
    }

    //Método para añadir nombre desde el option menú
    private void addMovie(int position) {
        movies.add(position, new Movie("New movie " + (++count), R.drawable.madmaxmovie));

        //Se le informa al adaptador de la introducción de datos nuevos
        mAdapter.notifyItemInserted(position);

        mLayoutManager.scrollToPosition(position);
    }

    //Método para borrar nombre desde el option menú
    private void removeMovie(int position){
        movies.remove(position);

        //Se le informa al adaptador de la eliminación de uno de los datos
        mAdapter.notifyItemRemoved(position);
    }
}