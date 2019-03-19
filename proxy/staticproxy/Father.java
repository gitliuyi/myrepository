package staticproxy;

/*
create by Jack on 2019/3/17
 */
public class Father {

    private Person person;

    public Father(Person person){
        this.person=person;
    }
    public void findLove(){
        System.out.println("父亲物色对象");
        person.findLove();
        System.out.println("父亲完成任务");
    }

}
