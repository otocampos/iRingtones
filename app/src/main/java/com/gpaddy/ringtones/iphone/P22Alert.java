package com.gpaddy.ringtones.iphone;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

public class P22Alert extends Fragment {

    S2SetAdapterAlert adapter;
    ArrayList<String> arrayString;
    ArrayList<S1ObjectMusic> objectArray;
    S1ObjectMusic objectMusic[];

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.p26pager, container, false);

        arrayString = new ArrayList<>();
        objectArray = new ArrayList<>();

        arrayString = sAlert();

        ListView listView = (ListView) view.findViewById(R.id.listView);

        int lengh = arrayString.size();
        objectMusic = new S1ObjectMusic[lengh];
        for (int i = 0 ; i < lengh ; i++) {
            objectMusic[i] = new S1ObjectMusic(arrayString.get(i));
            objectArray.add(objectMusic[i]);
        }

        adapter = new S2SetAdapterAlert(getActivity() , R.layout.p22row, objectArray);
        listView.setAdapter(adapter);

        return view;
    }

    public ArrayList<String> sAlert () {
//        int count = 12;
//
//        String sAlert[] = new String[count];
//        sAlert[0] = "Aurora";
//        sAlert[1] = "Bamboo";
//        sAlert[2] = "Chord";
//        sAlert[3] = "Circles";
//        sAlert[4] = "Complete";
//        sAlert[5] = "Digital";
//        sAlert[6] = "Hello";
//        sAlert[7] = "Keys";
//        sAlert[8] = "Note";
//        sAlert[9] = "Popcorn";
//        sAlert[10] = "Pulse";
//        sAlert[11] = "Synth";

        ArrayList<String> array = new ArrayList<>();


        File file = new File(Environment.getExternalStorageDirectory()+"/i Alert/");
        File[] files=file.listFiles();
        for(int i=0;i<files.length;i++){
            String name = files[i].getName().replace(".mp3","");
            array.add(name);

        }
//        array.addAll(Arrays.asList(sAlert).subList(0 , count));

        return array;
    }

}

