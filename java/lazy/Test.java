package lazy;

import java.lang.reflect.Constructor;

/*
create by Jack on 2019/3/14
 */
public class Test {

    public static void main(String[] args) {

//        Thread t1=new Thread(new ExecutorThread());
//        Thread t2=new Thread(new ExecutorThread());
//        t1.start();
//        t2.start();
//        System.out.println("-----end----");
        try {
            Class<?>clazz=InnerSingleton.class;
            Constructor c = clazz.getDeclaredConstructor(null);
            c.setAccessible(true);
            Object o1=c.newInstance();

            Object o2=InnerSingleton.getInstance();
            System.out.println(o2);
            //System.out.println(o1==o2);

        }catch(Exception e){
            e.printStackTrace();
        }


    }

}
