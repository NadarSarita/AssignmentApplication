package com.example.assignmentapplication.ui.author;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.assignmentapplication.ui.author.model.AuthorDataSource;
import com.example.assignmentapplication.ui.author.model.AuthorDataSourceFactory;
import com.example.assignmentapplication.ui.author.model.AuthorResponse;


public class AuthorViewModel extends ViewModel {
    public LiveData<PagedList<AuthorResponse>> authorPagedList;
    private LiveData<AuthorDataSource> liveDataSource;
    public static int PAGE_SIZE = 10;

    public AuthorViewModel() {
        init();
    }
    private void init() {
        AuthorDataSourceFactory itemDataSourceFactory = new AuthorDataSourceFactory();
        liveDataSource = itemDataSourceFactory.authorLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(PAGE_SIZE)
                .setPageSize(PAGE_SIZE)
                .build();
        authorPagedList = new LivePagedListBuilder<>(itemDataSourceFactory, config).build();
    }
}
