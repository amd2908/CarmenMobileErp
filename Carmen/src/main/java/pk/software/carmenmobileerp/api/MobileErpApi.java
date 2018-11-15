package pk.software.carmenmobileerp.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Piotr Karmiński on 27.01.2018.
 */

//API
public interface MobileErpApi {

    @Headers({
            "X-Parse-Application-Id: wPF6ui2mjaXxDrm7QBlgJIGKBWEJqb2kLz8Vvcul",
            "X-Parse-REST-API-Key: iDk8jlZetR9K5JWtUW6N28qoikFx6YGf78SUGuaT",
            "X-Parse-Revocable-Session: 1"
    })
    @GET("login")
    Call<UserResponse> getLogin(@Query("username") String username, @Query("password") String password);

    @Headers({
            "X-Parse-Application-Id: wPF6ui2mjaXxDrm7QBlgJIGKBWEJqb2kLz8Vvcul",
            "X-Parse-REST-API-Key: iDk8jlZetR9K5JWtUW6N28qoikFx6YGf78SUGuaT",
            "X-Parse-Revocable-Session: 1"
    })
    @POST("users")
    Call<UserResponse>registerUser(@Body RegisterRequest registerRequest);
}
