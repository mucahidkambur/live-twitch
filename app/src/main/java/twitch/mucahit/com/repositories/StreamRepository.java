package twitch.mucahit.com.repositories;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Insert;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import twitch.mucahit.com.App;
import twitch.mucahit.com.api.StreamService;
import twitch.mucahit.com.database.dao.StreamDao;
import twitch.mucahit.com.database.entity.Stream;
import twitch.mucahit.com.database.entity.StreamResponse;
import twitch.mucahit.com.database.entity.StreamResult;

@Singleton
public class StreamRepository {

    private final StreamService streamService;
    private final StreamDao streamDao;
    private final Executor executor;

    @Inject
    public StreamRepository(StreamService streamService, StreamDao streamDao, Executor executor) {
        this.streamService = streamService;
        this.streamDao = streamDao;
        this.executor = executor;
    }

    public LiveData<List<Stream>> getStreams(){
        refreshStream();

        return streamDao.getAllStreamer();
    }

    private void refreshStream(){
        executor.execute(() -> {

            streamService.getStreams("87wjif2ck3gw4cf5gss3qcb1erant6").enqueue(new Callback<StreamResponse>() {
                @Override
                public void onResponse(Call<StreamResponse> call, Response<StreamResponse> response) {
                    Toast.makeText(App.context, "Data refreshed from network", Toast.LENGTH_SHORT).show();
                    executor.execute(() -> {

//                            Log.d("deneme", response.body().getStreams().get(0).getGame());
//                            List<Integer> streamIds = response.body().getStreamIds();
//
//                            StreamResult streamResult = new StreamResult(streamIds);
//                            streamDao.save(streamResult);

                        StreamResponse streamResponse = response.body();
                        streamDao.save(streamResponse.getStreams());
                    });
                }

                @Override
                public void onFailure(Call<StreamResponse> call, Throwable t) {
                    Log.d("deneme", t.getLocalizedMessage());
                }
            });
        });
    }
}
