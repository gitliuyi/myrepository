package dynamicproxy;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/*
create by Jack on 2019/3/17
 */
public class GPProxy {

    public static Object newProxyInstance(GPClassLoader loader,
                                          Class<?>[] interfaces,
                                          GPInvocationHandler h)
            throws IllegalArgumentException {
        try{
           // 生成java文件
            String src=generateSrc(interfaces);
            String filepath=GPProxy.class.getResource("").getPath().replace("%20"," ");
            File f=new File(filepath+"$Proxy0.java");
            FileWriter fw=new FileWriter(f);
            fw.write(src);
            fw.flush();
            fw.close();
            //将java文件编译成class文件
            JavaCompiler compiler= ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager=compiler.getStandardFileManager(null,null,null);
            Iterable iterator=manager.getJavaFileObjects(f);
            JavaCompiler.CompilationTask task=compiler.getTask(null,manager,null,null,null,iterator);
            task.call();
            manager.close();
            //将class文件转换成可执行的字节码文件
            Class proxyclass=loader.findClass("$Proxy0");

            Constructor c=proxyclass.getConstructor(GPInvocationHandler.class);
            return c.newInstance(h);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static String generateSrc(Class<?>[] interfaces) {
        StringBuffer sb=new StringBuffer();
        sb.append("package dynamicproxy;\n");
        sb.append("import staticproxy.Person;\n");
        sb.append("import java.lang.reflect.*;\n");
        sb.append("public class $Proxy0 implements "+interfaces[0].getName()+"{\n");
        sb.append("GPInvocationHandler h;\n");
        sb.append("public $Proxy0(GPInvocationHandler h){\n");
        sb.append("this.h=h;\n");
        sb.append("}\n");
        for(Method m:interfaces[0].getMethods()){
            sb.append("public "+m.getReturnType().getName()+" "+m.getName()+"(){\n");
            sb.append("try{\n");
            sb.append("Method m="+interfaces[0].getName()+".class.getMethod(\""+m.getName()+"\",new Class[]{});\n");
            sb.append("this.h.invoke(this,m,null);\n");
            sb.append("}catch(Throwable e){\n");
            sb.append("e.printStackTrace();\n");
            sb.append("}\n");
            sb.append("}");
        }

        sb.append("}");
        return sb.toString();
    }
}
