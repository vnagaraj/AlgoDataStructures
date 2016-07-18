package week1;

/*
import org.testng.annotations.Test;

import java.util.Stack;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Unit test for CheckBrackets class
 */
public class CheckBracketsTest {
 /*
    @Test
    public void test1_using_java_lib(){
        String input = "[]";
        Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
        assertTrue(CheckBrackets.checkBrackets(input, opening_brackets_stack).equals("Success"));
    }

    @Test
    public void test2_using_java_lib(){
        String input = "{}[]";
        Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
        assertTrue(CheckBrackets.checkBrackets(input, opening_brackets_stack).equals("Success"));
    }

    @Test
    public void test3_using_java_lib(){
        String input = "[()]";
        Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
        assertTrue(CheckBrackets.checkBrackets(input, opening_brackets_stack).equals("Success"));
    }

    @Test
    public void test4_using_java_lib(){
        String input = "(())";
        Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
        assertTrue(CheckBrackets.checkBrackets(input, opening_brackets_stack).equals("Success"));
    }

    @Test
    public void test5_using_java_lib(){
        String input = "{[]}()";
        Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
        assertTrue(CheckBrackets.checkBrackets(input, opening_brackets_stack).equals("Success"));
    }

    @Test
    public void test6_using_java_lib(){
        String input = "{";
        Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
        assertTrue(CheckBrackets.checkBrackets(input, opening_brackets_stack).equals("1"));
    }

    @Test
    public void test7_using_java_lib(){
        String input = "{[}";
        Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
        assertTrue(CheckBrackets.checkBrackets(input, opening_brackets_stack).equals("3"));
    }

    @Test
    public void test8_using_java_lib(){
        String input = "foo(bar)";
        Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
        assertTrue(CheckBrackets.checkBrackets(input, opening_brackets_stack).equals("Success"));
    }

    @Test
    public void test9_using_java_lib(){
        String input = "foo(bar[i)";
        Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
        assertTrue(CheckBrackets.checkBrackets(input, opening_brackets_stack).equals("10"));
    }


    @Test
    public void test1_using_linklist_stack(){
        String input = "[]";
        LinkedListStack<Bracket> opening_brackets_stack = new LinkedListStack<>();
        assertTrue(CheckBrackets.checkBrackets(input, opening_brackets_stack).equals("Success"));
    }

    @Test
    public void test2_using_linklist_stack(){
        String input = "{}[]";
        LinkedListStack<Bracket> opening_brackets_stack = new LinkedListStack<>();
        assertTrue(CheckBrackets.checkBrackets(input, opening_brackets_stack).equals("Success"));
    }

    @Test
    public void test3_using_linklist_stack(){
        String input = "[()]";
        LinkedListStack<Bracket> opening_brackets_stack = new LinkedListStack<>();
        assertTrue(CheckBrackets.checkBrackets(input, opening_brackets_stack).equals("Success"));
    }

    @Test
    public void test4_using_linklist_stack(){
        String input = "(())";
        LinkedListStack<Bracket> opening_brackets_stack = new LinkedListStack<>();
        assertTrue(CheckBrackets.checkBrackets(input, opening_brackets_stack).equals("Success"));
    }

    @Test
    public void test5_using_linklist_stack(){
        String input = "{[]}()";
        LinkedListStack<Bracket> opening_brackets_stack = new LinkedListStack<>();
        assertTrue(CheckBrackets.checkBrackets(input, opening_brackets_stack).equals("Success"));
    }

    @Test
    public void test6_using_linklist_stack(){
        String input = "{";
        LinkedListStack<Bracket> opening_brackets_stack = new LinkedListStack<>();
        assertTrue(CheckBrackets.checkBrackets(input, opening_brackets_stack).equals("1"));
    }

    @Test
    public void test7_using_linklist_stack(){
        String input = "{[}";
        LinkedListStack<Bracket> opening_brackets_stack = new LinkedListStack<>();
        assertTrue(CheckBrackets.checkBrackets(input, opening_brackets_stack).equals("3"));
    }

    @Test
    public void test8_using_linklist_stack(){
        String input = "foo(bar)";
        LinkedListStack<Bracket> opening_brackets_stack = new LinkedListStack<>();
        assertTrue(CheckBrackets.checkBrackets(input, opening_brackets_stack).equals("Success"));
    }

    @Test
    public void test9_using_linklist_stack(){
        String input = "foo(bar[i)";
        LinkedListStack<Bracket> opening_brackets_stack = new LinkedListStack<>();
        assertTrue(CheckBrackets.checkBrackets(input, opening_brackets_stack).equals("10"));
    }

    @Test
    public void test1_using_dynamic_array_stack(){
        String input = "[]";
        DynamicArrayStack<Bracket> opening_brackets_stack = new DynamicArrayStack<>();
        assertTrue(CheckBrackets.checkBrackets(input, opening_brackets_stack).equals("Success"));
    }

    @Test
    public void test2_using_dynamic_array_stack(){
        String input = "{}[]";
        DynamicArrayStack<Bracket> opening_brackets_stack = new DynamicArrayStack<>();
        assertTrue(CheckBrackets.checkBrackets(input, opening_brackets_stack).equals("Success"));
    }

    @Test
    public void test3_using_dynamic_array_stack(){
        String input = "[()]";
        DynamicArrayStack<Bracket> opening_brackets_stack = new DynamicArrayStack<>();
        assertTrue(CheckBrackets.checkBrackets(input, opening_brackets_stack).equals("Success"));
    }

    @Test
    public void test4_using_dynamic_array_stack(){
        String input = "(())";
        DynamicArrayStack<Bracket> opening_brackets_stack = new DynamicArrayStack<>();
        assertTrue(CheckBrackets.checkBrackets(input, opening_brackets_stack).equals("Success"));
    }

    @Test
    public void test5_using_dynamic_array_stack(){
        String input = "{[]}()";
        DynamicArrayStack<Bracket> opening_brackets_stack = new DynamicArrayStack<>();
        assertTrue(CheckBrackets.checkBrackets(input, opening_brackets_stack).equals("Success"));
    }

    @Test
    public void test6_using_dynamic_array_stack(){
        String input = "{";
        DynamicArrayStack<Bracket> opening_brackets_stack = new DynamicArrayStack<>();
        assertTrue(CheckBrackets.checkBrackets(input, opening_brackets_stack).equals("1"));
    }

    @Test
    public void test7_using_dynamic_array_stack(){
        String input = "{[}";
        DynamicArrayStack<Bracket> opening_brackets_stack = new DynamicArrayStack<>();
        assertTrue(CheckBrackets.checkBrackets(input, opening_brackets_stack).equals("3"));
    }

    @Test
    public void test8_using_dynamic_array_stack(){
        String input = "foo(bar)";
        DynamicArrayStack<Bracket> opening_brackets_stack = new DynamicArrayStack<>();
        assertTrue(CheckBrackets.checkBrackets(input, opening_brackets_stack).equals("Success"));
    }

    @Test
    public void test9_using_dynamic_array_stack(){
        String input = "foo(bar[i)";
        DynamicArrayStack<Bracket> opening_brackets_stack = new DynamicArrayStack<>();
        assertTrue(CheckBrackets.checkBrackets(input, opening_brackets_stack).equals("10"));
    }
   */
}
