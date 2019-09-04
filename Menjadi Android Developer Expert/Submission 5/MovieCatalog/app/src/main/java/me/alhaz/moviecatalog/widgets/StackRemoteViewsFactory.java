package me.alhaz.moviecatalog.widgets;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import com.bumptech.glide.Glide;
import me.alhaz.moviecatalog.R;
import me.alhaz.moviecatalog.repositories.movies.local.MovieLocalRepository;
import me.alhaz.moviecatalog.repositories.movies.local.entities.MovieEntity;
import me.alhaz.moviecatalog.repositories.tvshows.local.TVShowLocalRepository;
import me.alhaz.moviecatalog.repositories.tvshows.local.entities.TVShowEntity;

import java.util.ArrayList;
import java.util.List;

public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private final List<String> mWidgetItems = new ArrayList<>();
    private List<MovieEntity> movies = new ArrayList<MovieEntity>();
    private List<TVShowEntity> tvShows = new ArrayList<TVShowEntity>();
    private final Context mContext;
    private final Application application;
    private final MovieLocalRepository movieLocalRepository;
    private final TVShowLocalRepository tvShowLocalRepository;

    StackRemoteViewsFactory(Context context) {
        mContext = context;
        application = (Application) mContext.getApplicationContext();
        movieLocalRepository = new MovieLocalRepository(application);
        tvShowLocalRepository = new TVShowLocalRepository(application);
    }

    @Override
    public void onCreate() {
        movies = movieLocalRepository.getMoviePosterFavorites();
        tvShows = tvShowLocalRepository.getTVShowPosterFavorites();
    }

    @Override
    public void onDataSetChanged() {
        mWidgetItems.clear();
        if (movies.size() > 0) {
            for(int i = 0; i < movies.size(); i++){
                MovieEntity movie = movies.get(i);
                mWidgetItems.add(movie.getPosterPath());
            }
        }
        if (tvShows.size() > 0) {
            for(int i = 0; i < tvShows.size(); i++){
                TVShowEntity tvShow = tvShows.get(i);
                mWidgetItems.add(tvShow.getPosterPath());
            }
        }
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return mWidgetItems.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.widget_item);
        try {
            Bitmap bitmap = Glide.with(application)
                    .asBitmap()
                    .load("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + mWidgetItems.get(position))
                    .submit(300, 450)
                    .get();
            rv.setImageViewBitmap(R.id.imageView, bitmap);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        Bundle extras = new Bundle();
        extras.putInt(FavoriteWidget.EXTRA_ITEM, position);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);

        rv.setOnClickFillInIntent(R.id.imageView, fillInIntent);
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

}
