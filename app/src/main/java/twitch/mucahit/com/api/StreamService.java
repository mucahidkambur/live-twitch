package twitch.mucahit.com.api;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import twitch.mucahit.com.database.entity.StreamResponse;

public interface StreamService {

    @GET("kraken/streams/followed")
    Observable<StreamResponse> getStreams(@Query("client_id") String clientId, @Query("oauth_token") String token);
}
