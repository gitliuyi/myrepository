package dynamic;

import staticproxy.Person;

/*
create by Jack on 2019/3/18
 */
public class Girl implements Person {
    @Override
    public void findLove() {
        System.out.println("高富帅");
    }

    @Override
    public void findJob() {
        System.out.println("发现工作");
    }
}
