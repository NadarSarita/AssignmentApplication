package com.example.assignmentapplication.ui.article.model;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.ItemKeyedDataSource;

import com.example.assignmentapplication.network.ApiService;
import com.example.assignmentapplication.network.ApiServiceBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ArticleDataSource extends ItemKeyedDataSource<Integer,ArticleResponse>
{

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<ArticleResponse> callback) {
        ApiService service = ApiServiceBuilder.buildService(ApiService.class);
        Call<List<ArticleResponse>> call = service.getArticles(0,params.requestedLoadSize);
        call.enqueue(new Callback<List<ArticleResponse>>() {
            @Override
            public void onResponse(Call<List<ArticleResponse>> call, Response<List<ArticleResponse>> response) {
               List<ArticleResponse> apiResponse = response.body();
                Log.d(TAG, "onResponse: "+apiResponse.size());
                callback.onResult(apiResponse,0,apiResponse.size());
            }

            @Override
            public void onFailure(Call<List<ArticleResponse>> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<ArticleResponse> callback) {
        ApiService service = ApiServiceBuilder.buildService(ApiService.class);
        Call<List<ArticleResponse>> call = service.getArticles(params.key,params.key+params.requestedLoadSize);
        call.enqueue(new Callback<List<ArticleResponse>>() {
            @Override
            public void onResponse(Call<List<ArticleResponse>> call, Response<List<ArticleResponse>> response) {
                List<ArticleResponse> apiResponse = response.body();
                Log.d(TAG, "onResponse: "+apiResponse.size());
                callback.onResult(apiResponse);
            }

            @Override
            public void onFailure(Call<List<ArticleResponse>> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<ArticleResponse> callback) {

    }

    @NonNull
    @Override
    public Integer getKey(@NonNull ArticleResponse item) {
        return item.getId();
    }
}