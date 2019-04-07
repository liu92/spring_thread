package com.thread.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * double基础计算工具类
 * 
 * @ClassName: CalculateDoubleUtil.java
 * @Description: double基础计算工具类
 * 
 * @author tangzhi
 * @version V1.0
 * @Date 2015-11-4 下午1:47:38
 */
public class CalculateDoubleUtil {

	/**
	 * 多个double 类型相加
	 * 
	 * @Title: doubleAdd
	 * @Description: 多个double 类型相加
	 * @param: @param d double类型数据
	 * @param: @return 计算类型
	 * @return: double
	 * @throws
	 * @author tangzhi
	 * @Date 2015-11-4 下午1:49:47
	 */
	public static double doubleAdd(double... d) {
		BigDecimal result = BigDecimal.ZERO;
		for (double n : d) {
			result = result.add(new BigDecimal(Double.toString(n)));
		}
		return result.doubleValue();
	}

	/**
	 * 多个double类型相加，默认RoundingMode.HALF_EVEN舍入模式
	 * 
	 * @Title: doubleAddWithScale
	 * @Description: 多个double类型相加
	 * @param: @param scale 保留几位小数
	 * @param: @param d 需要计算的数字
	 * @param: @return 计算结果
	 * @return: double
	 * @throws
	 * @author tangzhi
	 * @Date 2015-11-4 下午1:52:53
	 */
	public static double doubleAddWithScale(int scale, double... d) {
		BigDecimal result = BigDecimal.ZERO;
		for (double n : d) {
			result = result.add(new BigDecimal(Double.toString(n)));
		}

		/**
		 * 向最接近数字方向舍入的舍入模式，如果与两个相邻数字的距离相等，则向相邻的偶数舍入。如果舍弃部分左边的数字为奇数，则舍入行为同
		 * RoundingMode.HALF_UP；如果为偶数，则舍入行为同RoundingMode.HALF_DOWN。注意，在重复进行一系列计算时
		 * ，此舍入模式可以在统计上将累加错误减到最小。此舍入模式也称为“银行家舍入法”，主要在美国使用。此舍入模式类似于 Java 中对float
		 * 和double 算法使用的舍入策略。
		 */
		return result.setScale(scale, RoundingMode.HALF_EVEN).doubleValue();
	}
	
	/**
	 *  多个double类型相加,指定舍入模式
	 * @Title: doubleAdd
	 * @Description: 多个double类型相加,指定舍入模式
	 * @param: @param scale
	 * @param: @param mode
	 * @param: @param dd
	 * @param: @return   
	 * @return: double   
	 * @throws 
	 * @author        tangzhi
	 * @Date          2015-11-4 下午2:01:28
	 */
	public static double doubleAdd(int scale, RoundingMode mode, double... dd) {
		BigDecimal result = BigDecimal.ZERO;
		for (double n : dd) {
			result = result.add(new BigDecimal(Double.toString(n)));
		}
		return result.setScale(scale, mode).doubleValue();
	}

	/**
	 * double相加
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	
	
	/**
	 * 两个double类型数据相加，无精度限制，慎用
	 * @Title: doubleAdd
	 * @Description: 两个double类型数据相加
	 * @param: @param d1	double类型数据
	 * @param: @param d2	double类型数据
	 * @param: @return   	计算结果
	 * @return: double   
	 * @throws 
	 * @author        tangzhi
	 * @Date          2015-11-4 下午2:04:32
	 */
	public static double doubleAdd(double d1, double d2) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.add(bd2).doubleValue();

	}

	
	/**
	 * 两个double类型数据相加，需要精度限制，RoundingMode.HALF_EVEN舍入模式
	 * @Title: doubleAdd
	 * @Description: 两个double类型数据相加，需要精度限制，RoundingMode.HALF_EVEN舍入模式
	 * @param: @param d1	double 类型数据
	 * @param: @param d2	double 类型数据
	 * @param: @param scale	精度
	 * @param: @return   	计算结果
	 * @return: double   
	 * @throws 
	 * @author        tangzhi
	 * @Date          2015-11-4 下午2:05:35
	 */
	public static double doubleAdd(double d1, double d2, int scale) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.add(bd2).setScale(scale, RoundingMode.HALF_EVEN)
				.doubleValue();

	}

	
	/**
	 * 两个double类型数据相加，需要精度限制，需要指定舍入模式，
	 * @Title: doubleAdd
	 * @Description: 两个double类型数据相加，需要精度限制，需要指定舍入模式，
	 * @param: @param d1	double 类型数据
	 * @param: @param d2	double 类型数据
	 * @param: @param scale	精度
	 * @param: @param mode	舍入模式
	 * @param: @return   	计算结果
	 * @return: double   
	 * @throws 
	 * @author        tangzhi
	 * @Date          2015-11-4 下午2:07:42
	 */
	public static double doubleAdd(double d1, double d2, int scale,
			RoundingMode mode) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.add(bd2).setScale(scale, mode).doubleValue();

	}

	
	/**
	 * 两个double类型数据相减（d1-d2）,无精度，慎用
	 * @Title: doubleSubtract
	 * @Description: 两个double类型数据相减（d1-d2）,无精度，慎用
	 * @param: @param d1	double 类型数据
	 * @param: @param d2	double 类型数据
	 * @param: @return   	计算结果
	 * @return: double   
	 * @throws 
	 * @author        tangzhi
	 * @Date          2015-11-4 下午2:09:27
	 */
	public static double doubleSubtract(double d1, double d2) {
		return new BigDecimal(Double.toString(d1)).subtract(
				new BigDecimal(Double.toString(d2))).doubleValue();
	}

	
	/**
	 * 两个double类型数据相减（d1-d2）,需要精度，RoundingMode.HALF_EVEN舍入模式
	 * @Title: doubleSubtract
	 * @Description: 两个double类型数据相减（d1-d2）,需要精度，RoundingMode.HALF_EVEN舍入模式
	 * @param: @param d1	double 类型数据
	 * @param: @param d2	double 类型数据
	 * @param: @param scale	精度
	 * @param: @return   	计算结果
	 * @return: double   
	 * @throws 
	 * @author        tangzhi
	 * @Date          2015-11-4 下午2:39:47
	 */
	public static double doubleSubtract(double d1, double d2, int scale) {
		BigDecimal b = new BigDecimal(Double.toString(d1))
				.subtract(new BigDecimal(Double.toString(d2)));
		return b.setScale(scale, RoundingMode.HALF_EVEN).doubleValue();
	}

	
	/**
	 * 两个double类型数据相减（d1-d2）,需要精度，需要指定舍入模式
	 * @Title: doubleSubtract
	 * @Description: 两个double类型数据相减（d1-d2）,需要精度，需要指定舍入模式
	 * @param: @param d1	double 类型数据
	 * @param: @param d2	double 类型类型
	 * @param: @param scale	精度
	 * @param: @param mode	舍入模式
	 * @param: @return   	计算结果
	 * @return: double   
	 * @throws 
	 * @author        tangzhi
	 * @Date          2015-11-4 下午2:42:52
	 */
	public static double doubleSubtract(double d1, double d2, int scale,
			RoundingMode mode) {
		BigDecimal b = new BigDecimal(Double.toString(d1))
				.subtract(new BigDecimal(Double.toString(d2)));
		return b.setScale(scale, mode).doubleValue();
	}

	
	/**
	 * 两个double类型数据相除，无精度，慎用
	 * @Title: doubleDivide
	 * @Description: 两个double类型数据相除，无精度，慎用
	 * @param: @param d1	double类型数据，除数
	 * @param: @param d2	double类型数据，被除数
	 * @param: @return   	计算结果
	 * @return: double   
	 * @throws 
	 * @author        tangzhi
	 * @Date          2015-11-4 下午2:48:35
	 */
	public static double doubleDivide(double d1, double d2) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.divide(bd2).doubleValue();
	}

	/**
	 * 相除 取位 d1/d2 n.scale HALF_EVEN
	 * 
	 * @param d1
	 * @param d2
	 * @param scale
	 * @return
	 */
	
	/**
	 * 两个double类型数据相除，需要精度，RoundingMode.HALF_EVEN舍入模式
	 * @Title: doubleDivide
	 * @Description: 两个double类型数据相除，需要精度，RoundingMode.HALF_EVEN舍入模式
	 * @param: @param d1	double 类型数据，除数
	 * @param: @param d2	double 类型数据，被除数
	 * @param: @param scale	精度
	 * @param: @return   	计算结果
	 * @return: double   
	 * @throws 
	 * @author        tangzhi
	 * @Date          2015-11-4 下午2:53:50
	 */
	public static double doubleDivide(double d1, double d2, int scale) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		BigDecimal bd3 = bd1.divide(bd2, 15, RoundingMode.HALF_EVEN);
		return bd3.setScale(scale, RoundingMode.HALF_EVEN).doubleValue();
	}

	
	/**
	 * 两个double类型数据相除，需要精度，需要指定舍入模式
	 * @Title: doubleDivide
	 * @Description: 两个double类型数据相除，需要精度，需要指定舍入模式
	 * @param: @param d1	double类型数据，除数
	 * @param: @param d2	double类型数据，被除数
	 * @param: @param scale	精度
	 * @param: @param mode	舍入模式
	 * @param: @return   	计算结果
	 * @return: double   
	 * @throws 
	 * @author        tangzhi
	 * @Date          2015-11-4 下午2:55:07
	 */
	public static double doubleDivide(double d1, double d2, int scale,
			RoundingMode mode) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		BigDecimal bd3 = bd1.divide(bd2, 15, mode);
		return bd3.setScale(scale, mode).doubleValue();
	}

	
	/**
	 * 两个double类型数据相乘，无精度，无舍入模式
	 * @Title: doubleMultiply
	 * @Description: 两个double类型数据相乘，无精度，无舍入模式
	 * @param: @param d1	double类型数据
	 * @param: @param d2	double类型数据
	 * @param: @return   	计算结果
	 * @return: double   
	 * @throws 
	 * @author        tangzhi
	 * @Date          2015-11-4 下午2:57:03
	 */
	public static double doubleMultiply(double d1, double d2) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.multiply(bd2).doubleValue();
	}

	
	/**
	 * 两个double类型数据相乘，需要精度，RoundingMode.HALF_EVEN无舍入模式
	 * @Title: doubleMultiply
	 * @Description: 两个double类型数据相乘，需要精度，RoundingMode.HALF_EVEN舍入模式
	 * @param: @param d1	double类型数据
	 * @param: @param d2	double类型数据
	 * @param: @param scale	精度
	 * @param: @return   	计算结果
	 * @return: double   
	 * @throws 
	 * @author        tangzhi
	 * @Date          2015-11-4 下午2:59:34
	 */
	public static double doubleMultiply(double d1, double d2, int scale) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.multiply(bd2).setScale(scale, RoundingMode.HALF_EVEN)
				.doubleValue();
	}

	
	/**
	 * 两个double类型数据相乘，需要精度，需要指定舍入模式
	 * @Title: doubleMultiply
	 * @Description: 两个double类型数据相乘，需要精度，需要指定舍入模式
	 * @param: @param d1	计算结果
	 * @param: @param d2	计算结果
	 * @param: @param scale	精度
	 * @param: @param mode	舍入模式
	 * @param: @return   
	 * @return: double   
	 * @throws 
	 * @author        tangzhi
	 * @Date          2015-11-4 下午3:00:47
	 */
	public static double doubleMultiply(double d1, double d2, int scale,
			RoundingMode mode) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.multiply(bd2).setScale(scale, mode).doubleValue();
	}

	
	
	
}
