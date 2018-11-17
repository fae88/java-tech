package jdk;

public interface Factory {


    /**
     * 接口的静态方法
     */
    static void print() {
        System.out.println("this is the Factory's static print method");
    }

    /**
     * 接口的默认方法
     */
    default void print2() {
        System.out.println("this is the Factory's default print method");
    }
}
