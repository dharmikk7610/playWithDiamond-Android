package Api;

import Model.Signupmodel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Userapiservice {

    @POST("auth/signup")
    Call<Object> signup(@Body Signupmodel userreq);
}
