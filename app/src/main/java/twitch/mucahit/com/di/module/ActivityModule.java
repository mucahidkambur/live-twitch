package twitch.mucahit.com.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import twitch.mucahit.com.activities.MainActivity;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract MainActivity contributeMainActivity();
}
