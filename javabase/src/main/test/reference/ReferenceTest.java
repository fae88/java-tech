package reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class ReferenceTest {

    public static void main(String[] args) {
        ReferenceQueue<ReferenceTest.Book> queue = new ReferenceQueue<>();
        SoftReference<ReferenceTest.Book> s = new SoftReference<>(new ReferenceTest.Book("soft", "12"), queue);
        WeakReference<ReferenceTest.Book> w = new WeakReference<>(new ReferenceTest.Book("weak", "13"), queue);
        PhantomReference<ReferenceTest.Book> p = new PhantomReference<>(new ReferenceTest.Book("pf", "14"), queue);

//        System.gc();
//
//        System.out.println(p.get());
//        System.out.println(s.get());
//        System.out.println(w.get());

        String b = "abc";
        String sb = new String("abc");

        System.out.println("abc".intern() == "abc");
        System.out.println("abc" == "abc");
        System.out.println(sb == "abc");


    }

    static class Book {
        String name;

        String price;

        Book(String name, String price) {
            this.name = name;
            this.price = price;
        }


        @Override
        protected void finalize() throws Throwable {
            System.out.println( name + " "  + price + " is destroyed");
        }
    }
}
