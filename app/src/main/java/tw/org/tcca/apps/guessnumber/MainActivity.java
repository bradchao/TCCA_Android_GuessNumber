package tw.org.tcca.apps.guessnumber;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
    private int counter;    // 0

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.input);
        log = findViewById(R.id.log);

        initRound();
    }

    public void guess(View view) {
        counter++;
        String inputString = input.getText().toString();
        String result = checkAB(inputString);
        log.append(counter + "." + inputString + ":" + result + "\n");

        input.setText("");

        if (result.equals("3A0B")){
            // WINNER
            showDialog(true);
        }else if (counter == 10){
            // LOSER
            showDialog(false);
        }

    }

    private void showDialog(boolean isWinner){
        String mesg = isWinner?"恭喜老爺":"魯蛇:" + answer;
        AlertDialog alertDialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Result");
        builder.setMessage(mesg);
        builder.setCancelable(false);
        builder.setPositiveButton("Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                newGame(null);
            }
        });
        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.super.finish();
            }
        });
        alertDialog = builder.create();
        alertDialog.show();
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
        counter = 0;
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