package edu.akelael.comics.model;

import java.util.List;

public interface IComicListModelCallbacks {
    void getComicsCallback(List<ComicData.Data.Comic> comicList);
}
