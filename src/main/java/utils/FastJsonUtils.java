package utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.Serializable;

/**
 * Created by alan on 17-5-14.
 */
public class FastJsonUtils {

    static {
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    }

    public static<T> String objectToString(T object){
         return JSON.toJSONString(object, SerializerFeature.WriteClassName,SerializerFeature.WriteDateUseDateFormat);
    }

    public static<T> T stringToObject(String context){
        return (T) JSON.parseObject(context,Object.class);
    }
}
