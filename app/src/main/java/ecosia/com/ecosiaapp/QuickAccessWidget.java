package ecosia.com.ecosiaapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Created by Abanoub Wagdy on 8/11/2016.
 */
public class QuickAccessWidget extends AppWidgetProvider {

    private final String ACTION_WIDGET_PLAY = "Play";

    private final String ACTION_WIDGET_STOP = "Stop";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            updateAppWidget(context, appWidgetManager, appWidgetIds[i]);
        }
    }

    private void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

        final RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.quick_access_widget);

        Intent playIntent = new Intent(context, Mp3PlayingService.class);
        playIntent.setAction(ACTION_WIDGET_PLAY);

        Intent stopIntent = new Intent(context, Mp3PlayingService.class);
        stopIntent.setAction(ACTION_WIDGET_STOP);

        PendingIntent playPendingIntent = PendingIntent.getService(context, 1, playIntent, 0);

        PendingIntent stopPendingIntent = PendingIntent.getService(context, 1, stopIntent, 0);

        views.setOnClickPendingIntent(R.id.imagePlay, playPendingIntent);
        views.setOnClickPendingIntent(R.id.imageStop, stopPendingIntent);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
}
