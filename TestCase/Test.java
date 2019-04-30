package com.gupaoedu.vip.spring.TestCase;

import com.gupaoedu.vip.spring.TestCase.entity.Reader;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
create by Jack on 2019/4/29
 */
public class Test {
    public static void main(String[] args) {
        Reader reader=new Reader();
        reader.setId(1);
        reader.setName("first_re");
        reader.setPhone("12378");
        List<?> list = select(reader);
        System.out.println(list);
    }

    private static List<?> select(Object obj) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Object> list = new ArrayList<>();
        Map<String,String> mapper=new HashMap<>();
        Map<String,String> mapper2=new HashMap<>();
        try {
            Class<?> clazz = obj.getClass();
            Table a=clazz.getAnnotation(Table.class);
            Field[] fields=clazz.getDeclaredFields();
            for (Field field : fields) {
                if(field.isAnnotationPresent(Column.class)){
                    mapper.put(field.getAnnotation(Column.class).name(),field.getName());
                    mapper2.put(field.getName(),field.getAnnotation(Column.class).name());
                }else{
                    mapper.put(field.getName(),field.getName());
                    mapper2.put(field.getName(),field.getName());
                }
            }
            String tableName=a.name();
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "liuyi", "123456");
            String sql = "select * from "+tableName;
            StringBuffer where=new StringBuffer(" where 1=1");
            for(Field field:fields){
                field.setAccessible(true);
                if(field.get(obj)!=null){
                    if(field.getType()==String.class){
                        where.append(" and "+mapper2.get(field.getName())+"="+"'"+field.get(obj)+"'");
                    }else if(field.getType()==int.class){
                        where.append(" and "+mapper2.get(field.getName())+"="+field.get(obj));
                    }
                }
            }
            sql=sql+where.toString();
            System.out.println(sql);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            int columnCount = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                Object instance = clazz.newInstance();
                for (int i = 1; i <= columnCount; i++) {
                   String columnName=rs.getMetaData().getColumnName(i);
                   Field field=clazz.getDeclaredField(mapper.get(columnName));
                   field.setAccessible(true);
                   field.set(instance,rs.getObject(i));
                }
                list.add(instance);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}