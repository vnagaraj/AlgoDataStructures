package week2;

import java.io.*;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * Created by VGN on 6/14/16.
 */
public class MergeTables {

    static long maxRows = 0;

    public static Table[] getTables(long[] rowData){
        Table[] tables = new Table[rowData.length];
        for (int i=0; i< rowData.length; i++){
            if (rowData[i] > maxRows){
                maxRows = rowData[i];
            }
            tables[i] = new Table(i, rowData[i]);
        }
        return tables;
    }

    public static void mergeTables(Table dest, Table source){
        Table destRoot = getRoot(dest);
        Table srcRoot = getRoot(source);
        if (destRoot == srcRoot){
            return;
        }
        long curRows = -1;
        if (destRoot.rank > srcRoot.rank){
            srcRoot.parent = destRoot;
            destRoot.numRows += srcRoot.numRows;
            srcRoot.numRows = 0;
            curRows = destRoot.numRows;
        } else{
            destRoot.parent = srcRoot;
            if (srcRoot.rank == destRoot.rank){
                srcRoot.rank += 1;
            }
            srcRoot.numRows += destRoot.numRows;
            destRoot.numRows =0;
            curRows = srcRoot.numRows;
        }
        if (curRows > maxRows){
            maxRows = curRows;
        }
    }

    private static Table getRoot(Table table){
        if (table != table.parent){
            table.parent = getRoot(table.parent);
        }
        return table.parent;
    }

    public void run(InputReader reader, OutputWriter writer) {
        int tableNum = reader.nextInt();
        int seqCount = reader.nextInt();
        int maxRow = -1;
        Table[] tables = new Table[tableNum];
        for (int i = 0; i <tableNum; i++) {
            int numberOfRows = reader.nextInt();
            tables[i] = new Table(i, numberOfRows);
            if (maxRow < numberOfRows){
                maxRow = numberOfRows;
            }
        }
        MergeTables.maxRows = maxRow;
        for (int i = 0; i < seqCount; i++) {
            int destination = reader.nextInt()-1;
            int source = reader.nextInt()-1;
            Table destTable = tables[destination];
            Table srcTable = tables[source];
            MergeTables.mergeTables(destTable, srcTable);
            writer.printf("%d\n", MergeTables.maxRows);
        }
    }

    /*
    public static void main(String[] args){
        long[] rowData = new long[]{5, 5, 3,6 ,5,3};
        Table[] tables = getTables(rowData);
        long[][] mergeQueries = new long[4][2];
        mergeQueries[0]= new long[]{1,0};
        mergeQueries[1] = new long[]{2,2};
        mergeQueries[2] = new long[]{0, 4};
        mergeQueries[3] = new long[]{1, 1};
        for (int i=0; i < mergeQueries.length ; i++){
            int firstTable = (int)mergeQueries[i][0];
            int secondTable = (int) mergeQueries[i][1];
            MergeTables.mergeTables(tables[firstTable], tables[secondTable]);
            System.out.println(MergeTables.getMax(tables));
        }

    }
    */

    public static void main(String[] args) {
        InputReader reader = new InputReader(System.in);
        OutputWriter writer = new OutputWriter(System.out);
        new MergeTables().run(reader, writer);
        writer.writer.flush();
    }

}

class Table{
    Table parent;
    int index;
    long numRows;
    int rank;

    Table(int index, long numRows){
        parent = this;
        this.index = index;
        this.numRows = numRows;
    }

    public String toString(){
        return String.valueOf(index);
    }

}

class InputReader {
    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public InputReader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream), 32768);
        tokenizer = null;
    }

    public String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public double nextDouble() {
        return Double.parseDouble(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}

class OutputWriter {
    public PrintWriter writer;

    OutputWriter(OutputStream stream) {
        writer = new PrintWriter(stream);
    }

    public void printf(String format, Object... args) {
        writer.print(String.format(Locale.ENGLISH, format, args));
    }
}


