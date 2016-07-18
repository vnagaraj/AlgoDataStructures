package week2;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BuildHeapBrute{
    private Long[] data;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new BuildHeapBrute().solve();
    }

    private void readData() throws IOException {
        int n = in.nextInt();
        data = new Long[n];
        for (int i = 0; i < n; ++i) {
          data[i] = Long.valueOf(in.next());
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
      // The following naive implementation just sorts 
      // the given sequence using selection sort algorithm
      // and saves the resulting sequence of swaps.
      // This turns the given array into a heap, 
      // but in the worst case gives a quadratic number of swaps.
      //
      // TODO: replace by a more efficient implementation
      for (int i = 0; i < data.length; ++i) {
        for (int j = i + 1; j < data.length; ++j) {
          if (data[i] > data[j]) {
            swaps.add(new Swap(i, j));
            long tmp = data[i];
            data[i] = data[j];
            data[j] = tmp;
          }
        }
      }
      return swaps;
    }

    public  void readData(Long[] input) throws IOException {
        data = new Long[input.length];
        for (int i = 0; i < data.length; ++i) {
            data[i] = input[i];
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
