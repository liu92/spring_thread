package com.thread.enumtype;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * enum 工具类
 * 
 * @date 2015年11月04日 下午1:41:13
 * @author tzh
 */
public class EnumUtil {

	/**
	 * 根据枚举类型和序号获取指定的枚举对象
	 * 
	 * @param type
	 *            枚举类型
	 * @param code
	 *            序号
	 * @return 枚举对象
	 * @date 2015年11月04日 下午1:41:13
	 * @author tzh
	 */
	public static <T extends Enum<T>> EnumSet<T> decode(Class<T> type, int code) {
		Map<Integer, T> codeMap = new HashMap<Integer, T>();

		for (T val : EnumSet.allOf(type)) {
			codeMap.put(val.ordinal(), val);
		}

		EnumSet<T> result = EnumSet.noneOf(type);
		while (code != 0) {
			int ordinal = Integer.numberOfTrailingZeros(code);
			code ^= Integer.lowestOneBit(code);
			result.add(codeMap.get(ordinal));
		}

		return result;
	}

	/**
	 * 根据value获取enum对象
	 * 
	 * @return 返回enum对象
	 * @date 2015年11月04日 下午1:41:13
	 * @author tzh
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getEnumByValue(String value, Class<T> c) {
		// if (!ValidateUtil.isEmpty(value)) {
		for (Object o : c.getEnumConstants()) {
			if (o.toString().equals(value)) {
				return (T) o;
			}
		}
		// }
		return null;
	}

	/**
	 * 根据name获取enum对象
	 * 
	 * @return enum对象
	 * @date 2015年11月04日 下午1:41:13
	 * @author tzh
	 */
	public static <T extends Enum<T>> T getEnumByName(String Value, Class<T> type) {

		for (T val : EnumSet.allOf(type)) {

			if (val.name().equals(Value)) {
				return val;
			}
		}

		return null;
	}

	/**
	 * 将指定的枚举集合的那么取出，放置到list集合中
	 * 
	 * @param EnumSet
	 *            （enum集合）
	 * @return enum name集合
	 * @date 2015年11月04日 下午1:41:13
	 * @author tzh
	 */
	public static List<String> getEnumNamesByEnumSet(EnumSet<?> set) {
		List<String> list = new ArrayList<String>();

		if (null == set || set.size() == 0) {
			return list;
		}
		for (Enum<?> enum1 : set) {
			list.add(enum1.name());
		}

		return list;
	}

	/**
	 * 将枚举转换成map数据
	 * 
	 * @param <T>
	 * @param type
	 *            enum class 枚举class
	 * @return Map<name,String> 返回map<枚举name,枚举toString>
	 * @date 2015年11月04日 下午1:41:13
	 * @author tzh
	 */
	public static <T extends Enum<T>> Map<String, String> getEnumMap(Class<T> type) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (Enum<?> e : EnumSet.allOf(type)) {
			map.put(e.name(), e.toString());
		}
		return map;
	}

	/**
	 * 获取枚举对象集合
	 * 
	 * @param clazz
	 *            enum class
	 * @return 符合要求的枚举对象集合
	 * @date 2015年11月04日 下午1:41:13
	 * @author tzh
	 */
	public static <T extends Enum<T>> List<T> getEnumList(Class<T> clazz) {
		List<T> tList = new ArrayList<T>();
		for (T val : EnumSet.allOf(clazz)) {
			tList.add(val);
		}
		return tList;
	}

	/**
	 * 获取枚举对象集合
	 * 
	 * @param clazz
	 *            enum class
	 * @return 符合要求的枚举对象集合
	 * @date 2015年11月04日 下午1:41:13
	 * @author tzh
	 */
	public static <T extends Enum<T>> List<T> getEnumList(EnumSet<T> set) {
		List<T> tList = new ArrayList<T>();
		for (T enum1 : set) {
			tList.add(enum1);
		}
		return tList;
	}

	/**
	 * 根据名称获取enum中属性值，获取枚举对象集合
	 * 
	 * @param clazz
	 *            enum class
	 * @param fieldValue
	 *            enum 的属性值
	 * @return 符合要求的枚举对象集合
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @date 2015年11月04日 下午1:41:13
	 * @author tzh
	 */
	public static <T extends Enum<T>> List<T> getEnumByFiledName(Class<T> clazz, Object fieldValue) throws IllegalArgumentException, IllegalAccessException {
		List<T> tList = new ArrayList<T>();
		for (T val : EnumSet.allOf(clazz)) {
			Class<?> c = val.getClass();
			Field[] fields = c.getDeclaredFields();
			for (Field f : fields) {
				f.setAccessible(true);
				Object fValue = f.get(val);
				if (fValue.equals(fieldValue)) {
					tList.add(val);
					continue;
				}
			}
		}
		return tList;
	}

	/**
	 * 将指定的枚举数组中的name取出，放置到字符串数组中
	 * 
	 * @param Enum
	 *            [] （enum数组）
	 * @return String[] name数组
	 * @date 2015年12月22日 下午10:41:13
	 * @author tzh
	 */
	public static String[] getEnumNamesByEnumArray(Enum[] set) {

		if (null == set || set.length == 0) {
			return null;
		}
		String[] names = new String[set.length];
		int i = 0;
		for (Enum<?> enum1 : set) {
			names[i] = enum1.name();
			i++;
		}

		return names;
	}

	/**
	 * 将enumSet 转换成枚举数组
	 * 
	 * @Description: 将enumSet 转换成枚举数组
	 * @param set
	 * @return
	 * @return Enum[]
	 * @throws
	 * @author tangzhi
	 * @date 2016-2-18
	 */
	public static <T extends Enum<T>> T[] getEnumArrayByEnumSet(EnumSet<T> set, T[] enums) {
		if (null == set || set.size() == 0) {
			return null;
		}
		if (null == enums || enums.length == 0) {
			return null;
		}
		set.toArray(enums);
		return enums;
	}

	/**
	 * 根据名称获取enumSet
	 * 
	 * @Description: 根据名称获取enumSet
	 * @param name
	 * @param clazz
	 * @return
	 * @return EnumSet<?>
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws
	 * @author tangzhi
	 * @date 2016-5-22
	 */
	public static EnumSet<?> getEnumSetByName(String name, Class<?> clazz) throws IllegalArgumentException, IllegalAccessException {
		Field[] fields = clazz.getDeclaredFields();
		for (Field f : fields) {// 多余的属性处理
			f.setAccessible(true);
			if (f.getType().equals(EnumSet.class) && f.getName().equals(name)) {
				return (EnumSet<?>) f.get(clazz);
			}
		}

		return null;
	}

	/**
	 * 根据name获取enum对象
	 * 
	 * @return enum对象
	 * @date 2015年11月04日 下午1:41:13
	 * @author tzh
	 * @throws Exception
	 */
	public static Map<String, Object> getMapByName(String Value, Class type) throws Exception {

		Enum source = getEnumByName(Value, type);

		Map<String, Object> data = new LinkedHashMap<String, Object>();

		data.put("name", source.name());
		Class<?> c = source.getClass();
		Field[] fields = c.getDeclaredFields();
		for (Field f : fields) {// 多余的属性处理
			boolean isStatic = Modifier.isStatic(f.getModifiers());
			if (!isStatic) {
				f.setAccessible(true);
				Object fValue = f.get(source);
				if (!f.isEnumConstant()) {
					data.put(f.getName(), fValue);
				}
			}
		}

		return data;

	}

	


	public static <T> Map<Object, Object> EnumToMap(Class<T> enumT, Object value) {
		Map<Object, Object> enummap = new HashMap<Object, Object>();
		
//		T[] enums = enumT.getEnumConstants();
//		
//		if (enums == null || enums.length <= 0) {
//			return enummap;
//		}
//		
		String valueMathod = "getValue"; //默认接口value方法
		String nameMathod = "name";//默认接口description方法
	
//		for (int i = 0, len = enums.length; i < len; i++) {
//			T tobj = enums[i];
			try {
				
				Object resultValue = getMethodValue(valueMathod, value); //获取value值
				if ("".equals(resultValue)) {
//					continue;
				}
				Object resultDes = getMethodValue(nameMathod, value); //获取description描述值
				if ("".equals(resultDes)) { //如果描述不存在获取属性值
//					resultDes = tobj;
				}
				enummap.put(resultDes + "", resultValue);
			} catch (Exception e) {
				e.printStackTrace();
			}
//		}
		return enummap;
	}
	

	/**
	 * 根据反射，通过方法名称获取方法值，忽略大小写的
	 * @param methodName
	 * @param obj
	 * @param args
	 * @return return value
	 */
	private static <T> Object getMethodValue(String methodName, T obj,
			Object... args) {
		Object resut = "";
		// boolean isHas = false;
		try {
			/********************************* start *****************************************/
			Method[] methods = obj.getClass().getMethods(); //获取方法数组，这里只要共有的方法
			if (methods.length <= 0) {
				return resut;
			}
			// String methodstr=Arrays.toString(obj.getClass().getMethods());
			// if(methodstr.indexOf(methodName)<0){ //只能粗略判断如果同时存在 getValue和getValues可能判断错误
			// return resut;
			// }
			// List<Method> methodNamelist=Arrays.asList(methods); //这样似乎还要循环取出名称
			Method method = null;
			for (int i = 0, len = methods.length; i < len; i++) {
				if (methods[i].getName().equalsIgnoreCase(methodName)) { //忽略大小写取方法
					// isHas = true;
					methodName = methods[i].getName(); //如果存在，则取出正确的方法名称
					method = methods[i];
					break;
				}
			}
	
			/*************************** end ***********************************************/
			// Method method = obj.getClass().getDeclaredMethod(methodName); ///确定方法
			if (method == null) {
				return resut;
			}
			resut = method.invoke(obj, args); //方法执行
			if (resut == null) {
				resut = "";
			}
			return resut; //返回结果
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resut;
	}
}
