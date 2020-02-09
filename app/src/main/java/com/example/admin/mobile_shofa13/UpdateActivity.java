package com.example.admin.mobile_shofa13;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.admin.mobile_shofa13.Config.InitService;
import com.example.admin.mobile_shofa13.Model.PostPutDelFilm;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateActivity extends AppCompatActivity {

    private EditText et_judul, et_genre, et_tahun, et_durasi;
    private Button bt_simpan;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Ubah Data Film");

        et_judul = findViewById(R.id.edit_judul);
        et_genre = findViewById(R.id.edit_genre);
        et_tahun = findViewById(R.id.edit_tahun);
        et_durasi = findViewById(R.id.edit_durasi);
        bt_simpan = findViewById(R.id.btn_simpan);

        Intent i = getIntent();
        Bundle b = i.getBundleExtra("film");

        et_judul.setText(b.getString("judul"));
        et_genre.setText(b.getString("genre"));
        et_tahun.setText(b.getString("tahun"));
        et_durasi.setText(String.valueOf(b.getInt("durasi")));
        id = b.getInt("id");

        bt_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String judul = et_judul.getText().toString();
                String genre = et_genre.getText().toString();
                String tahun = et_tahun.getText().toString();
                String durasi = et_durasi.getText().toString();

                if(judul.isEmpty() || genre.isEmpty() || tahun.isEmpty() || durasi.isEmpty()){
                    Toast.makeText(UpdateActivity.this, "Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }else if(tahun.length() > 4){
                    Toast.makeText(UpdateActivity.this, "panjang tahun tidak boleh lebih dari 4 digit", Toast.LENGTH_SHORT).show();
                }else{
                    Call<PostPutDelFilm> call = InitService.getInstance().putFilm(id,judul,genre,tahun,Integer.parseInt(durasi));
                    call.enqueue(new Callback<PostPutDelFilm>() {
                        @Override
                        public void onResponse(Call<PostPutDelFilm> call, Response<PostPutDelFilm> response) {
                            Toast.makeText(UpdateActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            setResult(2);
                            finish();
                        }

                        @Override
                        public void onFailure(Call<PostPutDelFilm> call, Throwable t) {
                            Log.d("Error", "ubahFilm: " + t.getMessage());
                        }
                    });
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
