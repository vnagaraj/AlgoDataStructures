package week2;

import java.io.*;
import java.util.StringTokenizer;

class MinPQ<Item extends Comparable<Item>> {
    private Item[] array;
    private int size = 0;

    MinPQ(int capacity) {
        array = (Item[]) new Comparable[capacity + 1];
    }

    public Comparable getMin() {
        if (size == 0) {
            throw new RuntimeException("Priority Queue is empty");
        }
        return array[1];
    }

    public Item delMin() {
        if (size == 0) {
            throw new RuntimeException("Priority Queue is empty");
        }
        Item item = array[1];
        //swap with last element
        array[1] = array[size--];
        array[size + 1] = null;
        sink(1);
        return item;
    }

    public void insert(Item item) {
        if (item == null) {
            throw new RuntimeException("Cannot insert null item into priority queue");
        }
        array[++size] = item;
        swim(size);
    }

    private void swim(int index) {
        while (index > 1) {
            int parent = index / 2;
            if (array[index].compareTo(array[parent]) > 0) {
                break;
            }
            //swap
            Item temp = array[index];
            array[index] = array[parent];
            array[parent] = temp;
            index = parent;
        }
    }

    private void sink(int index) {
        int smaller_index = -1; //get smaller_index among the children
        while (true) {
            int children_index_1 = index * 2;
            int children_index_2 = index * 2 + 1;
            if (children_index_1 > size) {
                break;
            }
            if (children_index_2 > size) {
                smaller_index = children_index_1;
            } else {
                if (array[children_index_1].compareTo(array[children_index_2]) <= 0) {
                    smaller_index = children_index_1;
                } else {
                    smaller_index = children_index_2;
                }
            }
            if (array[smaller_index].compareTo(array[index]) < 0) {
                //swap values at index and smaller index
                Item temp = array[smaller_index];
                array[smaller_index] = array[index];
                array[index] = temp;
                index = smaller_index;
            } else {
                break;
            }
        }
    }
}

public class JobQueue {
    private int numWorkers;
    private long[] jobs;

    private WorkerStartTime[] assignedWorker;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new long[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i].worker + " " + assignedWorker[i].startTime);
        }
    }

    private static class WorkerStartTime implements Comparable<WorkerStartTime>{
        int worker;
        long startTime;

        WorkerStartTime(int worker, long startTime){
            this.worker = worker;
            this.startTime = startTime;
        }


        @Override
        public int compareTo(WorkerStartTime o) {
            if (this.startTime < o.startTime){
                return -1;
            }
            if (this.startTime > o.startTime){
                return 1;
            }
            if (this.worker < o.worker){
                return -1;
            }
            if (this.worker > o.worker){
                return 1;
            }
            return 0;
        }

        public String toString(){
            return "Worker " + this.worker + "StartTime " + this.startTime;
        }
    }

    private void assignJobs() {
        MinPQ<WorkerStartTime> pq = new MinPQ<WorkerStartTime>(numWorkers);
        for (int i =0; i < numWorkers; i++){
            pq.insert(new WorkerStartTime(i, 0));
        }
        assignedWorker = new WorkerStartTime[jobs.length];
        int jobCounter = 0;
        while (jobCounter < jobs.length){
            WorkerStartTime worker = pq.delMin();
            assignedWorker[jobCounter] = worker;
            pq.insert(new WorkerStartTime(worker.worker, worker.startTime + jobs[jobCounter]));
            jobCounter++;
        }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        writeResponse();
        out.close();
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
