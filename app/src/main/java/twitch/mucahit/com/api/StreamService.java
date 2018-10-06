package twitch.mucahit.com.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import twitch.mucahit.com.database.entity.StreamResponse;

public interface StreamService {

    @GET("kraken/streams/followed?oauth_token=0koaiuaigrp8dwisvb93fplk4mwzze")
    Call<StreamResponse> getStreams(@Query("client_id") String clientId);
}
