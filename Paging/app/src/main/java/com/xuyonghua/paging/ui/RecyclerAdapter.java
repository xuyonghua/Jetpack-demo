package com.xuyonghua.paging.ui;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xuyonghua.paging.R;
import com.xuyonghua.paging.entity.Repo;

public class RecyclerAdapter extends PagedListAdapter<Repo, RecyclerAdapter.RecyclerViewHolder> {
    private static final String TAG = MainActivity.class.getSimpleName();
    protected RecyclerAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Repo repo = getItem(position);
        if (repo != null) {
            holder.fullName.setText(repo.getFullName());
            holder.description.setText(repo.getDescription());
            holder.star.setText(repo.getStarCount());
            holder.fork.setText(repo.getForkCount());
        }
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView fullName;
        TextView description;
        TextView fork;
        TextView star;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.fullName);
            description = itemView.findViewById(R.id.description);
            fork = itemView.findViewById(R.id.fork);
            star = itemView.findViewById(R.id.star);
        }
    }

    private static DiffUtil.ItemCallback<Repo> DIFF_CALLBACK = new DiffUtil.ItemCallback<Repo>() {
        @Override
        public boolean areItemsTheSame(Repo oldItem, Repo newItem) {
            return oldItem.getFullName().equals(newItem.getFullName());
        }

        @Override
        public boolean areContentsTheSame(Repo oldItem, Repo newItem) {
            return oldItem.equals(newItem);
        }
    };

}
