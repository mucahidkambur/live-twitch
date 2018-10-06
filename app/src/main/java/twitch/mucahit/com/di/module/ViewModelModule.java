package twitch.mucahit.com.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import twitch.mucahit.com.di.key.ViewModelKey;
import twitch.mucahit.com.view_models.FactoryViewModel;
import twitch.mucahit.com.view_models.StreamViewModel;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(StreamViewModel.class)
    abstract ViewModel bindStreamViewModel(StreamViewModel streamViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(FactoryViewModel factory);
}
