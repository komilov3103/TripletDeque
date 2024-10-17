package ru.mpei;

public class Main {
    public static void main(String[] args) {
        TripletDeque<String> td = new TripletDeque<>();
        td.addFirst("Komil");
        td.addFirst("Komil");
        td.addFirst("Komil");
        td.addFirst("Komil");
        td.addLast("Dilovar");
        td.addLast("Dilovar");
        td.addLast("Dilovar");
        td.addLast("Dilovar");
        td.printDeque();
        var komil = td.removeFirstOccurrence("Komil");
        td.printDeque();
        System.out.println(komil);



    }
}
