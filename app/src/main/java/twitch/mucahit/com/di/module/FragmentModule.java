package twitch.mucahit.com.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import twitch.mucahit.com.fragments.StreamFragment;

@Module
public abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract StreamFragment contributeStreamFragment();
}
