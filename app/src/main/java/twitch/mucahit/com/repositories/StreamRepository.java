package twitch.mucahit.com.repositories;

import android.arch.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import twitch.mucahit.com.BuildConfig;
import twitch.mucahit.com.api.StreamService;
import twitch.mucahit.com.database.dao.StreamDao;
import twitch.mucahit.com.database.entity.Stream;
import twitch.mucahit.com.database.entity.StreamResponse;

@Singleton
public class StreamRepository {

    private final StreamService streamService;
    private final StreamDao streamDao;
    private final Executor executor;
    private CompositeDisposable disposable = new CompositeDisposable();
    private StreamResponse response;

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

        Observable.interval(0,1, TimeUnit.MINUTES, Schedulers.io())
                .flatMap(new Function<Long,  Observable<Response>>() {
                    @Override
                    public Observable<Response> apply(Long aLong) throws Exception {

                        executor.execute(streamDao::clear);

                        disposable.add(streamService.getStreams(BuildConfig.CLIENT_ID, BuildConfig.OAUTH_TOKEN)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeWith(new DisposableObserver<StreamResponse>() {
                                    @Override
                                    public void onNext(StreamResponse streamResponse) {
                                        response = streamResponse;
                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onComplete() {
                                        if (response != null){
                                            executor.execute(()->{
                                                streamDao.save(response.getStreams());
                                            });
                                        }
                                    }
                                }));
                        return Observable.empty();
                    }
                })
                .subscribe();
    }
}
