package edu.akelael.comics.model;

import java.util.List;

public interface IComicListModelCallbacks {
    void getComicsCallback(List<Marvel.Data.Comic> comicList);
}
