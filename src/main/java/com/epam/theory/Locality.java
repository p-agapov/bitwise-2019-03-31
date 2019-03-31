package com.epam.theory;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

public class Locality {

    public static void main(String[] args) {

        // float[2]
        // 12b = header
        // 4b length
        // 4b * 2 = 8b
        // 24 * 500 * 14761 = 177132000

        // [14761]
        // 12b = header
        // 4b length
        // 4b * 14761 = 59044b
        // 59044 * 500  = 29522000

        // [500]
        // 12b = header
        // 4b length
        // 4b * 500 = 2000b
        // ---
        // 2016b


        // ~ 206_656_016 b
        //   206_666_016

        float[][][] arr = new float[500][14761][2];


        // Shallow
        System.out.println(ClassLayout.parseInstance(arr));


        System.out.println(GraphLayout.parseInstance((Object) arr).toFootprint());


        float[][][] arr2 = new float[2][500][14761];
        System.out.println(GraphLayout.parseInstance((Object) arr2).toFootprint());

    }
}
