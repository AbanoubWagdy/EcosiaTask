package ecosia.com.ecosiaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Intent intent = new Intent(getApplicationContext(),Mp3PlayingService.class);
//        intent.putExtra("play",true);
//
//        startService(intent);

    }
}
