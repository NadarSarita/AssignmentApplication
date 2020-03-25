package com.example.assignmentapplication.ui.author;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignmentapplication.R;
import com.example.assignmentapplication.ui.author.model.AuthorResponse;
import com.victor.loading.rotate.RotateLoading;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AuthorFragment extends Fragment {
    RecyclerView recyclerView;
    private AuthorViewModel authorViewModel;
    @BindView(R.id.rotateLoding)
    RotateLoading rotateLoading;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_author, container, false);
        ButterKnife.bind(this, root);

        recyclerView = root.findViewById(R.id.recyclerView);
        rotateLoading.start();
        final AuthorResponseAdapter adapter = new AuthorResponseAdapter();
        AuthorViewModel itemViewModel = ViewModelProviders.of(this).get(AuthorViewModel.class);
        itemViewModel.authorPagedList.observe(this, new Observer<PagedList<AuthorResponse>>() {
            @Override
            public void onChanged(PagedList<AuthorResponse> users) {
                adapter.submitList(users);
            }
        });
        recyclerView.setAdapter(adapter);
        rotateLoading.stop();
        return root;
    }


}
