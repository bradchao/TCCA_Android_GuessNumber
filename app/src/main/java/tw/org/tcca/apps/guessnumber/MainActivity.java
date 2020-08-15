package tw.org.tcca.apps.guessnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void guess(View view) {
    }

    public void exit(View view) {
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.v("bradlog", "onBackPressed");
    }

    private long lastTime = 0;

    @Override
    public void finish() {
        if (System.currentTimeMillis() - lastTime < 3*1000){
            super.finish();
        }else{
            lastTime = System.currentTimeMillis();
            Toast.makeText(this, "One more time exit", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("bradlog", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("bradlog", "onDestroy");
    }
}