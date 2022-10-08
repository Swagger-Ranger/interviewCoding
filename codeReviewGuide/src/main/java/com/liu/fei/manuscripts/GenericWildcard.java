package com.liu.fei.manuscripts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author liufei
 * @since 2022/10/9
 * <p>
 * 泛型通配符的使用
 **/
public class GenericWildcard {


    /**
     * 如果不使用 下界super <T extends Comparable<T>> 在传入子类时，无法使用max方法，因为子类并没有实现 Comparable 接口
     * Collection<? extends T> c 则是限定传入的集合都是只读的
     */
    public static <T extends Comparable<? super T>> T max(Collection<? extends T> c) {
        if (c.isEmpty()) {
            return null;
        }

        Iterator<? extends T> iter = c.iterator();
        T max = iter.next();

        while (iter.hasNext()) {
            T temp = iter.next();
            if (temp.compareTo(max) > 0) {
                max = temp;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        List<Fruit> fruits = new ArrayList<Fruit>();
        List<Apple> apples = new ArrayList<Apple>();

        fruits.add(new Apple(70.0));
        fruits.add(new RedFujiApple(85.15));
        fruits.add(new Orange(76.30));

        apples.add(new Apple(71.0));
        apples.add(new RedFujiApple(84.15));
        apples.add(new RedFujiApple(79.63));

        Fruit fruit = max(fruits);
        System.out.println(fruit);

        // Bound mismatch:
        // The generic method max(Collection<T>) of type Client is not applicable for the arguments (List<Apple>).
        // The inferred type Apple is not a valid substitute for the bounded parameter <T extends Comparable<T>>
         Apple apple = max(apples);
    }
}


class Fruit implements Comparable<Fruit> {
    private double calories;

    public Fruit(double calories) {
        this.calories = calories;
    }


    @Override
    public int compareTo(Fruit o) {
        return Double.compare(calories, o.calories);
    }


    @Override
    public String toString() {
        return calories + " kcal";
    }
}

class Apple extends Fruit {
    public Apple(double calories) {
        super(calories);
    }
}

class Orange extends Fruit {
    public Orange(double calories) {
        super(calories);
    }
}

class RedFujiApple extends Apple {
    public RedFujiApple(double calories) {
        super(calories);
    }
}