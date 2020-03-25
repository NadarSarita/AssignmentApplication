package com.example.assignmentapplication.ui.blog.model;

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

public class BlogDataSource extends ItemKeyedDataSource<Integer, BlogResponse>
{

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<BlogResponse> callback) {
        ApiService service = ApiServiceBuilder.buildService(ApiService.class);
        Call<List<BlogResponse>> call = service.getBlogs(0,params.requestedLoadSize);
        call.enqueue(new Callback<List<BlogResponse>>() {
            @Override
            public void onResponse(Call<List<BlogResponse>> call, Response<List<BlogResponse>> response) {
               List<BlogResponse> apiResponse = response.body();
                Log.d(TAG, "onResponse: "+apiResponse.size());
                callback.onResult(apiResponse,0,apiResponse.size());
            }

            @Override
            public void onFailure(Call<List<BlogResponse>> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<BlogResponse> callback) {
        ApiService service = ApiServiceBuilder.buildService(ApiService.class);
        Call<List<BlogResponse>> call = service.getBlogs(params.key,params.key+params.requestedLoadSize);
        call.enqueue(new Callback<List<BlogResponse>>() {
            @Override
            public void onResponse(Call<List<BlogResponse>> call, Response<List<BlogResponse>> response) {
                List<BlogResponse> apiResponse = response.body();
                Log.d(TAG, "onResponse: "+apiResponse.size());
                callback.onResult(apiResponse);
            }

            @Override
            public void onFailure(Call<List<BlogResponse>> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<BlogResponse> callback) {

    }

    @NonNull
    @Override
    public Integer getKey(@NonNull BlogResponse item) {
        return item.getId();
    }
}