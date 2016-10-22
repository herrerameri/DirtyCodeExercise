package edu.akelael.comics.model;

import java.util.HashMap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ComicListModel implements IComicListModel {
    Server server;
    Retrofit retrofit;
    HashMap<String, String> mMap;

    @Override
    public void initializeApiConnection(){
        Retrofit.Builder retrofitBuilder;
        retrofitBuilder = new Retrofit.Builder();
        retrofitBuilder.baseUrl(server.baseUrl);
        retrofitBuilder.addConverterFactory(GsonConverterFactory.create());
        retrofit = retrofitBuilder.build();
        mMap = new HashMap<>();
        mMap.put("ts", "ts");
        mMap.put("apikey", "apikey");
        mMap.put("hash", "hash");
    }

    @Override
    public void getComics(final IComicListModelCallbacks listener){
        server = retrofit.create(Server.class);
        Call<Marvel> marvelCall = server.getCharacter(Server.ID_AMAZING_SPIDERMAN, mMap);
        marvelCall.enqueue(new Callback<Marvel>() {
            @Override
            public void onResponse(Call<Marvel> call, Response<Marvel> response) {
                if (response.code() == 200) {
                    listener.getComicsCallback(response.body().data.results);
                }
                else{
                    listener.getComicsCallback(null);
                }
            }
            @Override
            public void onFailure(Call<Marvel> call, Throwable t) {
                listener.getComicsCallback(null);
            }
        });
    }
}
