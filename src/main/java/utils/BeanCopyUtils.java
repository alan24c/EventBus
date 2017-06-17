package utils;

import java.lang.reflect.Field;

/**
 * Created by alan on 17-6-17.
 */
public class BeanCopyUtils {

    public static Object beanCopy(Object fromBean,Class toBeanClass){
        Class formBeanClass = fromBean.getClass();
        Field[] fromFields = formBeanClass.getDeclaredFields();   // getFields()只能获取 public 方法

        Field[] toFields = toBeanClass.getDeclaredFields();
        Object toBean = null;
        try {
            toBean = toBeanClass.newInstance();                  // newInstance() 只能对 public class 调用
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        for(Field fromField : fromFields){
            for(Field toField : toFields){
                if(fromField.getName().equals(toField.getName())){

                    fromField.setAccessible(true);
                    toField.setAccessible(true);

                    Object val = null;
                    try {
                        val = fromField.get(fromBean);
                        toField.set(toBean,val);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }
            }
        }

        return toBean;
    }
}
