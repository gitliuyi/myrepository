package register;

import lazy.ExecutorThread;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.util.concurrent.CountDownLatch;

/*
create by Jack on 2019/3/15
 */
public class Test {

    //    public static void main(String[] args) {
//        EnumSingle o1=EnumSingle.getInstance();
//        EnumSingle o2=null;
//        o1.setData(new Object());
//
//        try {
//            FileOutputStream fos=new FileOutputStream("enum.obj");
//            ObjectOutputStream oos=new ObjectOutputStream(fos);
//            oos.writeObject(o1);
//            oos.flush();
//            oos.close();
//
//            FileInputStream fis=new FileInputStream("enum.obj");
//            ObjectInputStream ois=new ObjectInputStream(fis);
//            o2=(EnumSingle) ois.readObject();
//            System.out.println(o1.getData()==o2.getData());
//        }catch(Exception e ){
//            e.printStackTrace();
//        }
//    }

    //    public static void main(String[] args) {
//      try {
//          Class<?> clazz = EnumSingle.class;
//          Constructor c=clazz.getDeclaredConstructor(String.class,int.class);
//          c.setAccessible(true);
//          c.newInstance("Tom",666);
//      }catch(Exception e){
//          e.printStackTrace();
//      }
//    }
//    public static void main(String[] args) {
//
//        int n = 3;
//        CountDownLatch latch = new CountDownLatch(n);
//        for (int i = 0; i < n; i++) {
//            new Thread(new ExecutorThread(latch)).start();
//            latch.countDown();
//        }
//    }
}
