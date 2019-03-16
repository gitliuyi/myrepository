package lazy;

/*
create by Jack on 2019/3/14
 */
public class InnerSingleton {

   private InnerSingleton(){
       //if(inner.lazy!=null){
           throw new RuntimeException("不允許創建多個實例");
       //}
   }

   public static final InnerSingleton getInstance(){
       return inner.lazy;
   }

   private static class inner{
       private static final InnerSingleton lazy=new InnerSingleton();

   }

}
