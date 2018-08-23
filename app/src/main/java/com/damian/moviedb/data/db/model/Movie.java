package com.damian.moviedb.data.db.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.VisibleForTesting;

import com.damian.moviedb.data.api.model.ApiMovie;

@Entity (tableName = "movie")
public class Movie implements Parcelable {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "vote_count")
    private int voteCount;

    @ColumnInfo(name = "is_video")
    private boolean isVideo;

    @ColumnInfo(name = "vote_average")
    private double voteAverage;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "popularity")
    private double popularity;

    @ColumnInfo(name = "poster_path")
    private String posterPath;

    @ColumnInfo(name = "original_language")
    private String originalLanguage;

    @ColumnInfo(name = "original_title")
    private String originalTitle;

    @ColumnInfo(name = "genre_ids")
    private int[] genreIds;

    @ColumnInfo(name = "backdrop_path")
    private String backdropPath;

    @ColumnInfo(name = "is_adult")
    private boolean isAdult;

    @ColumnInfo(name = "overview")
    private String overview;

    @ColumnInfo(name = "release_date")
    private String releaseDate;

    @ColumnInfo(name = "result_page")
    private int resultPage;

    private static final String POSTER_URL = "https://image.tmdb.org/t/p/w500/";

    public Movie(ApiMovie apiMovie, int resultPage) {
        this.id = apiMovie.getId();
        this.voteCount =  apiMovie.getVoteCount();
        this.isVideo =  apiMovie.isVideo();
        this.voteAverage =  apiMovie.getVoteAverage();
        this.title =  apiMovie.getTitle();
        this.popularity =  apiMovie.getPopularity();
        this.posterPath =  apiMovie.getPosterPath();
        this.originalLanguage =  apiMovie.getOriginalLanguage();
        this.originalTitle =  apiMovie.getOriginalTitle();
        this.genreIds =  apiMovie.getGenreIds();
        this.backdropPath =  apiMovie.getBackdropPath();
        this.isAdult =  apiMovie.isAdult();
        this.overview =  apiMovie.getOverview();
        this.releaseDate =  apiMovie.getReleaseDate();
        this.resultPage = resultPage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public boolean isVideo() {
        return isVideo;
    }

    public void setVideo(boolean video) {
        isVideo = video;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public int[] getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(int[] genreIds) {
        this.genreIds = genreIds;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(boolean adult) {
        isAdult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getResultPage() {
        return resultPage;
    }

    public void setResultPage(int resultPage) {
        this.resultPage = resultPage;
    }

    public String getPosterUrl() {
        return POSTER_URL + posterPath;
    }

    public String getBackDropUrl() {
        return POSTER_URL + backdropPath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(voteCount);
        dest.writeInt((isVideo?1:0));
        dest.writeDouble(voteAverage);
        dest.writeString(title);
        dest.writeDouble(popularity);
        dest.writeString(posterPath);
        dest.writeString(originalLanguage);
        dest.writeString(originalTitle);
        dest.writeIntArray(genreIds);
        dest.writeString(backdropPath);
        dest.writeInt((isAdult?1:0));
        dest.writeString(overview);
        dest.writeString(releaseDate);
        dest.writeInt(resultPage);
    }

    public Movie(Parcel in) {
        this.id = in.readInt();
        this.voteCount = in.readInt();
        this.isVideo = in.readInt()==1?true:false;
        this.voteAverage = in.readDouble();
        this.title = in.readString();
        this.popularity = in.readDouble();
        this.posterPath = in.readString();
        this.originalLanguage = in.readString();
        this.originalTitle = in.readString();
        this.genreIds = in.createIntArray();
        this.backdropPath = in.readString();
        this.isAdult = in.readInt()==1?true:false;
        this.overview = in.readString();
        this.releaseDate = in.readString();
        this.resultPage = in.readInt();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    public Movie(int id, int voteCount, boolean isVideo, double voteAverage, String title,
                 double popularity, String posterPath, String originalLanguage,
                 String originalTitle, int[] genreIds, String backdropPath,
                 boolean isAdult, String overview, String releaseDate, int resultPage) {
        this.id = id;
        this.voteCount = voteCount;
        this.isVideo = isVideo;
        this.voteAverage = voteAverage;
        this.title = title;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.genreIds = genreIds;
        this.backdropPath = backdropPath;
        this.isAdult = isAdult;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.resultPage = resultPage;
    }
}