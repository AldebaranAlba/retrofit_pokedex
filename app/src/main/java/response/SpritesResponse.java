package response;

import com.google.gson.annotations.SerializedName;

import models.Sprites;

public class SpritesResponse {
    @SerializedName("sprites")
    private Sprites sprites;

    // Getter y Setter
    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }
}
