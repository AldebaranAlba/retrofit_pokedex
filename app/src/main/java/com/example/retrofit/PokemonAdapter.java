package com.example.retrofit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.zip.Inflater;

import apis.apiPokemon;
import models.Pokemon;
import response.PokemonResponse;
import response.SpritesResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> {
    private  List<Pokemon> pokemonList;

    public PokemonAdapter(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reciclador,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nombrePokemon.setText(pokemonList.get(position).getName());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiPokemon miapi = retrofit.create(apiPokemon.class);
//        int cont = 0;
//        for(int i = 0; i <= pokemonList.size(); i++){
//
//        }

        Call<SpritesResponse> response = miapi.getImage(position+1);
        response.enqueue(new Callback<SpritesResponse>() {
            @Override
            public void onResponse(Call<SpritesResponse> call, Response<SpritesResponse> response) {
                if(response.isSuccessful()){
                    SpritesResponse spritesData = response.body();
                    Picasso.get().load(spritesData.getSprites().getFrontDefault()).into(holder.imagenPokemon);

                }


            }

            @Override
            public void onFailure(Call<SpritesResponse> call, Throwable throwable) {

            }

        });


//        holder.setPosition(position);
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombrePokemon;
        ImageView imagenPokemon;
//        int position;
//
//        public void setPosition(int position){
//            this.position = position;
//        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombrePokemon = itemView.findViewById(R.id.nombrePokemon);
            imagenPokemon = itemView.findViewById(R.id.imageView);
        }
    }
}
