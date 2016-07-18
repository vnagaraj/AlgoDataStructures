package week2;

import java.io.IOException;
import java.util.Random;

/**
 * Created by VGN on 5/25/16.
 */
public class StressTestBuildHeap {
    public static void main(String[] args){
        try {
            verify(5);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void verify(int size) throws IOException {
        while (true){
            Long a[] = generateRandomArray(size);
            Long[] b = new Long[a.length];
            Long[]c = new Long[a.length];
            for (int i=0; i < b.length; i++){
                b[i] = a[i];
                c[i] = a[i];
            }
            BuildHeap buildHeap = new BuildHeap();
            BuildHeapBrute buildHeapBrute = new BuildHeapBrute();
            buildHeap.readData(a);
            buildHeapBrute.readData(b);
            long val = buildHeap.generateSwaps().size();
            if (!buildHeap.isHeap()){
                System.out.println("ERROR: Failure to validate");
                System.out.println("Binary heap " +val);
                System.out.println("array values" );
                for (int i=0; i< a.length; i++){
                    System.out.println(c[i]);
                }
                break;
            }
            System.out.println("Validated");
        }
    }

    private static Long[] generateRandomArray(int size){
        Random rn = new Random();
        Long[] a = new Long[size];
        for (int i = 0; i < size; i++) {
            //long value = (rn.nextLong());
            long value = rn.nextInt(10);
            a[i] = value;
        }
        return a;
    }
}
