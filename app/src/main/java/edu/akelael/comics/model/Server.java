package edu.akelael.comics.model;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface Server {
    final static String baseUrl = "http://gateway.marvel.com/v1/public/";
    final static int ID_AMAZING_SPIDERMAN = 1010733;

    @GET("characters/{characterId}/comics")
    Call<ComicData> getCharacter(@Path("characterId") int characterId, @QueryMap Map<String, String> credentials);
}
