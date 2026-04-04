package Ycollections.test;

import Ycollections.domain.Smartphone;

public class EqualsTest01 {
    public static void main(String[] args) {
        Smartphone s1 = new Smartphone("11213", "iPhone");
        Smartphone s2 = new Smartphone("11213", "iPhone");

        System.out.println(s1.equals(s2));
    }
}
