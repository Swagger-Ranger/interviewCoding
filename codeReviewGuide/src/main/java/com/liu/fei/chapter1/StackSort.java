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


    public static <T extends Comparable<? super T>> void sort(Stack<? extends T> stack, boolean desc) {

        if (stack == null || stack.isEmpty()) {
            return;
        }

        Stack<T> helpStack = new Stack<>();


        while (!stack.isEmpty()) {
            T pop = stack.pop();

            boolean b = (desc && pop.compareTo(helpStack.peek()) < 0) || (!desc && pop.compareTo(helpStack.peek()) > 0);


        }



    }


}
