package com.example.assignmentapplication.ui.author;

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
import com.example.assignmentapplication.ui.author.model.Address;
import com.example.assignmentapplication.ui.author.model.AuthorResponse;

public class AuthorResponseAdapter extends PagedListAdapter<AuthorResponse, AuthorResponseAdapter.AuthorResponseViewHolder> {
    public AuthorResponseAdapter() {
        super(USER_COMPARATOR);
    }

    @NonNull
    @Override
    public AuthorResponseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.author_list_item, parent, false);
        return new AuthorResponseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AuthorResponseViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public class AuthorResponseViewHolder extends RecyclerView.ViewHolder {
        private TextView txtAuthorName, txtAuthorEmail, txtAuthorAddress, txtAuthorWebsite, txtAuthorPhone;

        AuthorResponseViewHolder(@NonNull View itemView) {
            super(itemView);
            txtAuthorName = itemView.findViewById(R.id.txtAuthorName);
            txtAuthorEmail = itemView.findViewById(R.id.txtAuthorEmail);
            txtAuthorAddress = itemView.findViewById(R.id.txtAuthorAddress);
            txtAuthorWebsite = itemView.findViewById(R.id.txtAuthorWebsite);
            txtAuthorPhone = itemView.findViewById(R.id.txtAuthorPhone);
        }

        void bind(AuthorResponse author) {
            if (author.getName() != null) {
                txtAuthorName.setText(author.getName());
            }
            if (author.getEmail() != null) {
                txtAuthorEmail.setText(author.getEmail());
            }
            if (author.getAddress() != null) {
                Address adr = author.getAddress();
                txtAuthorAddress.setText(adr.getSuite() + " " + adr.getStreet() + "\n" + adr.getCity() + "\n" + adr.getZipcode());
            }
            if (author.getWebsite() != null) {
                txtAuthorWebsite.setText(author.getWebsite());
            }
            if (author.getPhone() != null) {
                txtAuthorPhone.setText(author.getPhone());
            }
        }
    }

    private static final DiffUtil.ItemCallback<AuthorResponse> USER_COMPARATOR = new DiffUtil.ItemCallback<AuthorResponse>() {
        @Override
        public boolean areItemsTheSame(@NonNull AuthorResponse oldItem, @NonNull AuthorResponse newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull AuthorResponse oldItem, @NonNull AuthorResponse newItem) {
            return oldItem == newItem;
        }
    };
}
