
package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


class DirectAddressingTable {
    private String[] items ;

    DirectAddressingTable(int size){
        items = new String[size];
    }

    void put(int key, String value){
        items[key] = value;
    }

    String get(int key){
        return items[key];
    }

    void delete(int key){
        items[key] = null;
    }


}

public class PhoneBookDA {
    private FastScanner in = new FastScanner();
    // Keep list of all existing (i.e. not deleted yet) contacts.
    //private List<Contact> contacts = new ArrayList<>();
    private String[] contacts = new String[100000];

    public static void main(String[] args) {
        new PhoneBookDA().processQueries();
    }

    private Query readQuery() {
        String type = in.next();
        int number = in.nextInt();
        if (type.equals("add")) {
            String name = in.next();
            return new Query(type, name, number);
        } else {
            return new Query(type, number);
        }
    }

    private void writeResponse(String response) {
        System.out.println(response);
    }


    private void processQuery(Query query, DirectAddressingTable directAddressingTable) {
        if (query.type.equals("add")) {
            directAddressingTable.put(query.number, query.name);
        } else if (query.type.equals("del")) {
            directAddressingTable.delete(query.number);
        } else {
            String response = directAddressingTable.get(query.number);
            if (response == null){
                response = "not found";
            }
            writeResponse(response);
        }
    }

    public void processQueries() {
        DirectAddressingTable table = new DirectAddressingTable(100000000);
        int queryCount = in.nextInt();
        for (int i = 0; i < queryCount; ++i)
            processQuery(readQuery(), table);
    }

    static class Query {
        String type;
        String name;
        int number;

        public Query(String type, String name, int number) {
            this.type = type;
            this.name = name;
            this.number = number;
        }

        public Query(String type, int number) {
            this.type = type;
            this.number = number;
        }
    }

    class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

