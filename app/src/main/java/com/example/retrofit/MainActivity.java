package com.example.retrofit;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import apis.apiPokemon;
import models.Pokemon;
import response.PokemonResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PokemonAdapter adaper;

    List<Pokemon> pokemonList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.mainRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiPokemon miapi = retrofit.create(apiPokemon.class);

        Call<PokemonResponse> response = miapi.getPokemon();

        //Ascincrono
        response.enqueue(new Callback<PokemonResponse>() {
            @Override
            //Viene en sobresito
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                if(response.isSuccessful()){
                    //Se crea por primera vez
                    //SE INSTANCIA POR RETROFIT. USA LOS PARAMETROS Y METODOS DE LA CLASE PokemonResponse
                    //PERO NO USA EL CONSTRUCTOR DE ESTE, USA UN CONSTRUCTOR ESPECIAL DE RETROFIT. IGNORANDO EL CONSTRUCTOR DE LA CLASE
                    PokemonResponse pokemonData = response.body();

                    //Creo la instancia singleton  y guardo los datos de pokemonData
                    PokemonResponse pokeSingleton = PokemonResponse.getInstance();

                    pokeSingleton.inicializar(pokemonData.getCount(),pokemonData.getNext(),pokemonData.getPrevious(),pokemonData.getResults());

                    for(int i = 0; i < pokeSingleton.getResults().size(); i++){
                        Pokemon poke = new Pokemon();
                        poke.setName(pokeSingleton.getResults().get(i).getName());
                        poke.setUrl(pokeSingleton.getResults().get(i).getUrl());
                        pokemonList.add(poke);
                        Log.i("Pokemon",pokeSingleton.getResults().get(i).getName());
                        Log.i("URL",pokeSingleton.getResults().get(i).getUrl());

//                        Log.i("Pokemon",poke.getName());
//                        Log.i("URL",poke.getUrl());
                        adaper.notifyDataSetChanged();
                    }


//                    for(){
//
//                    }

                }
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable throwable) {

            }
        });

        adaper = new PokemonAdapter(pokemonList);
        recyclerView.setAdapter(adaper);
    }
}