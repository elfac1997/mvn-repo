package com.pojo;

import java.util.Date;

public class Movie {

    private Integer movieId;
    private String movieName;
    private Date releaseTime;
    private String director;
    private String leadActors;
    private String picture;
    private double averating;
    private int numrating;
    private String description;
    private String typeList;

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getLeadActors() {
        return leadActors;
    }

    public void setLeadActors(String leadActors) {
        this.leadActors = leadActors;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public double getAverating() {
        return averating;
    }

    public void setAverating(double averating) {
        this.averating = averating;
    }

    public int getNumrating() {
        return numrating;
    }

    public void setNumrating(int numrating) {
        this.numrating = numrating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeList() {
        return typeList;
    }

    public void setTypeList(String typeList) {
        this.typeList = typeList;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", movieName='" + movieName + '\'' +
                ", releaseTime=" + releaseTime +
                ", director='" + director + '\'' +
                ", leadActors='" + leadActors + '\'' +
                ", picture='" + picture + '\'' +
                ", averating=" + averating +
                ", numrating=" + numrating +
                ", description='" + description + '\'' +
                ", typeList='" + typeList + '\'' +
                '}';
    }
}
