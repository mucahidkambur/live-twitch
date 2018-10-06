package twitch.mucahit.com.database.entity;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.List;

import twitch.mucahit.com.database.converter.StreamTypeConverters;

@Entity(primaryKeys = {"streamIds"})
@TypeConverters(StreamTypeConverters.class)
public class StreamResult {

    @NonNull
    public final List<Integer> streamIds;

    public StreamResult(List<Integer> streamIds) {
        this.streamIds = streamIds;
    }
}
