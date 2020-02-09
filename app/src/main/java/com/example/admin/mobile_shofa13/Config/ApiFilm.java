package com.example.admin.mobile_shofa13.Config;

import com.example.admin.mobile_shofa13.Model.GetFilm;
import com.example.admin.mobile_shofa13.Model.PostPutDelFilm;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiFilm {

    @GET("functions/get.php")
    Call<GetFilm> getFilm();

    @FormUrlEncoded
    @POST("functions/insert.php")
    Call<PostPutDelFilm> postFilm(@Field("judul") String judul,
                                  @Field("genre") String genre,
                                  @Field("tahun") String tahun,
                                  @Field("durasi") int durasi,
                                  @Field("images") String images);

    @FormUrlEncoded
    @POST("functions/update.php")
    Call<PostPutDelFilm> putFilm(@Field("id") int id,
                                 @Field("judul") String judul,
                                 @Field("genre") String genre,
                                 @Field("tahun") String tahun,
                                 @Field("durasi") int durasi);

    @FormUrlEncoded
    @POST("functions/delete.php")
    Call<PostPutDelFilm> delFilm(@Field("id") int id);
}
