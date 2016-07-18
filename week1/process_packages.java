package week1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Request {
    public Request(int arrival_time, int process_time) {
        this.arrival_time = arrival_time;
        this.process_time = process_time;
    }

    public int arrival_time;
    public int process_time;
}

class Response {
    public Response(boolean dropped, int start_time) {
        this.dropped = dropped;
        this.start_time = start_time;
    }

    public boolean dropped;
    public int start_time;
}

class Queue<Item>{
    Node first, last;
    int size;

    private class Node{
        Item item;
        Node next;
        Node(Item item){
            this.item = item;
        }
    }

    public void enqueue(Item item){
        if (item == null){
            throw new RuntimeException("Cannot insert null item");
        }
        Node oldLast = last;
        last = new Node(item);
        if (oldLast != null) {
            oldLast.next = last;
        }
        if (first == null){
            first = last;
        }
        size += 1;
    }

    public Item dequeue(){
        if (first == null){
            throw new RuntimeException("Cannot dequeue from empty queue");
        }
        Item item = first.item;
        first = first.next;
        if (first == null){
            last = first;
        }
        size -=1;
        return item;
    }

    public Item top(){
        if (first == null){
            throw new RuntimeException("Cannot dequeue from empty queue");
        }
        return first.item;
    }

    public Item last(){
        if (last == null){
            throw new RuntimeException("Cannot dequeue from empty queue");
        }
        return last.item;
    }

    public int size(){
        return this.size;
    }
    public boolean empty(){
        return size == 0;
    }
}

class Buffer {
    public Buffer(int size) {
        this.size_ = size;
        this.finish_time_ = new Queue<Integer>();
    }

    public Response Process(Request request) {
        while (!finish_time_.empty()){
            //removing from queue all finish_times of packets already processed before arrival time of new request
            if (request.arrival_time >= finish_time_.top()){
                finish_time_.dequeue();
            }else{
                break;
            }
        }
        if (finish_time_.size == this.size_){
            //buffer is full
            return new Response(true, -1);
        } else{
            int processing_time = -1;
            int finish_time = -1;
            if (finish_time_.size == 0){
                //queue is empty to request is processed immediately
                processing_time = request.arrival_time;
                finish_time = processing_time + request.process_time;
            } else{
                //get finish time from last element in queue
                processing_time = finish_time_.last();
                finish_time = finish_time_.last() + request.process_time;
            }
            finish_time_.enqueue(finish_time);
            return new Response(false, processing_time);
        }
    }

    private int size_;
    private Queue<Integer> finish_time_;
}

class process_packages {
    private static ArrayList<Request> ReadQueries(Scanner scanner) throws IOException {
        int requests_count = scanner.nextInt();
        ArrayList<Request> requests = new ArrayList<Request>();
        for (int i = 0; i < requests_count; ++i) {
            int arrival_time = scanner.nextInt();
            int process_time = scanner.nextInt();
            requests.add(new Request(arrival_time, process_time));
        }
        return requests;
    }

    private static ArrayList<Response> ProcessRequests(ArrayList<Request> requests, Buffer buffer) {
        ArrayList<Response> responses = new ArrayList<Response>();
        for (int i = 0; i < requests.size(); ++i) {
            responses.add(buffer.Process(requests.get(i)));
        }
        return responses;
    }

    private static void PrintResponses(ArrayList<Response> responses) {
        for (int i = 0; i < responses.size(); ++i) {
            Response response = responses.get(i);
            if (response.dropped) {
                System.out.println(-1);
            } else {
                System.out.println(response.start_time);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int buffer_max_size = scanner.nextInt();
        Buffer buffer = new Buffer(buffer_max_size);

        ArrayList<Request> requests = ReadQueries(scanner);
        ArrayList<Response> responses = ProcessRequests(requests, buffer);
        PrintResponses(responses);
    }
}
