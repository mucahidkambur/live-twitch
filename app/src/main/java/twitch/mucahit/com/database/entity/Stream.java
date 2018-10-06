package twitch.mucahit.com.database.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

@Entity
public class Stream {

    @PrimaryKey(autoGenerate = true)
    public final int id;

    @SerializedName("game")
    @NonNull
    public final String game;

    @SerializedName("viewers")
    @NonNull
    public final String viewers;

    @SerializedName("preview")
    @Embedded(prefix = "preview_")
    @NonNull
    public final Preview preview;

    @SerializedName("channel")
    @Embedded(prefix = "channel_")
    @NonNull
    public final Channel channel;

    private Date lastRefresh;

    public Stream(int id, @NonNull String game, @NonNull String viewers, @NonNull Preview preview, @NonNull Channel channel, Date lastRefresh) {
        this.id = id;
        this.game = game;
        this.viewers = viewers;
        this.preview = preview;
        this.channel = channel;
        this.lastRefresh = lastRefresh;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getGame() {
        return game;
    }

    @NonNull
    public String getViewers() {
        return viewers;
    }

    @NonNull
    public Preview getPreview() {
        return preview;
    }

    @NonNull
    public Channel getChannel() {
        return channel;
    }

    public Date getLastRefresh() {
        return lastRefresh;
    }

    public void setLastRefresh(Date lastRefresh) {
        this.lastRefresh = lastRefresh;
    }

    public static class Preview{

        @SerializedName("medium")
        @NonNull
        public final String preview;

        public Preview(@NonNull String preview) {

            this.preview = preview;
        }

        @NonNull
        public String getPreview() {
            return preview;
        }
    }

    public static class Channel{

        @SerializedName("status")
        @NonNull
        public final String status;

        @SerializedName("display_name")
        @NonNull
        public final String displayName;

        @SerializedName("logo")
        @NonNull
        public final String logo;

        @SerializedName("url")
        @NonNull
        public final String url;

        public Channel(@NonNull String status, @NonNull String displayName, @NonNull String logo, @NonNull String url) {
            this.status = status;
            this.displayName = displayName;
            this.logo = logo;
            this.url = url;
        }

        @NonNull
        public String getStatus() {
            return status;
        }

        @NonNull
        public String getDisplayName() {
            return displayName;
        }

        @NonNull
        public String getLogo() {
            return logo;
        }

        @NonNull
        public String getUrl() {
            return url;
        }
    }
}
