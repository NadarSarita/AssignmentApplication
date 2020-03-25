package com.example.assignmentapplication.ui.blog;

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
import com.example.assignmentapplication.ui.blog.model.BlogResponse;

public class BlogResponseAdapter extends PagedListAdapter<BlogResponse, BlogResponseAdapter.BlogResponseViewHolder> {
    public BlogResponseAdapter() {
        super(USER_COMPARATOR);
    }

    @NonNull
    @Override
    public BlogResponseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.blog_list_item, parent, false);
        return new BlogResponseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogResponseViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public class BlogResponseViewHolder extends RecyclerView.ViewHolder {
        private TextView blogTitle;
        private TextView blogBody;

        BlogResponseViewHolder(@NonNull View itemView) {
            super(itemView);
            blogTitle = itemView.findViewById(R.id.blog_title);
            blogBody = itemView.findViewById(R.id.blog_body);
        }

        void bind(BlogResponse blog) {
            if (blog.getName() != null) {
                blogTitle.setText(blog.getName());
            }
            if (blog.getBody() != null) {
                blogBody.setText(blog.getBody());
            }
        }
    }

    private static final DiffUtil.ItemCallback<BlogResponse> USER_COMPARATOR = new DiffUtil.ItemCallback<BlogResponse>() {
        @Override
        public boolean areItemsTheSame(@NonNull BlogResponse oldItem, @NonNull BlogResponse newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull BlogResponse oldItem, @NonNull BlogResponse newItem) {
            return oldItem == newItem;
        }
    };
}
