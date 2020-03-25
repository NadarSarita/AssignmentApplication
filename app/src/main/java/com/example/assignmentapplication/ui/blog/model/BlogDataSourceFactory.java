package com.example.assignmentapplication.ui.blog.model;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;


public class BlogDataSourceFactory extends DataSource.Factory<Integer, BlogResponse> {
    public MutableLiveData<BlogDataSource> blogLiveDataSource=new MutableLiveData<>();
    @Override public DataSource<Integer, BlogResponse> create() {
        BlogDataSource blogDataSource = new BlogDataSource();
        blogLiveDataSource.postValue(blogDataSource);
        return blogDataSource;
    }
}
