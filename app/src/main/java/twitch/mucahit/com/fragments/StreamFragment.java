package twitch.mucahit.com.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;
import dagger.multibindings.IntoMap;
import twitch.mucahit.com.R;
import twitch.mucahit.com.adapter.StreamAdapter;
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

    StreamAdapter adapter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public StreamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stream, container, false);
        ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        adapter = new StreamAdapter(getContext());
        recyclerView.setAdapter(adapter);

        // Inflate the layout for this fragment
        return view;
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
                runLayoutAnimation(recyclerView);
                adapter.setStreams(streams);
            }
        });
    }

    private void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.scheduleLayoutAnimation();
    }
}
