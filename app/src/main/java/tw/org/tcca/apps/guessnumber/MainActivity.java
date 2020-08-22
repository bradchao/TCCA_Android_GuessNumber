package tw.org.tcca.apps.guessnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;

public class MainActivity extends AppCompatActivity {
    private String answer;
    private EditText input;
    private TextView log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.input);
        log = findViewById(R.id.log);

        initRound();
    }

    public void guess(View view) {
        String inputString = input.getText().toString();
        String result = checkAB(inputString);
        log.append(inputString + ":" + result + "\n");

        input.setText("");

    }

    private String checkAB(String guess){
        int A, B; A = B = 0;
        for (int i=0; i<guess.length(); i++){
            if (answer.charAt(i) == guess.charAt(i)){
                A++;
            }else if(answer.indexOf(guess.charAt(i)) != -1){
                B++;
            }
        }
        return String.format("%dA%dB", A, B);
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

    private void initRound(){
        answer = createAnswer();
        log.setText("");
        input.setText("");
    }

    private String createAnswer(){
        HashSet<Integer> set = new HashSet<>();
        while (set.size()<3){
            set.add((int)(Math.random()*10));
        }
        String ret = "";
        for (Integer i : set){
            ret += i;
        }
        Log.v("bradlog", "ans = " + ret);
        return ret;
    }


    public void newGame(View view) {
        initRound();
    }
}