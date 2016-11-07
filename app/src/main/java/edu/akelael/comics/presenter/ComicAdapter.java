package edu.akelael.comics.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import edu.akelael.comics.R;
import edu.akelael.comics.model.ComicData;
import edu.akelael.comics.model.ComicData.Data.Comic;
import edu.akelael.comics.view.ComicViewHolder;

import java.util.Collections;
import java.util.List;

public class ComicAdapter extends RecyclerView.Adapter<ComicViewHolder> {
    private List<Comic> comics;

    public ComicAdapter() {
        this.comics = Collections.emptyList();
    }

    @Override
    public ComicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.comic_list_content, parent, false);
        return new ComicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ComicViewHolder holder, int position) {
        final Comic comic = comics.get(position);
        holder.setTitle(comic.getTitle());
        Picasso.with(holder.itemView.getContext())
                .load(comic.getThumbnailURL())
                .fit()
                .centerInside()
                .into(holder.getThumbnail());
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (comics != null) {
            count = comics.size();
        }
        return count;
    }

    public void setComics(List<ComicData.Data.Comic> comics) {
        this.comics = comics;
    }
}
