package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.StringTokenizer;


class LinearProbingTable {
    private int keys = 0;
    private int m = 1000;
    private int p =  10000019 ; //prime no greater 10pow7
    private Contact[] items ;
    private long a;
    private long b;



    LinearProbingTable(){
        items = new Contact[m];
        generate_a_b();

    }

    public int size(){
        return keys;
    }

    private void generate_a_b(){
        Random rand = new Random();
        a = rand.nextInt(p-1) +1;
        b = rand.nextInt(p-1);
    }

    private int hash(int key, int m){
        long val = (a * key + b)%p;
        int result = (int)val % m;
        return result;
    }

    public void put(Contact contact){
        rehash();
        int index = hash(contact.phoneNum, m);
        while (items[index] != null){
            if (items[index].equals(contact)){
                break;
            }
            index++;
        }
        items[index] = contact;
    }

    String get(int key){
        int index = hash(key, m);
        while (items[index] != null){
           Contact contact = items[index];
           if (contact.phoneNum == key){
               return contact.name;
           }
           index ++;
        }
        return null;
    }

    void delete(int key){
        int index = hash(key, m);
        while (items[index] != null){
            if (items[index].phoneNum == key){
                items[index] = null;
                keys --;
                break;
            }
        }
    }

    void rehash(){
        float loadFactor = (float)keys/m;
        if (loadFactor > 0.9 ){
            generate_a_b();
            Contact[] temp = new Contact[m*2];
            for (int i=0; i< items.length; i++){
                Contact contact = items[i];
                int key = contact.phoneNum;
                int index = hash(key, m*2);
                while (temp[index] != null){
                    index ++;
                }
                temp[index] = contact;
            }
            items = temp;
        }
    }
}

class Contact{
    String name;
    int phoneNum;

    public Contact(int phoneNum, String name){
        this.name = name;
        this.phoneNum = phoneNum;
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if (this.getClass() != o.getClass()){
            return false;
        }
        Contact c = (Contact)o;
        if (this.phoneNum == c.phoneNum){
            return true;
        }
        return false;
    }
}

public class PhoneBook {
    private FastScanner in = new FastScanner();

    public static void main(String[] args) {
        new PhoneBook().processQueries();
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


    private void processQuery(Query query, LinearProbingTable hashIntegerTable) {
        if (query.type.equals("add")) {
            Contact c = new Contact(query.number, query.name);
            hashIntegerTable.put(c);
        } else if (query.type.equals("del")) {
            hashIntegerTable.delete(query.number);
        } else {
            String response = hashIntegerTable.get(query.number);
            if (response == null){
                response = "not found";
            }
            writeResponse(response);
        }
    }

    public void processQueries() {
        LinearProbingTable hashIntegerTable = new LinearProbingTable();
        int queryCount = in.nextInt();
        for (int i = 0; i < queryCount; ++i)
            processQuery(readQuery(), hashIntegerTable);
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
