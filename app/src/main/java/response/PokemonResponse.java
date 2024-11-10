package response;

import java.util.ArrayList;
import java.util.List;

import models.Pokemon;

public class PokemonResponse {
    private Integer count;
    private String next;
    private String previous;

    private static PokemonResponse instance;

    private List<Pokemon> results;

    private PokemonResponse() {
        results = new ArrayList<>();
    }

    public static synchronized PokemonResponse getInstance() {
        if (instance == null) {
            instance = new PokemonResponse(); // Crear la instancia si no existe
        }
        return instance;
    }

    public void inicializar(Integer count, String next, String previous, List<Pokemon> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<Pokemon> getResults() {
        return results;
    }

    public void setResults(List<Pokemon> results) {
        this.results = results;
    }


}
