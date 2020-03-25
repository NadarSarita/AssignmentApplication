package com.example.assignmentapplication.ui.author.model;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;


public class AuthorDataSourceFactory extends DataSource.Factory<Integer, AuthorResponse> {
    public MutableLiveData<AuthorDataSource> authorLiveDataSource=new MutableLiveData<>();
    @Override public DataSource<Integer, AuthorResponse> create() {
        AuthorDataSource authorDataSource = new AuthorDataSource();
        authorLiveDataSource.postValue(authorDataSource);
        return authorDataSource;
    }
}
