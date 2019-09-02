package com.pc.something;


import java.util.List;

public class Parcel2 {

    private String name;//

    private List<String> list;


    class Contents {
        private int i = 11;
        public int value() {
            return i;
        }
    }

    static class Destination {
        private String label;
        Destination(String label) {
            this.label = label;
        }

        String readLabel() {
            return label;
        }
    }

    public Destination to(String s) {
        return new Destination(s);
    }

    public Contents contents() {
        return contents();
    }


    public void ship(String dest) {
        Contents contents = contents();
        Destination destination = to(dest);
        System.out.println(destination.readLabel());
    }

    public static void main(String[] args) {
        Parcel2 p = new Parcel2();
        p.ship("Shanghai");

        Parcel2 q = new Parcel2();

        Parcel2.Contents c = q.contents();
        Parcel2.Destination d = q.to("Beijing");

    }

}
