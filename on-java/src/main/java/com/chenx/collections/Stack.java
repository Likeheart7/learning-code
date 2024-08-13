package com.chenx.collections;

import java.util.ArrayDeque;
import java.util.Deque;

/*
基于Deque实现的Stack
Stack Method Equivalent Deque Method
push(e)     addFirst(e)
pop()       removeFirst()
peek()      getFirst()
 */
public class Stack<T> {
    private Deque<T> stack = new ArrayDeque<>();

    /**
     * 向堆栈栈顶放入一个元素
     *
     * @param element 要放入的元素
     */
    public void push(T element) {
        stack.push(element);
    }

    /**
     * 从栈顶取一个元素，但不从栈中移除该元素
     *
     * @return 取出的元素
     */
    public T peek() {
        return stack.peek();
    }


    /**
     * 从栈顶取出并移除一个元素
     *
     * @return 取出的元素
     */
    public T pop() {
        return stack.pop();
    }

    /**
     * 判断堆栈是否为空
     *
     * @return 堆栈为空返回true，否则返回false
     */
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public String toString() {
        return super.toString();
    }


    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.peek());
        System.out.println(stack.pop());
    }
}
