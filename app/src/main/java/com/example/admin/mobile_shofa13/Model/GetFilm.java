package com.example.admin.mobile_shofa13.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetFilm {
    @SerializedName("status")
    private String status;
    @SerializedName("rows")
    private int rows;
    @SerializedName("film")
    private List<Film> list_film;

    public GetFilm() {
    }

    public GetFilm(String status, int rows, List<Film> list_film) {
        this.status = status;
        this.rows = rows;
        this.list_film = list_film;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public List<Film> getList_film() {
        return list_film;
    }

    public void setList_film(List<Film> list_film) {
        this.list_film = list_film;
    }
}
