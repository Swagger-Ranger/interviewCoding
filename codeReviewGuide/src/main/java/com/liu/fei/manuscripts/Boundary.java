package com.liu.fei.manuscripts;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liufei
 * @since 2022/10/8
 **/
public class Boundary {




    static <T> T upperBoundedArg(List<? extends T> list, T item) {
        // The method add(capture#1-of ? extends T) in the type
        // List<capture#1-of ? extends T> is not applicable for the arguments (T)
        //! list.add(item);

        // ok, but senseless
        list.add(null);

        // 报错，要求是T的子类，放入T肯定不通过
//        list.add(item);

        // ok
        T value = list.get(0);
        return value;
    }

    static <T> Object lowerBoundedArg(List<? super T> list, T item) {
        // ok
        list.add(item);

        // Type mismatch: cannot convert from capture#4-of ? super T to T
        //! T t = holder.get();

        // ok, but type information has been lost
        Object obj = list.get(0);
        return obj;
    }


}

class A {
    public void methodA() { }
}

interface B {
    void methodB();
}

interface C {
    void methodC();
}

class D implements B, C {

    @Override
    public void methodB() {

    }


    @Override
    public void methodC() {

    }
}

class E extends A implements B, C {

    @Override
    public void methodB() {

    }


    @Override
    public void methodC() {

    }
}


class SingleBoundary<T extends A> {
    private T bounded;

    public void methodA() {
        bounded.methodA();
    }
}

// this won't work -- class must be first, then interfaces
// The type A is not an interface; it cannot be specified as a bounded parameter
// 如果所要限制的边界类型同时有类类型以及接口类型时，第一个边界类型必须是类类型，而从第二个边界类型开始以及之后的边界类型不能指定为类类型，但可以指定为接口类型；
// 这规则就像是Java的继承规则一样，不能多重继承，但能够实现多个接口，这很好理解。
//! class MultipleBoundaryA<T extends B & A> { }

class MultipleBoundaryB<T extends B & C> {
    private T bounded;

    public void methodB() {
        bounded.methodB();
    }

    public void methodC() {
        bounded.methodC();
    }
}

// as with inheritance, you can have only one
// concrete class but multiple interfaces
class MultipleBoundary<T extends A & B & C> {
    private T bounded;

    public void methodA() {
        bounded.methodA();
    }

    public void methodB() {
        bounded.methodB();
    }

    public void methodC() {
        bounded.methodC();
    }
}