package twitch.mucahit.com.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import twitch.mucahit.com.database.converter.DateConverter;
import twitch.mucahit.com.database.dao.StreamDao;
import twitch.mucahit.com.database.entity.Stream;
import twitch.mucahit.com.database.entity.StreamResult;

@Database(entities = {Stream.class, StreamResult.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class MyDatabase extends RoomDatabase {

    private static volatile MyDatabase INSTANCE;

    public abstract StreamDao streamDao();
}
