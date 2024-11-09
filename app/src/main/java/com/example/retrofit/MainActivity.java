package com.example.retrofit;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import apis.apiPokemon;
import response.PokemonResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                    PokemonResponse pokemonData = response.body();
                    Log.i("Pokemon",pokemonData.getResults().get(0).getName());

                }
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable throwable) {

            }
        });
    }
}