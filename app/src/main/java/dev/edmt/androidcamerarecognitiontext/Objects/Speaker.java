package dev.edmt.androidcamerarecognitiontext.Objects;

import android.content.Context;
import com.google.android.gms.vision.face.Face;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.ArrayList;
import java.util.Locale;

import dev.edmt.androidcamerarecognitiontext.DataProviders.FaceLecturesProvider;
import dev.edmt.androidcamerarecognitiontext.DataProviders.TextLecturesProvider;

/**
 * Created by erwin-salas on 08/05/18.
 */

public class Speaker {
    TextToSpeech mTTS;

    public Speaker(Context context) {
        this.mTTS = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mTTS.setLanguage(new Locale("es", "MEX"));

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {

                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });
    }


    public void speak(String text) {

        mTTS.setPitch(0.6f);
        mTTS.setSpeechRate(0.7f);

        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);


    }
    public void readFaceCaptures() throws InterruptedException {
        int cont=0;
        ArrayList<Face> list = FaceLecturesProvider.getInstance().getCaptures();
        int size=list.size();
        if (size==0){
            speak("No logro identificar personas cerca de tuyo");
        }
        else if (size==1){
            speak("Parece que solo tienes una persona cerca no seas timido dile algo");
        }
        else {
            speak("Al parecer estas acompañado por mas de una persona  ");
            Thread.sleep(30);
            speak(String.valueOf(list.size())+", para ser mas exactos");

            for (Face capture :
                    list ) {
                if (capture.getIsSmilingProbability()>=0.73){
                    cont += 1;
                }
            }
            if (cont>size/2){
                Thread.sleep(100);
                speak("Puedo notar que la mayoria esta muy feliz ");

            }
            else if (cont<size/3){
                Thread.sleep(100);
                speak("Creo que tus acompañantes no muestran mucha simpatia");

            }
        }


    }

    public void readTextLectures(){
        int cont=0;
        ArrayList<String> list = TextLecturesProvider.getInstance().getCaptures();
        int size=list.size();

    }



}
