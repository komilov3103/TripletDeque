package ru.mpei;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        TripletDeque<String> td = new TripletDeque<>();
        for (int i=0; i < 15 ;i++){
            td.addLast("n_"+i);
        }

        System.out.println(td.remove("n_3"));

        td.printDeque();

    }
}
