package example.com.jsonparsing.remote;

import example.com.jsonparsing.model.MoviesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by compaq on 3/30/2018.
 */

/**
 * ApiInterface for api execution
 */
public interface ApiInterface {

    // It is to get the response from the api
    // and setting values in the MoviesResponse model
    // through GsonConverterFactory
    // Api key is added in the query parameter to make the call secured.

    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);
}
