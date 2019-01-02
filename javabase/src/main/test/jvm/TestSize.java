package jvm;

public class TestSize {
 
    public static void main(String args[]) throws Exception {

        System.out.println(new Object[0].length);
        sizeOf();
    }
    private static Object types() {
//        return new Object();
//        return new Integer(1);
//        return new Short((short) 1);
//        return new Long(1);
//        return new Byte((byte) 0);
//        return new Character((char) 1);
//        return new Float(1);
//        return new Double(1);
//        return new Boolean(true);
        return new TestObj();
 
//        return new Object[0];
//        return new Object[1];
//        return new Object[2];
//        return new Object[3];
//        return new Object[4];
//        return new Object[5];
    }
    private static final Runtime rTime = Runtime.getRuntime();
 
    private static void sizeOf() {
 
        final int count = 100000;
        Object[] objs = new Object[count];
        runGC();
        long heapSizeBefore = usedMemory();   // before memory size
        for (int i = 0; i < count; i++) {
            objs[i] = types();
        }
        runGC();
        long heapSizeAfter = usedMemory();      // after memory size
        System.out.println("heapSizeBefore = " + heapSizeBefore);
        System.out.println("heapSizeAfter  = " + heapSizeAfter);
        Long size = Math.round((heapSizeAfter - heapSizeBefore) / (double) count);
        System.out.println(objs[0].getClass().getSimpleName() + "_size=" + size);
 
    }
    private static void runGC() {
        long usedMemOld = 0;
        long usedMemNew = usedMemory();
        while (usedMemOld != usedMemNew) {//循环多次
            rTime.runFinalization();//强制调用已经失去引用的对象的finalize方法
            rTime.gc();//进行垃圾收集
            Thread.yield();//使当前线程从执行状态（运行状态）变为可执行态（就绪状态），给jvm去执行gc
            usedMemOld = usedMemNew;
            usedMemNew = usedMemory();
        }
    }
    private static long usedMemory() {
        return rTime.totalMemory() - rTime.freeMemory();
    }

}
