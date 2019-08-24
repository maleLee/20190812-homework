package com.lee.homework.utils;

import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtil {

	
	private static final ObjectMapper mapper = new ObjectMapper();
	/**
	 * 把对象类型转换为Json字符串
	 * @param data
	 * @return
	 */
	public static String toJSONString(Object data) {
		try {
			String string = mapper.writeValueAsString(data);
			return string;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 把Json字符串转换为指定的对象类型
	 * @param jsonData
	 * @param beanType
	 * @return
	 */
	public static <T> T parseObject(String jsonData, Class<T> beanType) {
		try {
			T t = mapper.readValue(jsonData, beanType);
			return t;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 把Json字符串转换为指定泛型的List集合
	 * @param jsonData
	 * @param beanType
	 * @return
	 */
	public static <T> List<T> parseArray(String jsonData, Class<T> beanType) {
		JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, beanType);
		try {
			List<T> list = mapper.readValue(jsonData, javaType);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
