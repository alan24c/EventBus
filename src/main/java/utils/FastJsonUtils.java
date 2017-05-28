package utils;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * Created by alan on 17-5-14.
 */
public class FastJsonUtils {

    public static String objectToString(Object object){
         return JSON.toJSONString(object);
    }
}
