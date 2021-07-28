package com.base.mvvm.main.view.fragment.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.base.mvvm.R;
import com.base.mvvm.main.data.remote.model.movies.MovieResponse;
import com.bumptech.glide.Glide;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{
    private Context context;
    private List<MovieResponse> list;
    private SetOnClickItem setOnClickItem;

    public MovieAdapter(Context context, List<MovieResponse> list, SetOnClickItem setOnClickItem) {
        this.context = context;
        this.list = list;
        this.setOnClickItem = setOnClickItem;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_movies, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        MovieResponse movieResponse = list.get(position);
        holder.tvName.setText(movieResponse.getTitle());
        holder.tvContent.setText(movieResponse.getOverview());
        Glide.with(context).load("https://image.tmdb.org/t/p/w500" + movieResponse.getBackdropPath()).into(holder.imgAvatar);

        holder.itemView.setOnClickListener(v -> {
            setOnClickItem.OnclickItem(position);
        });

        if (position == getItemCount() - 1) {
            holder.view7.setVisibility(View.GONE);
        }

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imgAvatar;
        TextView tvName, tvContent;
        View view7;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.img_avatar);
            tvName = itemView.findViewById(R.id.tv_name);
            tvContent = itemView.findViewById(R.id.tv_content);
            view7 = itemView.findViewById(R.id.view7);
        }
    }

    public void updateList(List<MovieResponse> listMovie) {
        list.clear();
        list.addAll(listMovie);
        notifyDataSetChanged();
    }

    public interface SetOnClickItem {
        void OnclickItem(int position);
    }

}
