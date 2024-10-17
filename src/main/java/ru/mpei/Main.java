package ru.mpei;

public class Main {
    public static void main(String[] args) {
        TripletDeque<String> td = new TripletDeque<>();
        td.addFirst("one");
        td.addFirst("two");
        td.addLast("three");
        td.addLast("three");
        td.addLast("three");
        td.addLast("five");
        td.addFirst("one");
        td.addFirst("two");
        td.addLast("five");
        String s = td.removeFirst();
        String s1 = td.removeLast();
        td.removeFirst();
        td.removeFirst();
        td.removeFirst();
        td.printDeque();
        System.out.println();
        System.out.println(s);
        System.out.println(s1);





    }
}
