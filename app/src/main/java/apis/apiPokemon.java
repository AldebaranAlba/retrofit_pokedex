package apis;

import response.PokemonResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface apiPokemon {
    @GET("pokemon?limit=10&offset=0")
    Call<PokemonResponse> getPokemon();

}
