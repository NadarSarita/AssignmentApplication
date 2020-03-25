package com.example.assignmentapplication.ui.article;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignmentapplication.R;
import com.example.assignmentapplication.ui.article.model.ArticleResponse;

public class ArticleResponseAdapter extends PagedListAdapter<ArticleResponse, ArticleResponseAdapter.ArticleResponseViewHolder> {
    public ArticleResponseAdapter() {
        super(USER_COMPARATOR);
    }

    @NonNull
    @Override
    public ArticleResponseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_list_item, parent, false);
        return new ArticleResponseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleResponseViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public class ArticleResponseViewHolder extends RecyclerView.ViewHolder {
        private TextView articleTitle;
        private TextView articleBody, txt_comment;

        ArticleResponseViewHolder(@NonNull View itemView) {
            super(itemView);
            articleTitle = itemView.findViewById(R.id.article_title);
            articleBody = itemView.findViewById(R.id.article_body);
        }

        void bind(ArticleResponse article) {
            if (article.getTitle() != null) {
                articleTitle.setText(article.getTitle());
            }
            if (article.getBody() != null) {
                articleBody.setText(article.getBody());
            }

        }
    }

    private static final DiffUtil.ItemCallback<ArticleResponse> USER_COMPARATOR = new DiffUtil.ItemCallback<ArticleResponse>() {
        @Override
        public boolean areItemsTheSame(@NonNull ArticleResponse oldItem, @NonNull ArticleResponse newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull ArticleResponse oldItem, @NonNull ArticleResponse newItem) {
            return oldItem == newItem;
        }
    };
}
