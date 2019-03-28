package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
create by Jack on 2019/3/27
 */
public class ViewResolver {

    private String FileName;
    private java.io.File File;

    public ViewResolver(String fileName, java.io.File file) {
        FileName = fileName;
        File = file;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public java.io.File getFile() {
        return File;
    }

    public void setFile(java.io.File file) {
        File = file;
    }

    public String resolve(ModelAndView mv) {
        StringBuffer sb=new StringBuffer();
        String line="";
        try{
            RandomAccessFile ra=new RandomAccessFile(File,"r");
            while(null!=(line=ra.readLine())){
               line=new String(line.getBytes("ISO-8859-1"), "utf-8");
               Matcher matcher= match(line);
               if(matcher.find()){
                  String var= matcher.group(1);
                  String value= (String)mv.getModel().get(var);
                  line=line.replaceAll("\\$\\{([a-z]+)\\}",value);
               }
               sb.append(line);
            }
            return sb.toString();
        }catch(Exception e ){
            return null;
        }
    }

    private Matcher match(String line) {
        Pattern p=Pattern.compile("\\$\\{([a-z]+)\\}");
        Matcher m=p.matcher(line);
        return m;
    }
}