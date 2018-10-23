package com.itaiti.share;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.Test;

import com.alibaba.fastjson.JSONArray;

import lombok.Data;

public class TreeMapTest {

	@Test
	public void test() {
		/*
		 * TreeMap<Double, Integer> weightMap = new TreeMap<Double, Integer>();
		 * weightMap.put(92.6, 11); weightMap.put(72.6, 13);
		 * System.out.println(weightMap.lastKey());
		 */

		List<Integer> providers = new ArrayList<>();
		providers.add(11);
		providers.add(13);

		List<SmsProviderStatistic> list = new ArrayList<>();
		list.add(new SmsProviderStatistic(16, 99.6));
		list.add(new SmsProviderStatistic(14, 72.6));
		list.add(new SmsProviderStatistic(15, 88.6));
		list.add(new SmsProviderStatistic(11, 62.6));
		list.add(new SmsProviderStatistic(12, 91.6));
		list.add(new SmsProviderStatistic(13, 83.6));

		for (int i = 0; i < 10; i++) {
			System.out.println(doSelect(providers, list));
			System.out.println("---------------------------");
		}

	}

	protected Integer doSelect(List<Integer> providers, List<SmsProviderStatistic> ps) {
		try {
			TreeMap<Double, Integer> weightMap = new TreeMap<Double, Integer>();
			ps.forEach(action -> {
				double lastWeight = weightMap.size() == 0 ? 0 : weightMap.lastKey();
				weightMap.put(action.getHealthScore() + lastWeight, action.getId());// 权重累加
			});
			System.out.println("weightMap:" + JSONArray.toJSONString(weightMap));
			double randomWeight = weightMap.lastKey() * Math.random();
			System.out.println("randomWeight:" + randomWeight);
			SortedMap<Double, Integer> tailMap = weightMap.tailMap(randomWeight, false);
			System.out.println("tailMap:" + JSONArray.toJSONString(tailMap));
			return weightMap.get(tailMap.firstKey());
		} catch (Exception e) {
			System.out.println("WeightRandomLoadBalance#doSelect异常:" + e.getMessage());
		}

		// 异常的情况下，走普通随机
		Random r = new Random();
		int index = r.nextInt(providers.size());
		int provider = providers.get(index);
		System.out.println("WeightRandomLoadBalance#doSelect按权重失败：" + provider);
		return provider;
	}
	
	@Test
	public void divideTest() {
		SmsProviderStatistic s = new SmsProviderStatistic();
		s.setId(11);
		s.setSendCount10(29);
		s.setSendCount30(118);
		s.setSendCount60(230);
		s.setReportCount10(27);
		s.setReportCount30(116);
		s.setReportCount60(231);
		System.out.println(calculateHealthScore(s));
		
	}

	public double calculateHealthScore(SmsProviderStatistic s) {
		double hs = divide(s.getReportCount60(), s.getSendCount60(), 2, null);
		double bs = divide(s.getReportCount30(), s.getSendCount30(), 2, null);
		double ms = divide(s.getReportCount10(), s.getSendCount10(), 2, null);
		double score = hs * 10 + bs * 30 + ms * 60 + 2;
		return format(score, 2);
	}

	public static double divide(double fz, double fm, int mc, double... more) {
		if (fz == 0 || fm == 0)
			return 0;
		BigDecimal bg1 = new BigDecimal(fz);
		BigDecimal bg2 = new BigDecimal(fm);
		BigDecimal result = new BigDecimal(0);

		if (more != null && more.length > 0) {
			for (int i = 0, len = more.length; i < len; i++) {
				if (i < len - 1) {
					result = result.divide(new BigDecimal(more[i]));
				} else {
					result = result.divide(new BigDecimal(more[i]), new MathContext(mc, RoundingMode.HALF_UP));
				}
			}
		} else {
			result = bg1.divide(bg2, new MathContext(mc, RoundingMode.HALF_UP));
		}

		return result.doubleValue();
	}

	public static double format(double value, int mc) {
		BigDecimal bg1 = new BigDecimal(value);
		double f1 = bg1.setScale(mc, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}
}

@Data
class SmsProviderStatistic {
	
	public SmsProviderStatistic() {}
	public SmsProviderStatistic(Integer id, double healthScore) {
		this.id = id;
		this.healthScore = healthScore;
	}

	/**
	 * 主键Id
	 */
	private Integer id;

	/**
	 * 10分钟发送数
	 */
	private int sendCount10;

	/**
	 * 30分钟发送数
	 */
	private int sendCount30;

	/**
	 * 60分钟发送数
	 */
	private int sendCount60;

	/**
	 * 10分钟成功数
	 */
	private int reportCount10;

	/**
	 * 30分钟成功数
	 */
	private int reportCount30;

	/**
	 * 60分钟成功数
	 */
	private int reportCount60;

	/**
	 * 健康得分
	 */
	private double healthScore;

	/**
	 * 最后更新时间戳(所有操作)
	 */
	private LocalDateTime timeStamp;
}
