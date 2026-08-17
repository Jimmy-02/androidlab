package org.example;

public abstract class AStats {
    public abstract float[] generateRandNum(int size);

    //tbc
    public abstract float mean(float[] arr);

    public abstract float median(float[] arr);

    public abstract float variance(float[] arr);

    public abstract int[] freq(float[] arr);
}
