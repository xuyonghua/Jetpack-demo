package com.xyh.jetpacktest;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class StudentAdapter extends PagedListAdapter<Word, WordListAdapter.WordViewHolder> {
    private LayoutInflater mInflater;

    protected StudentAdapter(Context context) {
        super(DIFF_CALLBACK);
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public WordListAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item, parent, false);
        return new WordListAdapter.WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WordListAdapter.WordViewHolder holder, int position) {
        Word word = getItem(position);
        if (word != null) {
            holder.wordItemView.setText(word.getWord());
        } else {
            holder.wordItemView.setText("No Word");
        }
    }

    private static DiffUtil.ItemCallback<Word> DIFF_CALLBACK = new DiffUtil.ItemCallback<Word>() {
        @Override
        public boolean areItemsTheSame(Word oldItem, Word newItem) {
            return oldItem.getWord().equals(newItem.getWord());
        }

        @Override
        public boolean areContentsTheSame(Word oldItem, Word newItem) {
            return oldItem.getWord().equals(newItem.getWord());
        }
    };

}
