package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

class Bracket {
    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean Match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}')
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }

    char type;
    int position;
}

class LinkedListStack<Item> extends Stack<Item>{

    Node first;

    public Item push(Item item){
      if (item == null){
          throw new RuntimeException("Cannot push null item");
      }
      Node oldFirst = first;
      first = new Node(item);
      first.next = oldFirst;
      return item;
    }

    public Item pop(){
        if (first == null){
            throw new RuntimeException("Cannot pop from empty stack");
        }
        Item item = first.item;
        first = first.next;
        return item;
    }

    public boolean empty(){
        return first == null;
    }


    private class Node {
        Item item;
        Node next;

        Node(Item item){
            this.item = item;
        }
    }
}

class DynamicArrayStack<Item> extends Stack<Item>{
    private Item[] array;
    int size ;

    DynamicArrayStack(){
        array = (Item[])new Object[1];
    }

    public Item push(Item item){
        if (item == null){
            throw new RuntimeException("Cannot push null item");
        }
        if (size == array.length){
            resize(2*size);
        }
        array[size++] = item;
        return item;
    }

    public Item pop(){
        if (size == 0){
            throw new RuntimeException("Cannot pop from empty stack");
        }
        Item item = array[--size];
        return item;
    }

    public boolean empty(){
        return size == 0;
    }

    private void resize(int capacity){
        Item[] temp = (Item[])new Object[capacity];
        for (int i=0 ;i < size; i++){
            temp[i] = array[i];
        }
        array = temp;
    }


}


class CheckBrackets {
    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();
        Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
        System.out.println(checkBrackets(text, opening_brackets_stack));
    }

    public static String checkBrackets(String text, Stack<Bracket> opening_brackets_stack){

        for (int position = 0; position < text.length(); ++position) {
            char next = text.charAt(position);
            if (!(next == '(' || next == '[' || next == '{' || next == ')' || next == ']' || next == '}')){
                continue;
            }
            if (next == '(' || next == '[' || next == '{') {
                // Process opening bracket, write your code here
                Bracket bracket = new Bracket(next, position);
                opening_brackets_stack.push(bracket);
            }
            else {
                if (opening_brackets_stack.empty()) {
                    return String.valueOf((position+1));
                }
                Bracket bracket =  opening_brackets_stack.pop();
                if (!bracket.Match(next)){
                    return String.valueOf((position+1));
                }
            }
        }
        if (opening_brackets_stack.empty()){
            return "Success";
        }
        else{
            return String.valueOf(opening_brackets_stack.pop().position +1);
        }
    }
}
