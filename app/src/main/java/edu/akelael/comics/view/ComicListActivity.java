package edu.akelael.comics.view;

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

import java.util.List;

import edu.akelael.comics.R;
import edu.akelael.comics.model.Marvel;
import edu.akelael.comics.presenter.ComicAdapter;
import edu.akelael.comics.presenter.ComicListPresenter;
import edu.akelael.comics.presenter.IComicListPresenter;

public class ComicListActivity extends AppCompatActivity implements IComicListActivity {
    private RecyclerView recyclerView;
    private IComicListPresenter presenter;
    private ComicAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comic_list);
        recyclerView = (RecyclerView) findViewById(R.id.comic_list);
        presenter = new ComicListPresenter(this);
        adapter = new ComicAdapter();
        recyclerView.setAdapter(adapter);
        presenter.getComics();
    }

    @Override
    public void showError() {
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

    @Override
    public void setAndShowComics(List<Marvel.Data.Comic> comicList) {
        adapter.setComics(comicList);
        adapter.notifyDataSetChanged();
    }
}