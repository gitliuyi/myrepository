package deep;

import shallow.Jinggubang;

import java.io.*;
import java.util.List;

/*
create by Jack on 2019/3/16
 */
public class Sunwukong implements Serializable {

    private int id;

    private List list;

    private Jinggubang bang;

    public Sunwukong(int id, List list, Jinggubang bang) {
        this.id = id;
        this.list = list;
        this.bang = bang;
    }

    public Sunwukong clone(){
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            oos.flush();
            oos.close();

            ByteArrayInputStream bis=new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois=new ObjectInputStream(bis);
            return (Sunwukong)ois.readObject();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Jinggubang getBang() {
        return bang;
    }

    public void setBang(Jinggubang bang) {
        this.bang = bang;
    }
}
