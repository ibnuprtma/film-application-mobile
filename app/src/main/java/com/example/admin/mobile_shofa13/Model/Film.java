package com.example.admin.mobile_shofa13.Model;

import com.google.gson.annotations.SerializedName;

public class Film {
    @SerializedName("id")
    private int id;
    @SerializedName("judul")
    private String judul;
    @SerializedName("genre")
    private String genre;
    @SerializedName("tahun")
    private String tahun;
    @SerializedName("durasi")
    private int durasi;
    @SerializedName("images")
    private String images;

    public Film() {
    }

    public Film(int id, String judul, String genre, String tahun, int durasi, String images) {
        this.id = id;
        this.judul = judul;
        this.genre = genre;
        this.tahun = tahun;
        this.durasi = durasi;
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public int getDurasi() {
        return durasi;
    }

    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}
