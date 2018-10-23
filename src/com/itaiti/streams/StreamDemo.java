package com.itaiti.streams;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

import lombok.Data;

public class StreamDemo {
private static List<Dishes> dishesList;
	
	static {
		dishesList = Lists.newArrayList();
		dishesList.add(new Dishes("´ó°è²Ë", 80));
		dishesList.add(new Dishes("ÍÁ¶¹Ë¿", 155));
		dishesList.add(new Dishes("¹¬±£¼¦¶¡", 500));
		dishesList.add(new Dishes("Ë®ÖóÓã", 400));
		dishesList.add(new Dishes("Ã«ÑªÍú", 1000));
	}
	
	@Test
	public void demo() {
		List<String> lowCaloricDishesName = dishesList.parallelStream()
				.filter(d -> d.getCalories() < 400)
				.sorted(comparing(Dishes::getCalories))
				.map(Dishes::getName)
				.collect(toList());
		System.out.println("lowCaloricDishesName:"+lowCaloricDishesName);
	}
}

@Data
class Dishes {
	private String name;
	private int calories;
	public Dishes() {}
	
	public Dishes(String name, int calories) {
		this.name = name;
		this.calories = calories;
	}
}












