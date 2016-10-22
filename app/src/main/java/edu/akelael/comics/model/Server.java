package edu.akelael.comics.model;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface Server {
    String baseUrl = "http://gateway.marvel.com/v1/public/";
    int ID_AMAZING_SPIDERMAN = 1010733;

    @GET("characters/{characterId}/comics")
    Call<Marvel> getCharacter(@Path("characterId") int characterId, @QueryMap Map<String, String> credentials);
}
