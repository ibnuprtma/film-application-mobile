package com.example.admin.mobile_shofa13.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.mobile_shofa13.Config.InitService;
import com.example.admin.mobile_shofa13.Config.UpdateDeleteListener;
import com.example.admin.mobile_shofa13.Model.Film;
import com.example.admin.mobile_shofa13.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.MyViewHolder> {

    private List<Film> list_film;
    private UpdateDeleteListener mListener;
    private Context mContext;

    public FilmAdapter(List<Film> list_film, UpdateDeleteListener mListener, Context mContext) {
        this.list_film = list_film;
        this.mListener = mListener;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_film,parent,false);
        return new FilmAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Film f = list_film.get(position);
        holder.tv_judul.setText(f.getJudul());
        holder.tv_genre.setText(f.getGenre());
        holder.tv_tahun.setText(f.getTahun());
        holder.tv_durasi.setText(f.getDurasi() + " menit");
        Picasso.with(mContext).load(InitService.getUrl()+"images/"+f.getImages()).into(holder.iv_images);
    }

    @Override
    public int getItemCount() {
        return list_film.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_judul, tv_genre, tv_tahun, tv_durasi;
        ImageView iv_images,iv_delete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_judul = itemView.findViewById(R.id.tv_judul);
            tv_genre = itemView.findViewById(R.id.tv_genre);
            tv_tahun = itemView.findViewById(R.id.tv_tahun);
            tv_durasi = itemView.findViewById(R.id.tv_durasi);
            iv_images = itemView.findViewById(R.id.iv_images);
            iv_delete = itemView.findViewById(R.id.iv_delete);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mListener.onUpdate(getAdapterPosition());
                    return false;
                }
            });

            iv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onDelete(getAdapterPosition());
                }
            });
        }
    }
}
