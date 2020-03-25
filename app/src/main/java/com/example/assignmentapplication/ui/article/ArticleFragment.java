package com.example.assignmentapplication.ui.article;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.assignmentapplication.R;
import com.example.assignmentapplication.ui.article.model.ArticleResponse;
import com.victor.loading.rotate.RotateLoading;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleFragment extends Fragment {
    RecyclerView recyclerView;
    private ArticleViewModel itemViewModel;
    @BindView(R.id.rotateLoding)
    RotateLoading rotateLoading;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_article, container, false);
        ButterKnife.bind(this,root);
        recyclerView = root.findViewById(R.id.recyclerView);
        rotateLoading.start();
        final ArticleResponseAdapter adapter = new ArticleResponseAdapter();
         itemViewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);
        itemViewModel.articlePagedList.observe(this, new Observer<PagedList<ArticleResponse>>() {
            @Override public void onChanged(PagedList<ArticleResponse> users) {
                adapter.submitList(users);

            }
        });
        recyclerView.setAdapter(adapter);
        rotateLoading.stop();
        return root;
    }
}
