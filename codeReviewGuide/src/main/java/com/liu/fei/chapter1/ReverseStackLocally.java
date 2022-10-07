package com.liu.fei.chapter1;

import java.util.Stack;

/**
 * @author liufei
 * @since 2022/10/8
 * <p>
 * 原地反转栈，不使用额外的数据结构和申请额外空间 --（其实不使用额外的空间，但是方法栈是可以用的啊，递归就会不断地压栈）
 * <p>
 * 一个栈依次压入 1、2、3、4、5，那么从栈顶到栈底分别为 5、4、3、2、1。
 * 将这个栈转置 后，从栈顶到栈底为 1、2、3、4、5，也就是实现栈中元素的逆序，但是只能用递归函数来实 现，不能用其他数据结构。
 **/
public class ReverseStackLocally {

    /**
     * 取出最后一个元素
     *
     * @param stack
     * @param <T>
     * @return
     */
    private static <T> T popLastElement(Stack<T> stack) {

        // 每一层递归都要将当层的元素取出来
        T eachElement = stack.pop();

        if (stack.isEmpty()) {
            return eachElement;
        } else {

            // 只有最后退出时才返回，所以需要将最后一个一直传递下去，直到退出取出最后的值
            T lastElement = popLastElement(stack);

            // 只取最后一个，所以其他的要压回栈中
            stack.push(eachElement);
            return lastElement;
        }
    }

    /**
     * 反转栈,注意取出来，和放进去是分开的：popLastElement是取出来且取的是最后一个，此方法就是再把取出来的放回去，进而实现反转
     *
     * @param stack
     * @param <T>
     */
    public static  <T> void reverseStack(Stack<T> stack) {

        if (stack == null) {
            throw new NullPointerException("stack should not be null");
        }

        if (stack.isEmpty()) {
            return;
        }
        T pop = popLastElement(stack);
        reverseStack(stack);
        stack.push(pop);

    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        System.out.println(stack);
        ReverseStackLocally.reverseStack(stack);
        System.out.println(stack);


    }

}
