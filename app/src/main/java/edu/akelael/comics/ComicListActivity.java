package edu.akelael.comics;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ComicListActivity extends AppCompatActivity {
    private RecyclerView mList;
    private Map<String, String> mMap;
    private Retrofit.Builder retrofitBuilder;
    private Call<Marvel> marvelCall;
    public Retrofit retrofit;
    public Server server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comic_list);
        mList = (RecyclerView) findViewById(R.id.comic_list);
        final ComicAdapter comicAdapter = new ComicAdapter();
        mList.setAdapter(comicAdapter);
        setApiCredentials();
        initializeHttpClientConfig();
        showComics(comicAdapter, mMap, retrofitBuilder);
    }

    private void initializeHttpClientConfig(){
        retrofitBuilder = new Retrofit.Builder();
        retrofitBuilder.baseUrl(server.baseUrl);
        retrofitBuilder.addConverterFactory(GsonConverterFactory.create());
    }

    private void setApiCredentials(){
        mMap = new HashMap<>();
        mMap.put("ts", "ts");
        mMap.put("apikey", "apikey");
        mMap.put("hash", "hash");
    }

    private void showComics(final ComicAdapter comicAdapter, Map<String, String> map, Retrofit.Builder b) {
        retrofit = b.build();
        server = retrofit.create(Server.class);
        marvelCall = server.getCharacter(Server.ID_AMAZING_SPIDERMAN, map);
        marvelCall.enqueue(new Callback<Marvel>() {
            @Override
            public void onResponse(Call<Marvel> call, Response<Marvel> response) {
                if (response.code() == 200) {
                    comicAdapter.setComics(response.body().data.results);
                    comicAdapter.notifyDataSetChanged();
                }
                else{
                    showError();
                }
            }
            @Override
            public void onFailure(Call<Marvel> call, Throwable t) {
                showError();
            }
        });
    }

    private void showError() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast, (ViewGroup) findViewById(R.id.custom_toast_container));
        TextView textToast = (TextView) layout.findViewById(R.id.text);
        LinearLayout containerToast = (LinearLayout) layout.findViewById(R.id.custom_toast_container);
        textToast.setText(getString(R.string.mensaje_error));
        containerToast.setBackgroundColor(Color.RED);
        showToastWithLayout(layout);
    }

    private void showToastWithLayout(View layout) {
        Toast toastError = new Toast(getApplicationContext());
        toastError.setDuration(Toast.LENGTH_LONG);
        toastError.setView(layout);
        toastError.show();
    }
}
