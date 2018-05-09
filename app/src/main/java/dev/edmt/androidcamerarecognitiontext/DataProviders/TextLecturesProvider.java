package dev.edmt.androidcamerarecognitiontext.DataProviders;

import java.util.ArrayList;

/**
 * Created by erwin-salas on 08/05/18.
 */

public class TextLecturesProvider {
    private static  TextLecturesProvider ourInstance;
    ArrayList<String> captures;
    public static TextLecturesProvider getInstance() {
        if(ourInstance==null){
            ourInstance=new TextLecturesProvider();
            return ourInstance;
        }
        return ourInstance;
    }

    private TextLecturesProvider() {
        this.captures=new ArrayList<>();
    }

    public ArrayList<String> getCaptures() {
        return captures;
    }

    public void setCaptures(ArrayList<String> captures) {
        this.captures = captures;
    }
}
