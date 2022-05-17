package com.example.demo.vo;

import com.example.demo.common.util.TokenUtil;
import com.example.demo.modules.entity.ConsumeEntity;
import com.example.demo.modules.service.ConsumeService;
import com.sun.javaws.security.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.core.io.InputStreamResource;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
@Slf4j
public class test {
    @Autowired

    public static void main(String[] args) throws ClassNotFoundException {
//        TableVO tableVO=new LabEntity();
//        ArrayList<LabEntity> labEntities = new ArrayList<>( );
//        labEntities.add(new LabEntity());
//        tableVO.setTableData(labEntities);
//        System.out.println(tableVO.getTableData().toString() );
//        TableVO tableVO=labEntity;aaP[[;
//        System.out.println(tableVO.toString() );
//        Field[] declaredFields = ConsumeEntity.class.getDeclaredFields();
//        for (Field field:declaredFields
//        ) {
//                log.info("xxxxxx"+field);
//            if(field.getType()== List.class){
//                log.info("xxxxxxxxxxxxxxxx"+field.getType());
//                Type genericType = field.getGenericType( );
//                log.info("xxxxxxxxxxxxxxxx"+genericType);
//                ParameterizedType parameterizedType=(ParameterizedType) genericType;
//                log.info("xxxxxxxxxdddddddxxxxxxx"+parameterizedType);
//                Type actualTypeArgument = parameterizedType.getActualTypeArguments( )[0];
//                log.info("wwwwwwwwwwwwwwwwwwwwwwwwwwwwww"+actualTypeArgument);
//                String typeName = actualTypeArgument.getTypeName( );
//                Class<?> aClass = Class.forName(typeName);
//                log.info(""+aClass);
//                Field[] declaredFields1 = aClass.getDeclaredFields( );
//                for (Field field1:declaredFields1
//                     ) {
//                    log.info("wwwwssssssssssssssssssss"+field1);
//                }

        String a="eyJ0eXAiOiJKV1MiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJMSU1TIFdlYiBQb2ludCIsImF1ZCI6IkNsaWVudCIsImxvZ2luTmFtZSI6IueUqOaIt-WQjTEyMyIsIkF1dGgiOiLnrqHnkIblkZgiLCJpc3MiOiJDYXRDbGltYiIsImV4cCI6MTY1MTYzMjg0NywiaWF0IjoxNjUxNTQ2NDQ3fQ.DVyG7nfr6XrOe7dCS4TdG8-pP_xK2rX4WE7U80-hrDk";
        String Auth= TokenUtil.getAuthByToken(a);
        System.out.println(Auth );
            }

            public void test() throws IOException {

            }

}


//import java.util.Set;
//import java.util.TreeSet;
//
//class Father {
//    public int i = 1;
//    public static String name="Father";
//    public void say(){
//        System.out.println("case Father");
//    }
//    public static void toPrint(){
//        System.out.println("static Father");
//    }
//}
//class Son extends Father{
//    public int a = 3;
//    public int i = 2;
//    public  String name="Son";
//    public void say(){
//        System.out.println("case Son");
//    }
//    public static void toPrint(){
//        System.out.println("static Son");
//    }
//}
//public class test {
//    public static void main(String[] args) {
//        Father f = new Son();
//        Son s=(Son)f;
//        System.out.println(f.i);//属性隐藏
//        f.say();//实例方法覆盖
//        System.out.println(f.name);//属性交叉隐藏
//        f.toPrint();//静态方法隐藏
//        //System.out.println(f.a); 子类特有部分被隐藏
//        System.out.println(s.i);
//        s.say();
//        System.out.println(s.name);
//        s.toPrint();
//        System.out.println(s.a);
//
//    }
//}