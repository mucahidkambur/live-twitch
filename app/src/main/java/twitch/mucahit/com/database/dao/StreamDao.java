package twitch.mucahit.com.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.Date;
import java.util.List;

import twitch.mucahit.com.database.entity.Stream;
import twitch.mucahit.com.database.entity.StreamResponse;
import twitch.mucahit.com.database.entity.StreamResult;

@Dao
public interface StreamDao {

    @Insert
    void save(List<Stream> stream);

    @Query("SELECT * FROM stream")
    LiveData<List<Stream>> getAllStreamer();

    @Query("SELECT * FROM stream WHERE lastRefresh > :lastRefreshMax LIMIT 1")
    Stream hasStream(Date lastRefreshMax);
}
