package models;

import com.google.gson.annotations.SerializedName;

public class Sprites {
    //SerializedName: Para que aunque el nombre del atributo no concuerde con con el nombre de
    //la key en la respuesta, GSon pueda vincularlos y crear el objeto
    @SerializedName("back_default")
    private String backDefault;

    @SerializedName("back_shiny")
    private String backShiny;

    @SerializedName("front_default")
    private String frontDefault;

    @SerializedName("front_shiny")
    private String frontShiny;

    public String getBackDefault() {
        return backDefault;
    }

    public void setBackDefault(String backDefault) {
        this.backDefault = backDefault;
    }

    public String getBackShiny() {
        return backShiny;
    }

    public void setBackShiny(String backShiny) {
        this.backShiny = backShiny;
    }

    public String getFrontDefault() {
        return frontDefault;
    }

    public void setFrontDefault(String frontDefault) {
        this.frontDefault = frontDefault;
    }

    public String getFrontShiny() {
        return frontShiny;
    }

    public void setFrontShiny(String frontShiny) {
        this.frontShiny = frontShiny;
    }
}
