package org.example;

import java.util.Arrays;
import java.util.Random;

public class Statistics extends AStats {

    @Override
    public float[] generateRandNum(int size) {
        if(size <= 0){
            System.out.println("nguyen duong pls");
        }
        Random rand = new Random();
        float[] arr = new float[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextFloat();
        }
        return arr;
    }

    @Override
    public float mean(float[] arr) {
        float sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum / arr.length;
    }

    @Override
    public float median(float[] arr){
        Arrays.sort(arr);
        System.out.print("sorted arrsy: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("[%.2f] ", arr[i]);
        }
        System.out.println();
        System.out.print("median=");

        int n = arr.length;

        if(n % 2 == 0){
            return (arr[n/2-1] + arr[n/2]) /2;

        }else{
            return arr[n/2];
        }
    }

    //1. tinh trung binh 2. lay tung gia tri trong mang tru so trung binh 3. binh phuong so vua tru 4. cong don tat ca 5. roi chia cho so luong phan tu trong arr
    @Override
    public float variance(float[] arr) {
        float meanArr = mean(arr);
        float sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += (float) Math.pow(arr[i] - meanArr, 2);
        }
        return sum / arr.length;
    }

    @Override
    public int[] freq(float[] arr) {
        int[] result = new int[10];


        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0.1)
                result[0]++;
            else if (arr[i] < 0.2)
                result[1]++;
            else if (arr[i] < 0.3)
                result[2]++;
            else if (arr[i] < 0.4)
                result[3]++;
            else if (arr[i] < 0.5)
                result[4]++;
            else if (arr[i] < 0.6)
                result[5]++;
            else if (arr[i] < 0.7)
                result[6]++;
            else if (arr[i] < 0.8)
                result[7]++;
            else if (arr[i] < 0.9)
                result[8]++;
            else
                result[9]++;
        }
        return result;
    }
}