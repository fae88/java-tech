package thread;

public class DoubleCheckLockInstance {

    public static volatile DoubleCheckLockInstance doubleCheckLockInstance;

    public static DoubleCheckLockInstance getDoubleCheckLockInstance() {

        if (doubleCheckLockInstance == null) {

            synchronized (DoubleCheckLockInstance.class) {
                if (doubleCheckLockInstance == null) {
                    doubleCheckLockInstance = new DoubleCheckLockInstance();
                }
            }
        }

        return doubleCheckLockInstance;
    }

    public static void main(String[] args) {

        new Thread(() -> {
            System.out.println(DoubleCheckLockInstance.getDoubleCheckLockInstance().hashCode());
        }).start();

        System.out.println(DoubleCheckLockInstance.getDoubleCheckLockInstance().hashCode());
    }
}
