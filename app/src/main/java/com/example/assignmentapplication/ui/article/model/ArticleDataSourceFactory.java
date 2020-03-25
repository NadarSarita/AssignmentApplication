package com.example.assignmentapplication.ui.article.model;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;


public class ArticleDataSourceFactory extends DataSource.Factory<Integer, ArticleResponse> {
    public MutableLiveData<ArticleDataSource> articleLiveDataSource = new MutableLiveData<>();

    @Override
    public DataSource<Integer, ArticleResponse> create() {
        ArticleDataSource articleDataSource = new ArticleDataSource();
        articleLiveDataSource.postValue(articleDataSource);
        return articleDataSource;
    }
}
