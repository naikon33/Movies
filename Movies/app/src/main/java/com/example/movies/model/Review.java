package com.example.movies.model;

import com.google.gson.annotations.SerializedName;

public class Review {
    @SerializedName("author")
    private String author;
    @SerializedName("review")
    private String review;
    @SerializedName("type")
    private String status;

    public Review(String author, String review, String status) {
        this.author = author;
        this.review = review;
        this.status = status;
    }

    public String getAuthor() {
        return author;
    }

    public String getReview() {
        return review;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Review{" +
                "author='" + author + '\'' +
                ", review='" + review + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
