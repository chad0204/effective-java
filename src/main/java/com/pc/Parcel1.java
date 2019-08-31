package com.pc;

public class Parcel1 {


    private static String name;

//    public Parcel1(String name) {
//        this.name = name;
//    }

    class Contents implements Parcel {
        private int i = 11;
        public int value() {

            System.out.println(name);
            return i;
        }

        public Parcel1 f() {
            return Parcel1.this;
        }

        @Override
        public void method() {

        }
    }

    static class  Destination {
        private String label;
        Destination(String label) {
            this.label = label;
        }

        String readLabel() {
            return label;
        }
    }

    public Contents contents() {
        return new Contents();
    }

    public void ship(String dest) {
        Contents contents = new Contents();
        Destination destination = new Destination(dest);
        System.out.println(destination.readLabel());
    }

    public static void main(String[] args) {
        Parcel1 parcel1 = new Parcel1();
        parcel1.ship("Shanghai");


        Parcel c = parcel1.contents();
    }

}
