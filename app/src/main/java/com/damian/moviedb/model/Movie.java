package com.damian.moviedb.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable{

    private int voteCount;
    private int id;
    private boolean isVideo;
    private double voteAverage;
    private String title;
    private double popularity;
    private String posterPath;
    private String originalLanguage;
    private String originalTitle;
    private int[] genreIds;
    private String backdropPath;
    private boolean isAdult;
    private String overview;
    private String releaseDate;

    private static final String POSTER_URL = "https://image.tmdb.org/t/p/w500/";

    public Movie(int id, int voteCount, boolean isVideo, double voteAverage, String title,
                 double popularity, String posterPath, String originalLanguage,
                 String originalTitle, int[] genreIds, String backdropPath,
                 boolean isAdult, String overview, String releaseDate) {
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
    }

    public int getVoteCount() {
        return voteCount;
    }

    public int getId() {
        return id;
    }

    public boolean isVideo() {
        return isVideo;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public int[] getGenreIds() {
        return genreIds;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
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
    }

    protected Movie(Parcel in) {
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
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
