package ecosia.com.ecosiaapp;

import android.app.Activity;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import model.Media;
import utilities.Utilities;
import utilities.storeData;

/**
 * Created by Abanoub Wagdy on 8/11/2016.
 */
public class Mp3PlayingService extends Service {

    static MediaPlayer player;
    RemoteViews remoteViews;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent.getAction().equals("Play")) {
            if (!new storeData(getApplicationContext()).getIsPlaying()) {
                player = new MediaPlayer();
                List<Media> songs = Utilities.getAllMusicFiles(getApplicationContext());
                int randomizedSong = Utilities.randInt(0, songs.size());

                try {
                    player.setDataSource(songs.get(randomizedSong).getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                player.setLooping(false);
                player.setVolume(100, 100);
                updateWidgetMediaText(songs.get(randomizedSong));

                try {
                    player.prepare();
                    player.start();
                    player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            new storeData(getApplicationContext()).setIsPlaying(false);
                        }
                    });
                    new storeData(getApplicationContext()).setIsPlaying(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Another Song is already playing ,please stop this and play another random one", Toast.LENGTH_LONG).show();
            }

        } else {
            new storeData(getApplicationContext()).setIsPlaying(false);
            stopSelf();
        }

        return 1;
    }

    private void updateWidgetMediaText(Media media) {

        RemoteViews updateViews = new RemoteViews(this.getPackageName(), R.layout.quick_access_widget);
        updateViews.setTextViewText(R.id.tvMediaName, media.getDisplayName());

        ComponentName thisWidget = new ComponentName(this, QuickAccessWidget.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(this);

        manager.updateAppWidget(thisWidget, updateViews);
    }

    @Override
    public void onDestroy() {
        player.stop();
        player.release();
    }
}