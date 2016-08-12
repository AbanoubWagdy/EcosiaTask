package utilities;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Abanoub Wagdy on 8/11/2016.
 */
public class storeData {

    private Context context;
    String DATABASE_NAME = "Ecosia";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    String TAG = "StoreData";

    public storeData(Context ctx) {

        super();
        this.context = ctx;
        sharedPreferences = context.getSharedPreferences(DATABASE_NAME,
                Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setIsPlaying(boolean isPlaying) {
        editor.putBoolean("isPlaying", isPlaying);
        editor.commit();
    }

    public boolean getIsPlaying() {
        boolean isPlaying = sharedPreferences.getBoolean("isPlaying",
                false);
        return isPlaying;
    }
}
