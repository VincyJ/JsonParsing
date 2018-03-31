package example.com.jsonparsing.remote;

import android.util.Log;

import example.com.jsonparsing.interfaces.ApiCallback;
import example.com.jsonparsing.utils.AppConstants;
import example.com.jsonparsing.model.MoviesResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static example.com.jsonparsing.utils.AppConstants.BASE_URL;

/**
 * Created by compaq on 3/30/2018.
 */

public class ApiClient {

    private static final String TAG = ApiClient.class.getSimpleName();
    private static Retrofit retrofit = null;

    /**
     * Method to call retrofit client
     * @return Retrofit instance
     */
    private static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    /**
     * Method to get movie datas from the response
     * and passing datas in success callback
     * @param mApiCallback interface instance
     */
    public static void getDatas(final ApiCallback mApiCallback) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MoviesResponse> call = apiService.getTopRatedMovies(AppConstants.API_KEY);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if (response.body() != null && response.body().getResults() != null)
                    mApiCallback.succsessCallback(response.body().getResults());
                else
                    mApiCallback.failureCallback();
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                mApiCallback.failureCallback();
            }
        });
    }
}
