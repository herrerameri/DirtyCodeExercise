package edu.akelael.comics.model;

public interface IComicListModel {
    void initializeApiConnection();
    void getComics(IComicListModelCallbacks listener);
}
