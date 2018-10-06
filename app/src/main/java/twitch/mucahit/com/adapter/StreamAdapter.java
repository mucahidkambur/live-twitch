package twitch.mucahit.com.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import twitch.mucahit.com.R;
import twitch.mucahit.com.database.entity.Stream;

public class StreamAdapter extends RecyclerView.Adapter<StreamAdapter.StreamHolder> {

    private List<Stream> streams = new ArrayList<>();
    Context context;

    public StreamAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public StreamHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.stream_item, viewGroup, false);

       return new StreamHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StreamHolder holder, int i) {
        Stream currentStream = streams.get(i);
        Glide.with(context).load(currentStream.getPreview().getPreview()).into(holder.imgPreview);
        Glide.with(context).load(currentStream.getChannel().getLogo()).into(holder.imgLogo);
        holder.txChannel.setText(currentStream.getChannel().getDisplayName());
        holder.txGame.setText(currentStream.getGame());
        holder.txTitle.setText(currentStream.getChannel().getStatus());
        holder.txViewer.setText(currentStream.getViewers());
    }

    @Override
    public int getItemCount() {
        return streams.size();
    }

    public void setStreams(List<Stream> streams){
        this.streams = streams;
        notifyDataSetChanged();
    }

    class StreamHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.imgPreview)
        ImageView imgPreview;
        @BindView(R.id.imgLogo)
        ImageView imgLogo;
        @BindView(R.id.txChannel)
        TextView txChannel;
        @BindView(R.id.txGame)
        TextView txGame;
        @BindView(R.id.txTitle)
        TextView txTitle;
        @BindView(R.id.txViewer)
        TextView txViewer;

        public StreamHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
