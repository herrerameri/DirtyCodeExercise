package edu.akelael.comics.view;

import java.util.List;
import edu.akelael.comics.model.ComicData;

public interface IComicListActivity {
    void showError();
    void setAndShowComics(List<ComicData.Data.Comic> comicList);
}