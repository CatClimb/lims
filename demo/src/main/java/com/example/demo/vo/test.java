package com.example.demo.vo;

import com.example.demo.modules.entity.ComsumeEntity;
import com.example.demo.modules.entity.LabEntity;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
@Slf4j
public class test {
    public static void main(String[] args) throws ClassNotFoundException {
//        TableVO tableVO=new LabEntity();
//        ArrayList<LabEntity> labEntities = new ArrayList<>( );
//        labEntities.add(new LabEntity());
//        tableVO.setTableData(labEntities);
//        System.out.println(tableVO.getTableData().toString() );
//        TableVO tableVO=labEntity;aaP[[;
//        System.out.println(tableVO.toString() );
        Field[] declaredFields = ComsumeEntity.class.getDeclaredFields();
        for (Field field:declaredFields
        ) {
                log.info("xxxxxx"+field);
            if(field.getType()== List.class){
                log.info("xxxxxxxxxxxxxxxx"+field.getType());
                Type genericType = field.getGenericType( );
                log.info("xxxxxxxxxxxxxxxx"+genericType);
                ParameterizedType parameterizedType=(ParameterizedType) genericType;
                log.info("xxxxxxxxxdddddddxxxxxxx"+parameterizedType);
                Type actualTypeArgument = parameterizedType.getActualTypeArguments( )[0];
                log.info("wwwwwwwwwwwwwwwwwwwwwwwwwwwwww"+actualTypeArgument);
                String typeName = actualTypeArgument.getTypeName( );
                Class<?> aClass = Class.forName(typeName);
                log.info(""+aClass);
                Field[] declaredFields1 = aClass.getDeclaredFields( );
                for (Field field1:declaredFields1
                     ) {
                    log.info("wwwwssssssssssssssssssss"+field1);
                }


            }
        }
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