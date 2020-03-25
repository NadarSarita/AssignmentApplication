package com.example.assignmentapplication.ui.article;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.assignmentapplication.ui.article.model.ArticleDataSource;
import com.example.assignmentapplication.ui.article.model.ArticleDataSourceFactory;
import com.example.assignmentapplication.ui.article.model.ArticleResponse;

public class ArticleViewModel extends ViewModel {
    public LiveData<PagedList<ArticleResponse>> articlePagedList;
    private LiveData<ArticleDataSource> liveDataSource;
    public static int PAGE_SIZE = 10;

    public ArticleViewModel() {
        init();
    }

    private void init() {
        ArticleDataSourceFactory itemDataSourceFactory = new ArticleDataSourceFactory();
        liveDataSource = itemDataSourceFactory.articleLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(PAGE_SIZE)
                .setPageSize(PAGE_SIZE)
                .build();
        articlePagedList = new LivePagedListBuilder<>(itemDataSourceFactory, config).build();
    }
}