package apis;

import response.PokemonResponse;
import response.SpritesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface apiPokemon {
    @GET("pokemon?limit=150&offset=0")
    Call<PokemonResponse> getPokemon();

    @GET("pokemon/{number}")
    Call<SpritesResponse> getImage(@Path("number") int id);




}
