package dev.edmt.androidcamerarecognitiontext.DataProviders;

import android.content.Context;


import com.google.android.gms.vision.face.Face;

import java.util.ArrayList;

import dev.edmt.androidcamerarecognitiontext.Objects.Speaker;

/**
 * Created by erwin-salas on 08/05/18.
 */

public class FaceLecturesProvider {
    private ArrayList<Face> captures;
    private static  FaceLecturesProvider ourInstance = new FaceLecturesProvider();

    public static FaceLecturesProvider getInstance() {

        if(ourInstance==null){
            ourInstance=new FaceLecturesProvider();
            return ourInstance;
        }
        return ourInstance;
    }

    private FaceLecturesProvider() {
        this.captures=new ArrayList<>();
    }

    public void addOrUpdateFaceCapture(Face lecture){
        for (Face capture :
                captures) {
            if (capture.getId() == lecture.getId()) {
                captures.remove(capture);
                captures.add(lecture);
                return;
            }
        }
        captures.add(lecture);

    }

    public ArrayList<Face> getCaptures() {
        return captures;
    }

    public void setCaptures(ArrayList<Face> captures) {
        this.captures = captures;
    }
}
