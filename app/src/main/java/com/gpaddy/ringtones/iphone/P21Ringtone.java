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

public class P21Ringtone extends Fragment {

    S2SetAdapterRingtone adapter;
    ArrayList<String> arrayString;
    ArrayList<S1ObjectMusic> objectArray;
    S1ObjectMusic objectMusic[];

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.p26pager, container, false);

        arrayString = new ArrayList<>();
        objectArray = new ArrayList<>();

        arrayString = sRingtone();

        ListView listView = (ListView) view.findViewById(R.id.listView);

        int lengh = arrayString.size();
        objectMusic = new S1ObjectMusic[lengh];
        for (int i = 0 ; i < lengh ; i++) {
            objectMusic[i] = new S1ObjectMusic(arrayString.get(i));
            objectArray.add(objectMusic[i]);
        }

        adapter = new S2SetAdapterRingtone(getActivity() , R.layout.p22row, objectArray);
        listView.setAdapter(adapter);

        return view;
    }

    public ArrayList<String> sRingtone () {
//        int count = 78;
//
//        String sRingtone[] = new String[count];
//        sRingtone[0] = "Alarm";
//        sRingtone[1] = "Apex";
//        sRingtone[2] = "Ascending";
//        sRingtone[3] = "Bark";
//        sRingtone[4] = "Beacon";
//        sRingtone[5] = "Bell Tower";
//        sRingtone[6] = "Blues";
//        sRingtone[7] = "Boing";
//        sRingtone[8] = "Bulletin";
//        sRingtone[9] = "By The Seaside";
//        sRingtone[10] = "Chimes";
//        sRingtone[11] = "Circuit";
//        sRingtone[12] = "Constellation";
//        sRingtone[13] = "Cosmic";
//        sRingtone[14] = "Crystals";
//        sRingtone[15] = "Doorbell";
//        sRingtone[16] = "Duck";
//        sRingtone[17] = "Harp";
//        sRingtone[18] = "Hillside";
//        sRingtone[19] = "Illuminate";
//        sRingtone[20] = "Marimba";
//        sRingtone[21] = "Marimba remix";
//        sRingtone[22] = "Marimba remix2";
//        sRingtone[23] = "Marimba remix3";
//        sRingtone[24] = "Motorcycle";
//        sRingtone[25] = "Night Owl";
//        sRingtone[26] = "Old Car Horn";
//        sRingtone[27] = "Old Phone";
//        sRingtone[28] = "Opening";
//        sRingtone[29] = "Opening remix";
//        sRingtone[30] = "Opening remix2";
//        sRingtone[31] = "Opening remix3";
//        sRingtone[32] = "Opening remix4";
//        sRingtone[33] = "Piano Riff";
//        sRingtone[34] = "Pinball";
//        sRingtone[35] = "Playtime";
//        sRingtone[36] = "Presto";
//        sRingtone[37] = "Radar";
//        sRingtone[38] = "Radiate";
//        sRingtone[39] = "Ripples";
//        sRingtone[40] = "Robot";
//        sRingtone[41] = "Sci-fi";
//        sRingtone[42] = "Sencha";
//        sRingtone[43] = "Signal";
//        sRingtone[44] = "Silk";
//        sRingtone[45] = "Silk remix";
//        sRingtone[46] = "Silk remix2";
//        sRingtone[47] = "Slow Rise";
//        sRingtone[48] = "Sonar";
//        sRingtone[49] = "Stargaze";
//        sRingtone[50] = "Strum";
//        sRingtone[51] = "Summit";
//        sRingtone[52] = "Timba";
//        sRingtone[53] = "Time Passing";
//        sRingtone[54] = "Trill";
//        sRingtone[55] = "Twinkle";
//        sRingtone[56] = "Uplift";
//        sRingtone[57] = "Waves";
//        sRingtone[58] = "Xylophone";
//        sRingtone[59] = "Ringtone 1";
//        sRingtone[60] = "Ringtone 2";
//        sRingtone[61] = "Ringtone 3";
//        sRingtone[62] = "Ringtone 4";
//        sRingtone[63] = "Ringtone 5";
//        sRingtone[64] = "Ringtone 6";
//        sRingtone[65] = "Ringtone 7";
//        sRingtone[66] = "Ringtone 8";
//        sRingtone[67] = "Ringtone 9";
//        sRingtone[68] = "Ringtone 10";
//        sRingtone[69] = "Ringtone 11";
//        sRingtone[70] = "Ringtone 12";
//        sRingtone[71] = "Ringtone 13";
//        sRingtone[72] = "Ringtone 14";
//        sRingtone[73] = "Ringtone 15";
//        sRingtone[74] = "Ringtone 16";
//        sRingtone[75] = "Ringtone 17";
//        sRingtone[76] = "Ringtone 18";
//        sRingtone[77] = "Ringtone 19";

        ArrayList<String> array = new ArrayList<>();

        File file = new File(Environment.getExternalStorageDirectory()+"/i Ringtone/");
        File[] files=file.listFiles();
        for(int i=0;i<files.length;i++){
            String name = files[i].getName().replace(".mp3","");
            array.add(name);

        }

//        array.addAll(Arrays.asList(sRingtone).subList(0 , count));

        return array;
    }

}

