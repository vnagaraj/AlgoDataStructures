package week3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Hash table implemented using chaining scheme
 */

class ChainingHashTable{
    private Node[] nodes;
    private int prime = 1000000007;
    private int multiplier = 263;

    ChainingHashTable(int m){
        nodes = new Node[m];
    }

    public void add(String key){
        int index = hashFunc(key);
        Node first = nodes[index];
        if (first == null){
            nodes[index] = new Node(key);
        } else{
            //find the key and value
            Node foundNode = findKey(first, key);
            if (foundNode == null){
                //key not found in chain
                //so insert in front of chain
                Node oldFirst = first;
                first = new Node(key);
                first.next = oldFirst;
                nodes[index] = first;
            }
        }
    }

    public ArrayList<String> check(int index){
        ArrayList<String> result = new ArrayList<String>();
        Node first = nodes[index];
        while (first != null){
            result.add(first.key);
            first = first.next;
        }
        return result;
    }

    public boolean find(String key){
        int index = hashFunc(key);
        return (findKey(nodes[index], key)!= null);
    }

    public void del(String key){
        int index = hashFunc(key);
        Node first = nodes[index];
        Node prev = null;
        Node current = first;
        while (current != null){
            if (current.key.equals(key)){
                if (prev != null){
                    prev.next = current.next;
                }else{
                   //this is first element
                    first = first.next;
                    nodes[index] = first;
                }
                break;
            }
            prev = current;
            current = current.next;
        }
    }

    private Node findKey(Node current, String key){
        while (current != null){
            if (current.key.equals(key)){
                return current;
            }
            current = current.next;
        }
        return null;
    }

    private int hashFunc(String s) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = (hash * multiplier + s.charAt(i)) % prime;
        return (int)hash % nodes.length;
    }

    private class Node{
        String key;
        Node next;

        Node(String key){
            this.key = key;
        }
    }

}

public class HashChains {

    private FastScanner in;
    private PrintWriter out;
    // store all strings in one list
    private List<String> elems;
    // for hash function
    private int bucketCount;
    private int prime = 1000000007;
    private int multiplier = 263;

    public static void main(String[] args) throws IOException {
        new HashChains().processQueries();
    }

    private int hashFunc(String s) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = (hash * multiplier + s.charAt(i)) % prime;
        return (int)hash % bucketCount;
    }

    private Query readQuery() throws IOException {
        String type = in.next();
        if (!type.equals("check")) {
            String s = in.next();
            return new Query(type, s);
        } else {
            int ind = in.nextInt();
            return new Query(type, ind);
        }
    }

    private void writeSearchResult(boolean wasFound) {
        out.println(wasFound ? "yes" : "no");
        // Uncomment the following if you want to play with the program interactively.
        // out.flush();
    }

    private void processQuery(Query query, ChainingHashTable table) {
        switch (query.type) {
            case "add":
                table.add(query.s);
                break;
            case "del":
                table.del(query.s);
                break;
            case "find":
                if (table.find(query.s)){
                    out.println("yes");
                }else{
                    out.println("no");
                }
                break;
            case "check":
                ArrayList<String> elems = table.check(query.ind);
                for (String cur : elems)
                        out.print(cur + " ");
                out.println();
                // Uncomment the following if you want to play with the program interactively.
                // out.flush();
                break;
            default:
                throw new RuntimeException("Unknown query: " + query.type);
        }
    }

    public void processQueries() throws IOException {
        elems = new ArrayList<String>();
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        bucketCount = in.nextInt();
        int queryCount = in.nextInt();
        ChainingHashTable table = new ChainingHashTable(bucketCount);
        for (int i = 0; i < queryCount; ++i) {
            processQuery(readQuery(), table);
        }
        out.close();
    }

    static class Query {
        String type;
        String s;
        int ind;

        public Query(String type, String s) {
            this.type = type;
            this.s = s;
        }

        public Query(String type, int ind) {
            this.type = type;
            this.ind = ind;
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
