package com.base.mvvm.main.view.fragment.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.base.mvvm.R;
import com.base.mvvm.main.data.remote.response.Posts;
import java.util.List;

public class ListPostAdapter extends RecyclerView.Adapter<ListPostAdapter.ListPostViewHolder>{
    private List<Posts> list;
    private SetOnClickItem setOnClickItem;

    public ListPostAdapter(List<Posts> list, SetOnClickItem setOnClickItem) {
        this.list = list;
        this.setOnClickItem = setOnClickItem;
    }

    @NonNull
    @Override
    public ListPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_post, parent, false);
        return new ListPostViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ListPostViewHolder holder, int position) {
        Posts posts = list.get(position);
        holder.tvId.setText(posts.getId()+"");
        holder.tvTitle.setText(posts.getTitle());
        holder.tvBody.setText(posts.getBody());

        holder.itemView.setOnClickListener(v -> {
            setOnClickItem.OnclickItem(position);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ListPostViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvTitle, tvBody;

        public ListPostViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvBody = itemView.findViewById(R.id.tv_body);
        }
    }

    public void updateList(List<Posts> listPosts) {
        list.clear();
        list.addAll(listPosts);
        notifyDataSetChanged();
    }

    public interface SetOnClickItem {
        void OnclickItem(int position);
    }

}
