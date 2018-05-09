package dev.edmt.androidcamerarecognitiontext;

import android.app.ActivityOptions;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class DashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton startRecord = (FloatingActionButton) findViewById(R.id.fab);
        startRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                promptSpeechInput();
            }
        });
        FloatingActionButton textRecognizer = (FloatingActionButton) findViewById(R.id.textRecognizer);
        textRecognizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                starTextRecognizer();
            }
        });
        FloatingActionButton labels = (FloatingActionButton) findViewById(R.id.labelsRecognizer);
        labels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                starLabelstRecognizer();
            }
        });
        FloatingActionButton faceRecognizer = (FloatingActionButton) findViewById(R.id.FaseRecognizer);
        faceRecognizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                starFaceRecognizer();
            }
        });
    }
    public void starTextRecognizer(){
        Intent intent = new Intent(this, TextRecognizerActivity.class);

        startActivity(intent);
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
    }
    public void starLabelstRecognizer(){
        Intent intent = new Intent(this, LabelRecognizerActivity.class);

        startActivity(intent);
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
    }
    public void starFaceRecognizer(){
        Intent intent = new Intent(this, FaceReconizerActivity.class);

        startActivity(intent);
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
    }
    public void promptSpeechInput(){
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say Something!");

        try{
            startActivityForResult(i, 100);
        }
        catch(ActivityNotFoundException a){
            Toast.makeText(DashActivity.this, "Sorry! Your device doesn't support Speech Language!", Toast.LENGTH_LONG).show();
        }

    }
    public boolean evaluateCommands(String text){
        Intent intent;
        switch (text){
            case "read text":
                getWindow().setExitTransition(new Explode());
                intent= new Intent(getApplicationContext(), TextRecognizerActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                return true;
            case "leer":
                getWindow().setExitTransition(new Explode());
                intent = new Intent(getApplicationContext(), TextRecognizerActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                return true;
            case "face recognizer":
                getWindow().setExitTransition(new Explode());
                intent= new Intent(getApplicationContext(), TextRecognizerActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                return true;
            case "quienes me acompañan":
                getWindow().setExitTransition(new Explode());
                intent = new Intent(getApplicationContext(), FaceReconizerActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                return true;
            case "Hay personas con migo":
                getWindow().setExitTransition(new Explode());
                intent = new Intent(getApplicationContext(), FaceReconizerActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                return true;


        }
        return false;

    }

    public void onActivityResult(int request_code, int result_code, Intent i){
        super.onActivityResult(request_code,result_code,i);

        switch(request_code){
            case 100: if(result_code == RESULT_OK && i != null){
                ArrayList<String> result = i.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                String text = String.valueOf(result);
                Toast.makeText(getApplicationContext(),text, Toast.LENGTH_LONG).show();
                if(evaluateCommands(result.toString())){return;};

                for (String line: result){
                    Toast.makeText(getApplicationContext(),line, Toast.LENGTH_LONG).show();
                    if (evaluateCommands(line)){
                        break;
                    }


                }
            }
                break;
        }
    }

}
