package edu.akelael.comics.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import edu.akelael.comics.R;

public class ComicViewHolder extends RecyclerView.ViewHolder {
    ImageView thumbnail;
    TextView title;

    public ComicViewHolder(View itemView) {
        super(itemView);
        this.thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
        this.title = (TextView) itemView.findViewById(R.id.title);
    }

    public void setTitle(String text){
        title.setText(text);
    }

    public ImageView getThumbnail(){
        return thumbnail;
    }
}
