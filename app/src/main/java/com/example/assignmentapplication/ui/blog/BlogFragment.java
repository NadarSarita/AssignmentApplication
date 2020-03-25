package com.example.assignmentapplication.ui.blog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignmentapplication.R;
import com.example.assignmentapplication.ui.blog.model.BlogResponse;
import com.victor.loading.rotate.RotateLoading;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BlogFragment extends Fragment {
    RecyclerView recyclerView;
    private BlogViewModel blogViewModel;
    @BindView(R.id.rotateLoding)
    RotateLoading rotateLoading;

    @BindView(R.id.txtNoData)
    TextView txtNoData;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_blog, container, false);
        ButterKnife.bind(this,root);
        recyclerView = root.findViewById(R.id.recyclerView);
        rotateLoading.start();
        final BlogResponseAdapter adapter = new BlogResponseAdapter();
        BlogViewModel itemViewModel = ViewModelProviders.of(this).get(BlogViewModel.class);
        itemViewModel.blogPagedList.observe(this, new Observer<PagedList<BlogResponse>>() {
            @Override public void onChanged(PagedList<BlogResponse> users) {
                    recyclerView.setVisibility(View.VISIBLE);
                    txtNoData.setVisibility(View.GONE);
                    adapter.submitList(users);
            }
        });
        recyclerView.setAdapter(adapter);
        rotateLoading.stop();
        return root;
    }
}
