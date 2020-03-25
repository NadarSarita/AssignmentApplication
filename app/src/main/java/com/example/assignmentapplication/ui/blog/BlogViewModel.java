package com.example.assignmentapplication.ui.blog;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.assignmentapplication.ui.blog.model.BlogDataSource;
import com.example.assignmentapplication.ui.blog.model.BlogDataSourceFactory;
import com.example.assignmentapplication.ui.blog.model.BlogResponse;

public class BlogViewModel extends ViewModel {
    public LiveData<PagedList<BlogResponse>> blogPagedList;
    private LiveData<BlogDataSource> liveDataSource;
    public static int PAGE_SIZE = 10;

    public BlogViewModel() {
        init();
    }

    private void init() {
        BlogDataSourceFactory itemDataSourceFactory = new BlogDataSourceFactory();
        liveDataSource = itemDataSourceFactory.blogLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(PAGE_SIZE)
                .setPageSize(PAGE_SIZE)
                .build();
        blogPagedList = new LivePagedListBuilder<>(itemDataSourceFactory, config).build();
    }
}