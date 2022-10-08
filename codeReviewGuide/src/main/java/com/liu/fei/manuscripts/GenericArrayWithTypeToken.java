package com.liu.fei.manuscripts;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author liufei
 * @since 2022/10/8
 *
 * 创建泛型数组
 * 使用java.lang.reflect.Array类的工具方法newInstance就能创建指定Class<T>对应类型的数组，
 * 最后再强制类型转换，将返回值强制转换为T[]类型即可。这是在Java中使用泛型数组的唯一办法。
 **/
public class GenericArrayWithTypeToken<T> {
    private T[] array;

    @SuppressWarnings("unchecked")
    public GenericArrayWithTypeToken(Class<T> type, int length) {
        array = (T[]) Array.newInstance(type, length);
    }

    public void put(int index, T item) {
        array[index] = item;
    }

    public T get(int index) {
        return array[index];
    }

    // expose the underlying representation
    public T[] rep() {
        return array;
    }

    public static void main(String[] args) {
        GenericArrayWithTypeToken<Integer> gai =
                new GenericArrayWithTypeToken<>(Integer.class, 10);
        // this now works
        Integer[] iArr = gai.rep();
        iArr[0] = 1;
        System.out.println(Arrays.toString(iArr));
    }
}
