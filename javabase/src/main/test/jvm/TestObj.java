package jvm;

public class TestObj {

    private int hash;
    private int hash2;
    private int hash3;
    private int hash4;
    private transient int hash32;

    public static void setHash(int hash) {
    }

    public static class StrObj1 {
        private String a;
    }
    public static class StrObj2 {
        private String a;
        private String b;
    }
    public static class StrObj3 {
        private String a;
        private String b;
        private String c;
    }
    public static class StrObj4 {
        private String a;
        private String b;
        private String c;
        private String d;
    }
    public static class NumObj {
        private int a;
        private int b;
        private int c;
        private int d;
    }
    public static void main(String[] args) {
        TestObj obj = new TestObj();
        StrObj1 s1 = new StrObj1();
        StrObj2 s2 = new StrObj2();
        StrObj3 s3 = new StrObj3();
        StrObj4 s4 = new StrObj4();
        TestObj[] arrObj = new TestObj[10];
        NumObj num = new NumObj();
        //System.gc()  会出发 FullGC。
        System.gc();
    }
}
