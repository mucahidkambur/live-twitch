package twitch.mucahit.com.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import twitch.mucahit.com.api.StreamService;
import twitch.mucahit.com.database.MyDatabase;
import twitch.mucahit.com.database.dao.StreamDao;
import twitch.mucahit.com.repositories.StreamRepository;

@Module(includes = ViewModelModule.class)
public class AppModule {

    @Provides
    @Singleton
    MyDatabase provideDatabase(Application application){
        return Room.databaseBuilder(application,
                MyDatabase.class, "database.db").build();
    }

    @Provides
    @Singleton
    StreamDao provideStreamDao(MyDatabase database){return database.streamDao();}

    @Provides
    Executor provideExecutor(){
        return Executors.newSingleThreadExecutor();
    }

    @Provides
    @Singleton
    StreamRepository provideStreamRepository(StreamService streamService, StreamDao streamDao, Executor executor){
        return new StreamRepository(streamService, streamDao, executor);
    }

    private static String BASE_URL = "https://api.twitch.tv/";

    @Provides
    Gson provideGson(){return new GsonBuilder().create(); }

    @Provides
    Retrofit provideRetrofit(Gson gson){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }

    @Provides
    @Singleton
    StreamService provideStreamService(Retrofit restAdapter){
        return restAdapter.create(StreamService.class);
    }
}
