package org.example;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Statistics s = new Statistics();
        float[] rannum = s.generateRandNum(20);
        System.out.print("Numbers:");
        for (int i = 0; i < rannum.length; i++) {
            System.out.printf("[%.2f] ", rannum[i]);
        }

        System.out.println();

        System.out.println("mean=" + s.mean(rannum));

        System.out.printf("%.2f", s.median(rannum));
        System.out.println();
        System.out.println("variacen=" + s.variance(rannum));

        System.out.println("freg gom:");

        int[] f = s.freq(rannum);
        Graphics g = new Graphics();
        g.draw(f);
    }
}