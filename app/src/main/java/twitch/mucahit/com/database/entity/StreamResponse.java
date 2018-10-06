package twitch.mucahit.com.database.entity;


import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class StreamResponse {

    @SerializedName("streams")
    private List<Stream> streams;

    public List<Stream> getStreams() {
        return streams;
    }

    public void setStreams(List<Stream> streams) {
        this.streams = streams;
    }

    @NonNull
    public List<Integer> getStreamIds(){
        List<Integer> streamIds = new ArrayList<>();
        for (Stream stream : streams){
            streamIds.add(stream.id);
        }

        return streamIds;
    }
}
