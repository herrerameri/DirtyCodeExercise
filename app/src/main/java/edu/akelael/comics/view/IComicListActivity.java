package edu.akelael.comics.view;

import java.util.List;
import edu.akelael.comics.model.Marvel;

public interface IComicListActivity {
    void showError();
    void setAndShowComics(List<Marvel.Data.Comic> comicList);
}