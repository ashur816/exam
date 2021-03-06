package com.lydia.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/29.
 */
public class JsonUtils {
    private static ObjectMapper objectMapper = new JsonMapper();

    /**
     * @Description: 对象转换成json
     * @param obj  对象
     * @return json
     * @throws IOException
     */
    public static String translateToJson(Object obj) throws IOException {
        return objectMapper.writeValueAsString(obj);
    }

    /**
     * @Description: json 转换成对象
     * @param json
     * @param clazz  对象的 class
     * @return   对象
     * @throws IOException
     */
    public static <T> T readValue(String json, Class<T> clazz) throws IOException {
        return objectMapper.readValue(json, clazz);
    }

    /**
     * @Description: json 转换成对象
     * @param json
     * @param type
     * @return
     * @throws IOException
     */
    public static <T> T readValueByType(String json, TypeReference<?> type) throws IOException {
        return objectMapper.readValue(json, type);
    }

    /**
     * @Description: 获取json 的属性值
     * @param json
     * @param name   属性名称
     * @return       属性值
     * @throws IOException
     */
    public static String readValueByName(String json, String name) throws IOException {
        Map<?, ?> map = objectMapper.readValue(json, Map.class);
        Object object = map.get(name);
        return object == null ? null : object.toString();
    }

    /**
     * @Description: 获取json 的属性 map
     * @param json
     * @return  map
     * @throws IOException
     */
    public static Map<?, ?> readMap(String json) throws IOException {
        Map<?, ?> map = objectMapper.readValue(json, Map.class);
        return map;
    }


}
