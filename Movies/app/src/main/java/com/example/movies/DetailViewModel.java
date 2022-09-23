package com.example.movies;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.movies.data.MovieDao;
import com.example.movies.data.MoviesDatabase;
import com.example.movies.model.Movie;
import com.example.movies.model.Review;
import com.example.movies.model.ReviewResponse;
import com.example.movies.model.Trailer;
import com.example.movies.model.TrailerResponse;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DetailViewModel extends AndroidViewModel {
    private MutableLiveData<List<Trailer>> isTrailer=new MutableLiveData<>();
    private MutableLiveData<List<Review>> isReview=new MutableLiveData<>();
    private CompositeDisposable compositeDisposable=new CompositeDisposable();
    MovieDao movieDao;
    public DetailViewModel(@NonNull Application application) {
        super(application);
        movieDao= MoviesDatabase.getInstance(application).movieDao();
    }

    public LiveData<Movie> getFavouriteMovies(int movieId){
        return movieDao.getFavouriteMovie(movieId);
    }

    public void loadAdd(Movie movie){
        Disposable disposable=movieDao.insertMovie(movie)
                .subscribeOn(Schedulers.io())
                .subscribe();
        compositeDisposable.add(disposable);
    }

    public void loadRemove(int id){
        Disposable disposable=movieDao.remove(id)
                .subscribeOn(Schedulers.io())
                .subscribe();
        compositeDisposable.add(disposable);
    }

    public LiveData<List<Review>> getIsReview() {
        return isReview;
    }

    public LiveData<List<Trailer>> getIsTrailer() {
        return isTrailer;
    }

    public void loadTrailer(int id){
        Disposable disposable=ApiFactory.apiService.loadTrailer(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<TrailerResponse, List<Trailer>>() {
                    @Override
                    public List<Trailer> apply(TrailerResponse trailerResponse) throws Throwable {
                        return trailerResponse.getTrailerList().getTrailerList();
                    }
                })
                .subscribe(new Consumer<List<Trailer>>() {
                    @Override
                    public void accept(List<Trailer> trailers) throws Throwable {
                        isTrailer.setValue(trailers);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d("Movie",throwable.toString());
                    }
                });
        compositeDisposable.add(disposable);
    }

    public void loadReview(int id){
        Disposable disposable=ApiFactory.apiService.loadReview(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<ReviewResponse, List<Review>>() {
                    @Override
                    public List<Review> apply(ReviewResponse reviewResponse) throws Throwable {
                        return reviewResponse.getReviewList();
                    }
                })
                .subscribe(new Consumer<List<Review>>() {
                    @Override
                    public void accept(List<Review> reviews) throws Throwable {
                        isReview.setValue(reviews);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d("Movie",throwable.toString());
                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
