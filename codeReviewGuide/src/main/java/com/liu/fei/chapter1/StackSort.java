package com.liu.fei.chapter1;

import java.util.Stack;

/**
 * @author liufei
 * @since 2022/10/10
 * <p>
 * 用一个栈实现另一个栈的排序
 * 将要排序的栈记为 stack，申请的辅助栈记为 help。在 stack 上执行 pop 操作，弹出的元素 记为 cur。
 * 如果cur小于或等于help的栈顶元素，则将cur直接压入help;
 * 如果cur大于help的栈顶元素，则将help的元素逐一弹出，逐一压入stack，直到cur
 * 小于或等于 help 的栈顶元素，再将 cur 压入 help。
 **/
public class StackSort {


    /**
     * 将栈排序
     *
     * @param stack
     * @param desc  是否是降序
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void sort(Stack<T> stack, boolean desc) {
        if (stack == null || stack.isEmpty()) {
            return;
        }

        Stack<T> helpStack = new Stack<>();

        while (!stack.isEmpty()) {
            T cur = stack.pop();
            if (helpStack.isEmpty() || compare(desc, cur, helpStack.peek())) {
                // 如果满足直接压入
                helpStack.push(cur);
            } else {
                // 如果不满足，先弹出，直到满足再压入，然后继续
                while (!helpStack.isEmpty() && !compare(desc, cur, helpStack.peek())) {
                    stack.push(helpStack.pop());
                }
                helpStack.push(cur);
            }
        }

        // 再将数据压回去，因为返回是void的
        while (!helpStack.isEmpty()) {
            stack.push(helpStack.pop());
        }
    }

    private static <T extends Comparable<? super T>> boolean compare(boolean descOraAsc, T helpElement, T stackElement) {
        return (descOraAsc && stackElement.compareTo(helpElement) < 0) || (!descOraAsc && stackElement.compareTo(helpElement) > 0);
    }


    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(12);
        stack.push(3);
        stack.push(5);
        stack.push(2);
        stack.push(16);

        sort(stack, true);
        System.out.println(stack);
    }

}
