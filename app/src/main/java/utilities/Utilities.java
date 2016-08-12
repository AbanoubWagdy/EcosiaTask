package utilities;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Media;

/**
 * Created by Abanoub Wagdy on 8/11/2016.
 */
public class Utilities {

    private static List<String> songs = new ArrayList<String>();
    private static Cursor cursor;

    public static List<Media> getAllMusicFiles(Context context) {

        List<Media> medias = new ArrayList<>();

        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        String[] projection = {
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DURATION
        };

        final String sortOrder = MediaStore.Audio.AudioColumns.TITLE + " COLLATE LOCALIZED ASC";

        Cursor cursor = null;
        try {
            Uri uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            cursor = context.getContentResolver().query(uri, projection, selection, null, sortOrder);
            if (cursor != null) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    Media media = new Media();
                    media.setTitle(cursor.getString(0));
                    media.setArtist(cursor.getString(1));
                    media.setPath(cursor.getString(2));
                    media.setDisplayName(cursor.getString(3));
                    media.setSongDuration(cursor.getString(4));
                    medias.add(media);
                    cursor.moveToNext();
                }

            }

        } catch (Exception e) {
            Log.d("", e.toString());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return medias;

    }

    public static int randInt(int min, int max) {

        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}
