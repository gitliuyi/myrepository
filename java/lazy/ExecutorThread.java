package lazy;

import register.ContainerSingle;
import threadlocal.ThreadLocalSingleton;

import java.util.concurrent.CountDownLatch;

/*
create by Jack on 2019/3/14
 */
public class ExecutorThread implements  Runnable {
   // public CountDownLatch latch=null;

//    public ExecutorThread(CountDownLatch latch){
//        this.latch=latch;
//    }
    @Override
    public void run() {
        //LazySingleton lazy=LazySingleton.getInstance();
       // DoubleSingleton lazy=DoubleSingleton.getInstance();
//        InnerSingleton lazy=InnerSingleton.getInstance();
//        try{
//            latch.await();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        ContainerSingle lazy=ContainerSingle.getInstance("register.ContainerSingle");
        ThreadLocalSingleton lazy= ThreadLocalSingleton.getInstance();
        System.out.println( System.currentTimeMillis()+":"+Thread.currentThread()+":"+lazy);
    }
}
