package com.liu.fei.manuscripts;

/**
 * @author liufei
 * @since 2022/10/8
 * 解决instanceof判断泛型对象的问题
 **/
public class ClassTypeCapture<T> {
    Class<?> kind;

    public ClassTypeCapture(Class<?> kind) {
        this.kind = kind;
    }

    public boolean isInstance(Object obj) {
        return kind.isInstance(obj);
    }

    public static void main(String[] args) {
        ClassTypeCapture<Building> ctt1 =
                new ClassTypeCapture<>(Building.class);
        System.out.println(ctt1.isInstance(new Building()));
        System.out.println(ctt1.isInstance(new House()));

        ClassTypeCapture<House> ctt2 =
                new ClassTypeCapture<>(House.class);
        System.out.println(ctt2.isInstance(new Building()));
        System.out.println(ctt2.isInstance(new House()));
    }

   static class Building { }
   static class House extends Building { }
}


