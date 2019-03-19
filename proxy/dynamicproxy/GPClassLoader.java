package dynamicproxy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/*
create by Jack on 2019/3/17
 */
public class GPClassLoader extends ClassLoader {

    private File classPathFile;

    public GPClassLoader() {
        String classPath=GPClassLoader.class.getResource("").getPath().replace("%20"," ");;
        this.classPathFile=new File(classPath);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String classname=GPClassLoader.class.getPackage().getName()+"."+name;
        if(classPathFile!=null){
            File classFile=new File(classPathFile,name.replaceAll("\\.","/")+".class");
            if(classFile.exists()){
                FileInputStream in=null;
                ByteArrayOutputStream out=null;
                try {
                    in=new FileInputStream(classFile);
                    out=new ByteArrayOutputStream();
                    byte[] buff=new byte[1024];
                    int len;
                    while((len=in.read(buff))!=-1){
                        out.write(buff,0,len);
                    }
                    return defineClass(classname,out.toByteArray(),0,out.size());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
       return null;
    }
}
