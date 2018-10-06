package twitch.mucahit.com.view_models;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import twitch.mucahit.com.database.entity.Stream;
import twitch.mucahit.com.repositories.StreamRepository;

public class StreamViewModel extends ViewModel {

    private LiveData<List<Stream>> stream;
    private StreamRepository streamRepository;

    @Inject
    public StreamViewModel(StreamRepository streamRepository){
        this.streamRepository = streamRepository;
    }

    public void init(){
        if (this.stream != null){
            return;
        }
        stream = streamRepository.getStreams();
    }

    public LiveData<List<Stream>> getStream(){
        return this.stream;
    }
}
