package twitch.mucahit.com.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import twitch.mucahit.com.App;
import twitch.mucahit.com.di.module.ActivityModule;
import twitch.mucahit.com.di.module.AppModule;
import twitch.mucahit.com.di.module.FragmentModule;

@Singleton
@Component(modules={AndroidSupportInjectionModule.class, AppModule.class, ActivityModule.class, FragmentModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

    void inject(App app);
}