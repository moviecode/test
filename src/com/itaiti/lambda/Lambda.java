package com.itaiti.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

/**
 * JDK 1.8������ lambda ʹ��
 * 
 * @author itaiti
 * @email fei.zhang@mtime.com
 * @date 2016��10��13�� ����5:31:09
 * @version V1.0
 */
public class Lambda {

	/*
	 * public static void main(String[] args) { // testList(); // testList2();
	 * //testRunnable(); System.out.printf("%s,%s", "aa", "bb"); }
	 */

	@Test
	public static void testList() {
		List<Integer> ps = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			ps.add(i);
		}
		ps.forEach(p -> System.out.println(p));
	}

	@Test
	public static void testList2() {
		String[] atp = { "Rafael Nadal", "Novak Djokovic", "Stanislas Wawrinka", "David Ferrer", "Roger Federer",
				"Andy Murray", "Tomas Berdych", "Juan Martin Del Potro" };
		List<String> players = Arrays.asList(atp);

		// ��ǰ��ѭ����ʽ
		/*
		 * for (String player : players) { System.out.print(player + "; "); }
		 */

		// ʹ�� lambda ���ʽ�Լ���������(functional operation)
		// players.forEach((player) -> System.out.print(player + "; "));

		players.forEach((player) -> {
			System.out.print(player + "; ");
		});

		// �� Java 8 ��ʹ��˫ð�Ų�����(double colon operator)
		// players.forEach(System.out::println);
	}

	@Test
	public static void testRunnable() {
		// 1.1ʹ�������ڲ���
		/*
		 * new Thread(new Runnable() {
		 * 
		 * @Override public void run() { System.out.println("Hello world !"); }
		 * }).start();
		 */

		// 1.2ʹ�� lambda expression
		new Thread(() -> System.out.println("Hello world !")).start();

		// 2.1ʹ�������ڲ���
		/*
		 * Runnable race1 = new Runnable() {
		 * 
		 * @Override public void run() { System.out.println("Hello world !"); }
		 * };
		 */

		// 2.2ʹ�� lambda expression
		Runnable race2 = () -> System.out.println("Hello world !");

		// ֱ�ӵ��� run ����(û�����߳�Ŷ!)
		// race1.run();
		race2.run();
	}

	@Test
	public static void testSort() {
		String[] players = { "Rafael Nadal", "Novak Djokovic", "Stanislas Wawrinka", "David Ferrer", "Roger Federer",
				"Andy Murray", "Tomas Berdych", "Juan Martin Del Potro", "Richard Gasquet", "John Isner" };

		// 1.1 ʹ�������ڲ������ name ���� players
		Arrays.sort(players, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return (s1.compareTo(s2));
			}
		});

		// 1.2 ʹ�� lambda expression ���� players
		Comparator<String> sortByName = (String s1, String s2) -> (s1.compareTo(s2));
		Arrays.sort(players, sortByName);

		// 1.3 Ҳ���Բ���������ʽ:
		Arrays.sort(players, (String s1, String s2) -> (s1.compareTo(s2)));

		List<Integer> list = new ArrayList<Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				add(1);
				add(2);
			}
		};
	}
	
}
