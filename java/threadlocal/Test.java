package threadlocal;

import lazy.ExecutorThread;

/*
create by Jack on 2019/3/16
 */
public class Test {

    public static void main(String[] args) {
        System.out.println( System.currentTimeMillis()+":"+Thread.currentThread()+":"+ThreadLocalSingleton.getInstance());
        System.out.println( System.currentTimeMillis()+":"+Thread.currentThread()+":"+ThreadLocalSingleton.getInstance());
        System.out.println( System.currentTimeMillis()+":"+Thread.currentThread()+":"+ThreadLocalSingleton.getInstance());
        System.out.println( System.currentTimeMillis()+":"+Thread.currentThread()+":"+ThreadLocalSingleton.getInstance());
        System.out.println( System.currentTimeMillis()+":"+Thread.currentThread()+":"+ThreadLocalSingleton.getInstance());

        new Thread(new ExecutorThread()).start();

        new Thread(new ExecutorThread()).start();
    }
}
