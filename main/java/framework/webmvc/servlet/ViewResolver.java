package framework.webmvc.servlet;

import lombok.Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
create by Jack on 2019/4/20
 */
@Data
public class ViewResolver {
    private String fileName;
    private File file;

    public ViewResolver(String fileName, File file) {
        this.fileName = fileName;
        this.file = file;
    }

    public String solve(ModelAndView mv) {
        try {
            RandomAccessFile ra=new RandomAccessFile(file,"r");
            String line="";
            StringBuffer sb=new StringBuffer();
            while((line=ra.readLine())!=null){
                line=new String(line.getBytes("ISO-8859-1"),"UTF-8");
                Matcher matcher= match(line);
                if(matcher.find()){
                    String result=matcher.group(1);
                    line=line.replaceAll("\\￥\\{([a-z]+)\\}",(String)mv.getModel().get(result)==null?"":(String)mv.getModel().get(result));
                }
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Matcher match(String line) {
        Pattern pattern=Pattern.compile("\\￥\\{([a-z]+)\\}");
        Matcher matcher=pattern.matcher(line);
        return  matcher;
    }
}