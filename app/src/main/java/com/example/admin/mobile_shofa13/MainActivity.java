package com.example.admin.mobile_shofa13;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.admin.mobile_shofa13.Adapter.FilmAdapter;
import com.example.admin.mobile_shofa13.Config.InitService;
import com.example.admin.mobile_shofa13.Config.UpdateDeleteListener;
import com.example.admin.mobile_shofa13.Model.Film;
import com.example.admin.mobile_shofa13.Model.GetFilm;
import com.example.admin.mobile_shofa13.Model.PostPutDelFilm;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements UpdateDeleteListener {

    private List<Film> list_film;
    private RecyclerView rv_film;
    private FilmAdapter fAdapter;
    private FloatingActionButton fab_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv_film = findViewById(R.id.rv_film);
        fab_add = findViewById(R.id.fab_add);

        getFilm();

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,TambahActivity.class);
                startActivityForResult(i,1);
            }
        });
    }

    private void setRecycler(List<Film> list){
        fAdapter = new FilmAdapter(list,this,this);
        RecyclerView.LayoutManager mLayout = new LinearLayoutManager(this);
        rv_film.setLayoutManager(mLayout);
        rv_film.setAdapter(fAdapter);
        fAdapter.notifyDataSetChanged();
    }

    private void getFilm() {
        try{
            Call<GetFilm> call = InitService.getInstance().getFilm();

            call.enqueue(new Callback<GetFilm>() {
                @Override
                public void onResponse(Call<GetFilm> call, Response<GetFilm> response) {
                    if(response.isSuccessful()){
                        list_film = response.body().getList_film();
                        setRecycler(list_film);
                    }
                }

                @Override
                public void onFailure(Call<GetFilm> call, Throwable t) {
                    Log.d("Error", "getFilm: " + t.getMessage());
                }
            });
        }catch (Exception e){
            Log.d("Error", "getFilm: " + e.getMessage());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == 1){
            finish();
            startActivity(getIntent());
        }else
        if(requestCode == 2 && resultCode == 2){
            finish();
            startActivity(getIntent());
        }
    }

    @Override
    public void onUpdate(int position) {
        int id = list_film.get(position).getId();
        String judul = list_film.get(position).getJudul();
        String genre = list_film.get(position).getGenre();
        String tahun = list_film.get(position).getTahun();
        int durasi = list_film.get(position).getDurasi();

        Bundle b = new Bundle();
        b.putInt("id", id);
        b.putString("judul", judul);
        b.putString("genre", genre);
        b.putString("tahun", tahun);
        b.putInt("durasi", durasi);
        Intent i = new Intent(MainActivity.this, UpdateActivity.class);
        i.putExtra("film", b);
        startActivityForResult(i, 2);
    }

    private void showDialogHapus(final int position, String judul) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Yakin ingin menghapus film "+ judul + " ?");
        builder.setCancelable(true);

        builder.setPositiveButton(
                "Ya",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Call<PostPutDelFilm> call = InitService.getInstance().delFilm(list_film.get(position).getId());
                        call.enqueue(new Callback<PostPutDelFilm>() {
                            @Override
                            public void onResponse(Call<PostPutDelFilm> call, Response<PostPutDelFilm> response) {
                                Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                list_film.remove(position);
                                fAdapter.notifyItemRemoved(position);
                                fAdapter.notifyItemRangeChanged(position,list_film.size());
                            }

                            @Override
                            public void onFailure(Call<PostPutDelFilm> call, Throwable t) {
                                Log.d("Error", "hapusFilm: " + t.getMessage());
                            }
                        });
                    }
                });

        builder.setNegativeButton(
                "Tidak",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onDelete(final int position) {
        String judul = list_film.get(position).getJudul();
        showDialogHapus(position,judul);
    }
}
