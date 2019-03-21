package guavaByown;


import observer.Question;

/*
create by Jack on 2019/3/21
思考并总结装饰者模式和适配器模式的根本区别
装饰者 不管怎么包装，都是同一类；适配器则不一定是同类
装饰者侧重增强、拓展；适配器侧重兼容、转换
 */
public class Test {

    public static void main(String[] args) {
        GPer gper=GPer.getInstance();
        Teacher teacher=new Teacher("Rose");
        gper.register(teacher);
        gper.publishQuestion(new Question("小明","观察者适用于哪些场景？"));


    }
}
