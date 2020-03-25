package com.example.assignmentapplication.ui.author.model;

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

public class AuthorDataSource extends ItemKeyedDataSource<Integer, AuthorResponse> {

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<AuthorResponse> callback) {
        ApiService service = ApiServiceBuilder.buildService(ApiService.class);
        Call<List<AuthorResponse>> call = service.getAuthors(0, params.requestedLoadSize);
        call.enqueue(new Callback<List<AuthorResponse>>() {
            @Override
            public void onResponse(Call<List<AuthorResponse>> call, Response<List<AuthorResponse>> response) {
                List<AuthorResponse> apiResponse = response.body();
                Log.d(TAG, "onResponse: " + apiResponse.size());
                callback.onResult(apiResponse, 0, apiResponse.size());
            }

            @Override
            public void onFailure(Call<List<AuthorResponse>> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<AuthorResponse> callback) {
        ApiService service = ApiServiceBuilder.buildService(ApiService.class);
        Call<List<AuthorResponse>> call = service.getAuthors(params.key, params.key + params.requestedLoadSize);
        call.enqueue(new Callback<List<AuthorResponse>>() {
            @Override
            public void onResponse(Call<List<AuthorResponse>> call, Response<List<AuthorResponse>> response) {
                List<AuthorResponse> apiResponse = response.body();
                Log.d(TAG, "onResponse: " + apiResponse.size());
                callback.onResult(apiResponse);
            }

            @Override
            public void onFailure(Call<List<AuthorResponse>> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<AuthorResponse> callback) {

    }

    @NonNull
    @Override
    public Integer getKey(@NonNull AuthorResponse item) {
        return item.getId();
    }
}
