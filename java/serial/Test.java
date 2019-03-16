package serial;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*
create by Jack on 2019/3/15
 */
public class Test {

    public static void main(String[] args) {
        Object o1=SerialSingleton.getInstance();
        Object o2;
        try{
            FileOutputStream fos=new FileOutputStream("serial.obj");
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(o1);
            oos.flush();
            oos.close();

            FileInputStream fis=new FileInputStream("serial.obj");
            ObjectInputStream ois=new ObjectInputStream(fis);
            o2=ois.readObject();
            System.out.println(o1==o2);
        }catch (Exception e){
            e.printStackTrace();
        }



    }
}
