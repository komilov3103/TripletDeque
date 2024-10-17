package ru.mpei;

public class Main {
    public static void main(String[] args) {
        Container c = new Container();
        if (c instanceof Container){
            System.out.println(true);
        }
        String[] s = new String[5];
        System.out.println(s.getClass());
    }
}
