package com.damian.moviedb.data.api;

import android.support.annotation.VisibleForTesting;

import com.google.gson.annotations.SerializedName;

public class ApiMovieData {

    @SerializedName("vote_count")
    private int voteCount;

    @SerializedName("id")
    private int id;

    @SerializedName("video")
    private boolean isVideo;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("title")
    private String title;

    @SerializedName("popularity")
    private double popularity;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("genre_ids")
    private int[] genreIds;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("adult")
    private boolean isAdult;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private String releaseDate;

    public int getId() {
        return id;
    }

    public int getVoteCount() {
        return voteCount;
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

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    public  ApiMovieData(int id, int voteCount, boolean isVideo, double voteAverage, String title,
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
}
