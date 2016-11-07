package edu.akelael.comics.model;

import java.util.HashMap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ComicListModel implements IComicListModel {
    private static Server server;
    private static Retrofit httpClient;
    private static HashMap<String, String> apiRequestContent;
    private final static int RESPONSE_OK = 200;

    @Override
    public void initializeApiConnection(){
        initializeHttpClient();
        initializeRequestContent();
    }

    @Override
    public void getComics(final IComicListModelCallbacks listener){
        server = httpClient.create(Server.class);
        Call<ComicData> marvelCall = server.getCharacter(Server.ID_AMAZING_SPIDERMAN, apiRequestContent);
        marvelCall.enqueue(new Callback<ComicData>() {
            @Override
            public void onResponse(Call<ComicData> call, Response<ComicData> response) {
                if (response.code() == RESPONSE_OK) {
                    listener.getComicsCallback(response.body().data.results);
                }
                else{
                    listener.getComicsCallback(null);
                }
            }
            @Override
            public void onFailure(Call<ComicData> call, Throwable t) {
                listener.getComicsCallback(null);
            }
        });
    }

    private void initializeRequestContent(){
        Retrofit.Builder retrofitBuilder;
        retrofitBuilder = new Retrofit.Builder();
        retrofitBuilder.baseUrl(server.baseUrl);
        retrofitBuilder.addConverterFactory(GsonConverterFactory.create());
        httpClient = retrofitBuilder.build();
    }

    private void initializeHttpClient(){
        apiRequestContent = new HashMap<>();
        apiRequestContent.put("ts", "ts");
        apiRequestContent.put("apikey", "apikey");
        apiRequestContent.put("hash", "hash");
    }
}
