package com.liu.fei.chapter1;

import java.util.Stack;

/**
 * @author liufei
 * @create 2022/10/6
 * <p>
 * 【题目】
 * 实现一个特殊的栈，在实现栈的基本功能的基础上，再实现返回栈中最小元素的操作。
 * 【要求】
 * 1.pop、push、getMin 操作的时间复杂度都是 O(1)。
 * 2.设计的栈类型可以使用现成的栈结构。
 **/
public class GetMin<T extends Comparable<T>> {

    private final MinStack<T> stack;

    public GetMin(boolean timeOrSpace) {
        if (timeOrSpace) {
            stack = new TimeStack<>();
        } else {
            stack = new SpaceStack<>();
        }
    }

    public T pop() {
        return stack.pop();
    }

    public void push(T data) {
        stack.push(data);
    }

    public T getMin() {
        return stack.getMin();
    }

    private interface MinStack<T extends Comparable<T>> {

        /**
         * @return
         */
        T pop();

        void push(T data);

        T getMin();

    }


    /**
     * 时间优先的最小栈
     * 两个栈的数量都是对等的，数据栈每次插入一个数据，都在对于的最小栈中对于插入当前最小的数据
     */
    private static class TimeStack<T extends Comparable<T>> implements MinStack<T> {
        /**
         * 原始数据栈
         */
        private Stack<T> dataStack;
        /**
         * 最小值栈
         */
        private Stack<T> minStack;

        public TimeStack() {
            this.dataStack = new Stack<>();
            this.minStack = new Stack<>();
        }

        @Override
        public T pop() {
            if (this.minStack.isEmpty()) {
                throw new NullPointerException("stack is empty.");
            }
            minStack.pop();
            return dataStack.pop();
        }

        @Override
        public void push(T data) {

            if (data == null) {
                throw new NullPointerException("data should not be null");
            }
            dataStack.push(data);

            if (minStack.isEmpty()) {
                minStack.push(data);
            } else {
                T minNow = minStack.peek();
                if (minNow.compareTo(data) < 0) {
                    minStack.push(minNow);
                } else {
                    minStack.push(data);
                }
            }

        }

        @Override
        public T getMin() {
            if (this.minStack.isEmpty()) {
                throw new NullPointerException("stack is empty.");
            }
            return minStack.peek();

        }
    }

    /**
     * 空间优先的最小栈
     * 两个栈的数量都是对等的，数据栈每次插入一个数据，都在对于的最小栈中只在比当前最小值更小或者相等时的才才插入
     */
    private static class SpaceStack<T extends Comparable<T>> implements MinStack<T> {
        /**
         * 原始数据栈
         */
        private final Stack<T> dataStack;
        /**
         * 最小值栈
         */
        private final Stack<T> minStack;

        public SpaceStack() {
            this.dataStack = new Stack<>();
            this.minStack = new Stack<>();
        }

        @Override
        public T pop() {
            if (this.minStack.isEmpty()) {
                throw new NullPointerException("stack is empty.");
            }
            T min = minStack.peek();
            T pop = dataStack.pop();

            // 如果最小栈的数据和当前的相等则最小栈的也弹出，注意：最小栈的值不可能大于数据栈中的值
            if (min.compareTo(pop) == 0) {
                minStack.pop();
            }

            return pop;
        }

        @Override
        public void push(T data) {

            if (data == null) {
                throw new NullPointerException("data should not be null");
            }
            dataStack.push(data);

            if (minStack.isEmpty()) {
                minStack.push(data);
            } else {
                T minNow = minStack.peek();

                // 只有数据小于等于（注意一定要包含等于，因为弹出等于会弹出）当前的最小值时才插入
                if (minNow.compareTo(data) >= 0) {
                    minStack.push(data);
                }
            }

        }

        @Override
        public T getMin() {
            if (this.minStack.isEmpty()) {
                throw new NullPointerException("stack is empty.");
            }
            return minStack.peek();

        }
    }


    public static void main(String[] args) {
        System.out.println("aa ");
    }
}
