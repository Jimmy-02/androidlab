package org.example;

public class Graphics {
    public void draw(int[] fr){
        for (int i = 0; i < fr.length; i++) {
            System.out.print(i + "/10 - " + (i + 1) + "/10: ");
            for (int j = 0; j < fr[i]; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
