package edu.akelael.comics.presenter;

import java.util.List;

import edu.akelael.comics.model.ComicListModel;
import edu.akelael.comics.model.IComicListModel;
import edu.akelael.comics.model.IComicListModelCallbacks;
import edu.akelael.comics.model.ComicData;
import edu.akelael.comics.view.IComicListActivity;

public class ComicListPresenter implements IComicListPresenter, IComicListModelCallbacks {
    private IComicListActivity view;
    private IComicListModel model;

    public ComicListPresenter(IComicListActivity view){
        this.view = view;
        this.model = new ComicListModel();
        model.initializeApiConnection();
    }

    @Override
    public void getComics() {
        model.getComics(this);
    }

    @Override
    public void getComicsCallback(List<ComicData.Data.Comic> comicList) {
        if(comicList == null){
            view.showError();
        }
        else{
            view.setAndShowComics(comicList);
        }
    }
}
