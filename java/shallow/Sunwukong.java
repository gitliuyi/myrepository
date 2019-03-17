package shallow;

import java.util.List;

/*
create by Jack on 2019/3/16
子类重写父类注意事项：遵循：”两同、两小、一大“原则

1、两同：方法名相同，形参列表相同

2、两小：子类方法返回值类型比父类方法返回值类型更小或相等，子类方法声明抛出的异常类比父类方法声明抛出的异常类更小或相等

3、一大：子类方法访问权限比父类方法访问权限更大或相等

4、特别注意的是：覆盖的方法和被覆盖的方法，要么都是类方法，要么都是实例方法，不能一个是类方法，一个是实例方法，否则会发生编译错误
 */
public class Sunwukong implements Cloneable{

    private int id;

    private List list;

    private Jinggubang bang;

    public Sunwukong(int id, List list, Jinggubang bang) {
        this.id = id;
        this.list = list;
        this.bang = bang;
    }

    public Sunwukong cloneown(){
        return new Sunwukong(id,list,bang);
    }

    @Override
    public Sunwukong clone() throws CloneNotSupportedException {
        return (Sunwukong)super.clone();
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
