package com.itaiti.streams;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import lombok.Data;

public class StreamsApi {
	private static PrintStream out = System.out;

	@Test
	public void sortTest() {
		List<User> users = getUserList();
		// 过滤sex=1，并按年龄倒序排序
		/*
		 * List<User> list = users.parallelStream() .filter(t -> t.getSex() ==
		 * 1) .sorted(Comparator.comparing(User::getAge).reversed())
		 * .collect(Collectors.toList()); println(list);
		 */
		// 过滤sex=2，并按年龄升序排序
		List<Long> ids = users.parallelStream().filter(t -> t.getSex() == 2)
				.sorted(Comparator.comparingInt(User::getAge))
				// .map(t -> t.getId())
				.map(User::getId).collect(Collectors.toList());
		println(ids);

		// 计算年龄总和
		int totalAge = users.stream().filter(p -> p.getId() > 0).mapToInt(p -> p.getAge())
				// .count();
				.sum();
		println(totalAge);
	}

	@Test
	public void testIntStream() {
		// IntStream.of(new int[]{1,2,3}).forEach(System.out::println);
		IntStream.range(0, 3).forEach(System.out::println);
		// IntStream.rangeClosed(3, 6).forEach(System.out::println);
	}

	/**
	 * forEach 是terminal（末端）操作，因此它执行后，Stream 的元素就被“消费”掉了，无法对一个Stream 进行两次
	 * terminal运算。 下面的代码是错误的，会报java.lang.IllegalStateException: stream has
	 * already been operated upon or closed异常
	 */
	@Test
	public void testCollect() {
		Stream<String> stream = Stream.of("a", "b", "c");
		// 1.Array
		/*
		 * String[] strArray = stream.toArray(String[]::new);
		 * println("strArray:"+strArray[0]);
		 */

		// 2.Collection
		/*
		 * List<String> list1 = stream.collect(Collectors.toList());
		 * println("list1:"+list1); List<String> list2 =
		 * stream.collect(Collectors.toCollection(ArrayList::new));
		 * println("list2:"+list2);
		 */
		// Set<String> set = stream.collect(Collectors.toSet());
		// Stack stack = stream.collect(Collectors.toCollection(Stack::new));
		// println(stack);

		// 3.String
		String str = stream.collect(Collectors.joining("-")).toString();
		println(str);

	}

	@Test
	public void testMap() {
		// 转换大写
		List<String> wordList = Arrays.asList("hello", "world");
		List<String> output = wordList.stream().map(String::toUpperCase).collect(Collectors.toList());
		println(output);

		// 平方数
		List<Integer> nums = Arrays.asList(1, 2, 3, 4);
		List<Integer> squareNums = nums.stream().map(n -> n * n).collect(Collectors.toList());
		println(squareNums);

		// 一对多
		Stream<List<Integer>> inputStream = Stream.of(

				Arrays.asList(2, 3), Arrays.asList(1), Arrays.asList(4, 5, 6, 1));
		// flatMap 把 input Stream 中的层级结构扁平化，就是将最底层元素抽出来放到一起，最终 output 的新 Stream
		// 里面已经没有 List 了，都是直接的数字。
		Stream<Integer> outputStream = inputStream.flatMap((childList) -> childList.stream());
		List<Integer> list = outputStream
				// 降序
				.sorted((n1, n2) -> n2 - n1)
				// 升序
				// .sorted((n1, n2) -> n1-n2)
				// 去重
				.distinct()
				// 分页开始位置
				.skip(2)
				// 每页条数
				.limit(3)
				// .unordered()
				.collect(Collectors.toList());
		println(list);

	}

	@Test
	public void testFilter() {
		// 留下偶数
		// List<Integer> sixNums = Arrays.asList(1, 2, 3, 4, 5, 6);
		// List<Integer>evens = sixNums.stream().filter(n -> n%2 ==
		// 0).collect(Collectors.toList());
		Integer[] sixNums = { 1, 2, 3, 4, 5, 6 };
		Integer[] evens = Stream.of(sixNums).filter(n -> n % 2 == 0).toArray(Integer[]::new);
		// .toArray(Integer[]::new);
		println(evens);
	}

	@Test
	public void testForEach() {
		List<String> wordList = Arrays.asList("hello", "world");
		wordList.stream().forEach(p -> println(p));
	}

	@Test
	public void testPeek() {
		List<String> list = Stream.of("one", "two", "three", "four").filter(e -> e.length() > 3)
				.peek(e -> println("Filtered Value:" + e)).map(String::toUpperCase)
				.peek(e -> println("Mapped value:" + e)).collect(Collectors.toList());

		println(list);
	}

	@Test
	public void testFindFirst() {
		String strA = "abcd", strB = null;
		print(strA);
		print("");
		print(strB);
		println(getLength(strA));
		println(getLength(""));
		println(getLength(strB));
	}

	/**
	 * 这个方法的主要作用是把 Stream 元素组合起来。它提供一个起始值（种子），然后依照运算规则（BinaryOperator）， 和前面
	 * Stream 的第一个、第二个、第 n 个元素组合。从这个意义上说，字符串拼接、数值的 sum、min、max、average 都是特殊的
	 * reduce。
	 */
	@Test
	public void testReduce() {
		// 字符串连接，concat = "ABCD"
		String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
		println("concat:" + concat);
		// 求最小值，minValue = -3.0
		double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
		println("minValue:" + minValue);
		// 求和，sumValue = 10, 有起始值
		int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
		println("sumValue:" + sumValue);
		// 过滤，字符串连接，concat = "ace"
		concat = Stream.of("a", "B", "c", "D", "e", "F").filter(x -> x.compareTo("Z") < 0).reduce("", String::concat);
		println("concat:" + concat);

	}

	/**
	 * 这是一个有 10，000 个元素的 Stream，但在 short-circuiting 操作 limit 和 skip 的作用下 ，管道中
	 * map 操作指定的 getName() 方法的执行次数为 limit 所限定的 10 次，而最终返回结果在跳过前 3 个元素后只有后面 7
	 * 个返回。 有一种情况是 limit/skip 无法达到 short-circuiting 目的的，就是把它们放在 Stream
	 * 的排序操作后，原因跟 sorted 这个 intermediate 操作有关： 此时系统并不知道 Stream 排序后的次序如何，所以
	 * sorted 中的操作看上去就像完全没有被 limit 或者 skip 一样
	 */
	@Test
	public void testLimitAndSkip() {
		List<Person> persons = new ArrayList<>();
		for (int i = 1; i <= 10000; i++) {
			Person person = new Person(i, "name" + i);
			persons.add(person);
		}
		List<String> personList2 = persons.stream().map(Person::getName).limit(10).skip(3).collect(Collectors.toList());
		System.out.println(personList2);
	}

	// 分页取数
	@Test
	public void testLimitAndSkip2() {
		List<Person> persons = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			Person person = new Person(i, "name" + i);
			persons.add(person);
		}
		List<Person> personList2 = persons.stream().sorted((p1, p2) -> p1.getName().compareTo(p2.getName())).limit(2)
				.collect(Collectors.toList());
		println(personList2);
	}

	// 排序操作
	@Test
	public void testSorted() {
		List<Person> persons = Lists.newArrayList();
		for (int i = 1; i <= 5; i++) {
			Person person = new Person(i, "name" + i);
			persons.add(person);
		}
		List<Person> personList = persons.stream().limit(2).sorted((p1, p2) -> p1.getName().compareTo(p2.getName()))
				.collect(Collectors.toList());
		println(personList);
	}

	@Test
	public void testMin() {
		List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, -1);
		int minValue = list.stream().min(Comparator.comparingInt(Integer::intValue)).get();
		println("minValue:" + minValue);
	}

	@Test
	public void testMax() {
		List<Integer> list = Lists.newArrayList(7, 0, 10, -1, 9, 8);
		int maxValue = list.stream().max(Comparator.comparing(Integer::intValue)).get();
		println("maxValue:" + maxValue);
	}

	// 去重
	@Test
	public void testDistinct() {
		List<Integer> list = Lists.newArrayList(7, 0, 10, -1, 9, 8, 0, 1, 2, 10);
		List<Integer> sortedList = list.stream().distinct().sorted((p1, p2) -> p1 - p2).collect(Collectors.toList());
		println("sortedList:" + sortedList);
	}

	/**
	 * Stream 有三个 match 方法，从语义上说： allMatch：Stream 中全部元素符合传入的 predicate，返回 true
	 * anyMatch：Stream 中只要有一个元素符合传入的 predicate，返回 true noneMatch：Stream
	 * 中没有一个元素符合传入的 predicate，返回 true
	 */
	@Test
	public void testMatch() {
		List<Person> persons = new ArrayList<>();
		persons.add(new Person(1, "name" + 1, 10));
		persons.add(new Person(2, "name" + 2, 21));
		persons.add(new Person(3, "name" + 3, 34));
		persons.add(new Person(4, "name" + 4, 6));
		persons.add(new Person(5, "name" + 5, 55));

		boolean isAllAdult = persons.stream().allMatch(p -> p.getAge() > 18);
		println("isAllAdult:" + isAllAdult);
		boolean isThereAnyChild = persons.stream().anyMatch(p -> p.getAge() < 12);
		println("isThereAnyChild:" + isThereAnyChild);
	}

	@Test
	public void testGenerate() {
		Random seed = new Random(1000);
		Supplier<Integer> random = seed::nextInt;
		Stream.generate(random).limit(5).forEach(out::println);

		IntStream.generate(() -> (int) (System.nanoTime() % 100)).limit(10).forEach(out::println);
	}

	@Test
	public void testGroupingBy() {
		// 按照年齡分組 key=age value=List<Person>
		Random random = new Random();
		List<Person> persons = Lists.newArrayList();
		for (int i = 1; i < 100; i++) {
			persons.add(new Person(i, "name" + i, random.nextInt(100)));
		}
		Map<Integer, List<Person>> personGroups = persons.stream().collect(Collectors.groupingBy(Person::getAge));
		// println("personGroups:"+personGroups);
		for (Map.Entry<Integer, List<Person>> entry : personGroups.entrySet()) {
			println("key=" + entry.getKey() + "  value=" + entry.getValue().size());
		}
	}

	@Test
	public void testPartitioningBy() {
		// 分区 按照未成年人和成年人分区
		Random random = new Random();
		List<Person> persons = Lists.newArrayList();
		for (int i = 1; i < 100; i++) {
			persons.add(new Person(i, "name" + i, random.nextInt(100)));
		}
		Map<Boolean, List<Person>> children = persons.stream().collect(Collectors.partitioningBy(p -> p.getAge() < 18));
		println(children.get(true).size());
		println(children.get(false).size());
	}

	public static List<User> getUserList() {
		return Lists.newArrayList(new User(1L, "Tom", 1, 19, LocalDateTime.of(1998, 1, 6, 0, 0)),
				new User(2L, "Jack", 1, 18, LocalDateTime.of(1999, 2, 6, 0, 0)),
				new User(3L, "Rose", 2, 21, LocalDateTime.of(1996, 3, 6, 0, 0)),
				new User(4L, "Tim", 1, 22, LocalDateTime.of(1995, 4, 6, 0, 0)),
				new User(5L, "Aliya", 2, 20, LocalDateTime.of(1997, 5, 6, 0, 0)),
				new User(6L, "Make", 1, 18, LocalDateTime.of(1999, 6, 6, 0, 0)));
	}

	public static void println(Object obj) {
		out.println(JSON.toJSON(obj).toString());
	}

	public static void print(Object obj) {
		out.print(JSON.toJSON(obj).toString());
	}

	public static void print(String text) {
		// Java 8
		Optional.ofNullable(text).ifPresent(System.out::println);
		// Pre-Java 8
		if (text != null) {
			System.out.println(text);
		}
	}

	public static int getLength(String text) {
		// Java 8
		return Optional.ofNullable(text).map(String::length).orElse(-1);
		// Pre-Java 8
		// return if (text != null) ? text.length() : -1;
	};

	private class Person {
		public int no;
		private String name;
		private int age;

		public Person(int no, String name) {
			this.no = no;
			this.name = name;
		}

		public Person(int no, String name, int age) {
			this.no = no;
			this.name = name;
			this.age = age;
		}

		public String getName() {
			System.out.println(name);
			return name;
		}

		public int getAge() {
			return age;
		}
	}
}

@Data
class User {
	private Long id;
	private String name;
	private int sex;
	private int age;
	private LocalDateTime birthDay;

	public User(Long id, String name, int sex, int age, LocalDateTime birthDay) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.birthDay = birthDay;
	}

	public User() {
	}
}
