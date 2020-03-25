package com.example.assignmentapplication.network;

import com.example.assignmentapplication.ui.article.model.ArticleResponse;
import com.example.assignmentapplication.ui.article.model.BlogOfArticleResponse;
import com.example.assignmentapplication.ui.author.model.AuthorResponse;
import com.example.assignmentapplication.ui.author.model.Author_Article_Response;
import com.example.assignmentapplication.ui.blog.model.BlogResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("users")
    Call<List<AuthorResponse>> getAuthors(@Query("_start") Integer start, @Query("_limit") Integer limit);

    @GET("posts")
    Call<List<ArticleResponse>> getArticles(@Query("_start") Integer start, @Query("_limit") Integer limit);

    @GET("comments")
    Call<List<BlogResponse>> getBlogs(@Query("_start") Integer start, @Query("_limit") Integer limit);

    @GET("posts/{article_id}/comments")
    Call<List<BlogOfArticleResponse>> getBlogsOfArticle(@Path("article_id") Integer article_id);

    @GET("users/{author_id}/posts")
    Call<List<Author_Article_Response>> getAuthorArticles(@Path("author_id") Integer author_id);
}