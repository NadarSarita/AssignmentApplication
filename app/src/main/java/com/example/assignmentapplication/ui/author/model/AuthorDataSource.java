package com.example.assignmentapplication.ui.author.model;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.ItemKeyedDataSource;
import androidx.paging.PageKeyedDataSource;
import com.example.assignmentapplication.network.ApiService;
import com.example.assignmentapplication.network.ApiServiceBuilder;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class AuthorDataSource extends ItemKeyedDataSource<Integer,AuthorResponse>
{

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<AuthorResponse> callback) {
        ApiService service = ApiServiceBuilder.buildService(ApiService.class);
        Call<List<AuthorResponse>> call = service.getAuthors(0,params.requestedLoadSize);
        call.enqueue(new Callback<List<AuthorResponse>>() {
            @Override
            public void onResponse(Call<List<AuthorResponse>> call, Response<List<AuthorResponse>> response) {
               List<AuthorResponse> apiResponse = response.body();
                Log.d(TAG, "onResponse: "+apiResponse.size());
                callback.onResult(apiResponse,0,apiResponse.size());
            }

            @Override
            public void onFailure(Call<List<AuthorResponse>> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<AuthorResponse> callback) {
        ApiService service = ApiServiceBuilder.buildService(ApiService.class);
        Call<List<AuthorResponse>> call = service.getAuthors(params.key,params.key+params.requestedLoadSize);
        call.enqueue(new Callback<List<AuthorResponse>>() {
            @Override
            public void onResponse(Call<List<AuthorResponse>> call, Response<List<AuthorResponse>> response) {
                List<AuthorResponse> apiResponse = response.body();
                Log.d(TAG, "onResponse: "+apiResponse.size());
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


/*
public class BlogDataSource extends PageKeyedDataSource<Long, AuthorResponse> {

    public static long FIRST_PAGE = 1;
    @Override public void loadInitial(@NonNull final LoadInitialParams<Long> params,
                                      @NonNull final LoadInitialCallback<Long, AuthorResponse> callback) {
        ApiService service = ApiServiceBuilder.buildService(ApiService.class);
        Call<AuthorResponse> call = service.getAuthors();
        call.enqueue(new Callback<AuthorResponse>() {
            @Override
            public void onResponse(Call<AuthorResponse> call, Response<AuthorResponse> response) {
                AuthorResponse> apiResponse = response.body();


            }

            @Override
            public void onFailure(Call<AuthorResponse> call, Throwable t) {

            }
        });

        call.enqueue(new Callback<AuthorResponse>() {
            @Override
            public void onResponse(Call<AuthorResponse> call, Response<AuthorResponse> response) {
                AuthorResponse> apiResponse = response.body();

                callback.onResult(apiResponse, null, FIRST_PAGE + 1);

            }

            @Override
            public void onFailure(Call<AuthorResponse> call, Throwable t) {

            }
        });
    }
    @Override
    public void loadBefore(@NonNull final LoadParams<Long> params,
                           @NonNull final LoadCallback<Long, AuthorResponse> callback) {
        ApiService apiService = ApiServiceBuilder.buildService(ApiService.class);
        Call<AuthorResponse> call = apiService.getAuthors(params.key);
        call.enqueue(new Callback<AuthorResponse>() {
            @Override public void onResponse(Call<AuthorResponse> call, Response<AuthorResponse> response) {
                AuthorResponse> apiResponse = response.body();

                    long key;
                    if (params.key > 1) {
                        key = params.key - 1;
                    } else {
                        key = 0;
                    }
                    callback.onResult(apiResponse, key);

            }
            @Override public void onFailure(Call<AuthorResponse> call, Throwable t) {
            }
        });
    }
    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params,
                          @NonNull final LoadCallback<Long, AuthorResponse> callback) {
        ApiService service = ApiServiceBuilder.buildService(ApiService.class);
        Call<AuthorResponse> call = service.getAuthors(params.key);
        call.enqueue(new Callback<AuthorResponse>() {
            @Override public void onResponse(Call<AuthorResponse> call, Response<AuthorResponse> response) {
                AuthorResponse> apiResponse = response.body();
                    callback.onResult(apiResponse, params.key + 1);

            }
            @Override public void onFailure(Call<AuthorResponse> call, Throwable t) {
            }
        });
    }
}
*/
