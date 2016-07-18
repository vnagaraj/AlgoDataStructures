package week2;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BuildHeap {
    private Long[] data;
    private int counter;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new BuildHeap().solve();
    }

    private void readData() throws IOException {
        int n = in.nextInt();
        data = new Long[n];
        for (int i = 0; i < n; ++i) {
          data[i] = Long.valueOf(in.next());
        }
    }

    public boolean isHeap(){
        for(int i=0; i< data.length/2; i++){
            int index = i +1;
            int child = index * 2;
            if (data[index-1] > data[child-1]){
                return false;
            }
            child = child +1;
            if (child > data.length){
                return true;
            }
            if (data[index-1] > data[child-1]){
                return false;
            }

        }
        return true;

    }


    public  void readData(Long[] input) throws IOException {
        data = new Long[input.length];
        for (int i = 0; i < data.length; ++i) {
            data[i] = input[i];
        }
    }

    private void writeResponse(ArrayList<Swap> swaps) {
        out.println(swaps.size());
        for (Swap swap : swaps) {
          out.println(swap.index1 + " " + swap.index2);
        }
    }

    public ArrayList<Swap> generateSwaps() {
      ArrayList<Swap> swaps = new ArrayList<Swap>();
      for(int i=((data.length-1)/2); i>=0; i--){
          sink(i+1, swaps);
      }
      return swaps;
    }

    private void sink(int index, ArrayList<Swap> swaps){
        int smaller_index = -1; //get smaller_index among the children
        while (true){
            int children_index_1 = index * 2;
            int children_index_2 = index * 2 + 1;
            if (children_index_1 > data.length){
                break;
            }
            if (children_index_2 > data.length){
                smaller_index = children_index_1;
            }
            else{
                if (data[children_index_1-1] <= data[children_index_2 -1]){
                    smaller_index = children_index_1;
                }
                else{
                    smaller_index = children_index_2;
                }
            }
            if (data[smaller_index-1] < data[index-1]){
                //swap values at index and smaller index
                swaps.add(new Swap(index -1, smaller_index-1));
                long temp = data[smaller_index-1];
                data[smaller_index-1] = data[index -1];
                data[index-1] = temp;
                index = smaller_index;
            }else{
                break;
            }
        }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        ArrayList<Swap> swaps = generateSwaps();
        writeResponse(swaps);
        out.close();
    }

    static class Swap {
        int index1;
        int index2;

        public Swap(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
