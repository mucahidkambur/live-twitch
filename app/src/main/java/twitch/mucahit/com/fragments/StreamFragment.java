package twitch.mucahit.com.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import dagger.multibindings.IntoMap;
import twitch.mucahit.com.R;
import twitch.mucahit.com.database.entity.Stream;
import twitch.mucahit.com.view_models.StreamViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class StreamFragment extends Fragment {

    public static final String UID_KEY = "uid";
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private StreamViewModel viewModel;

    public StreamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stream, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.configureDagger();
        this.configureViewModel();
    }

    private void configureDagger(){
        AndroidSupportInjection.inject(this);
    }

    private void configureViewModel(){

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(StreamViewModel.class);
        viewModel.init();
        viewModel.getStream().observe(this, new Observer<List<Stream>>() {
            @Override
            public void onChanged(@Nullable List<Stream> streams) {

            }
        });
    }
}
