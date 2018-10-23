package com.itaiti.tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.Test;

public class PokerUtil {
	
	private List<Integer> pokerList;
	private List<Integer> takeThreeList;
	private List<Integer> landlord;
	private List<Integer> farmerA;
	private List<Integer> farmerB;
	
	private static String pokerDesc[] = new String[]{"A","2","3","4","5","6","7","8","9","10","J","Q","K","灏�","澶�"};
	
	private void shuffleCards(int times) {
		pokerList = new ArrayList<>();
		for(int i=0; i<54; i++) {
			pokerList.add(i);
		}
		for(int i=0; i<5; i++) {
			Collections.shuffle(pokerList);
		}
	}
	
	private void dealCard() {
		landlord = new ArrayList<>();
		farmerA = new ArrayList<>();
		farmerB = new ArrayList<>();
		if(pokerList.size() ==  51) {
			for(int i=0; i<17; i++) {
				System.out.println("-----"+i);
				landlord.add(pokerList.remove(getRandom(pokerList.size())));
				farmerA.add(pokerList.remove(getRandom(pokerList.size())));
				farmerB.add(pokerList.remove(getRandom(pokerList.size())));
			}
		}
	}
	
	private void takeThree() {
		takeThreeList = new ArrayList<>();
		takeThreeList.add(pokerList.remove(getRandom(pokerList.size())));
		takeThreeList.add(pokerList.remove(getRandom(pokerList.size())));
		takeThreeList.add(pokerList.remove(getRandom(pokerList.size())));
	}
	
	public static int getRandom(int size) {
		Random random = new Random();
		return random.nextInt(size);
	}
	
	public static List<String> printPoker(List<Integer> list) {
		String desc;
		List<String> pokerDescList = new ArrayList<>();
		
		for(Integer v : list) {
			if(v == 52) {
				pokerDescList.add(v+"-"+pokerDesc[13]);
			} else if(v == 53) {
				pokerDescList.add(v+"-"+pokerDesc[14]);
			} else {
				//pokerDescList.add(v+"-"+FlowerType.getDesc(getFlowerType(v))+""+pokerDesc[getPokerValue(v)]);
				pokerDescList.add(FlowerType.getDesc(getFlowerType(v))+""+pokerDesc[getPokerValue(v)]);
			}
			
		}
		return pokerDescList;
	}
	
	public static int getPokerValue(int v) {
		return v % 13;
	}
	
	public static int getFlowerType(int v) {
		return v / 13;
	}
	
	@Test
	public void test() {
		shuffleCards(5);
		takeThree();
		dealCard();
		System.out.println(pokerList);
		System.out.println(takeThreeList);
		System.out.println(landlord);
		System.out.println(farmerA);
		System.out.println(farmerB);
		
		Collections.sort(landlord);
		Collections.sort(farmerA);
		Collections.sort(farmerB);
		System.out.println("takeThreeList:"+printPoker(takeThreeList));
		System.out.println("landlord"+printPoker(landlord));
		System.out.println("farmerA"+printPoker(farmerA));
		System.out.println("farmerB"+printPoker(farmerB));
	}
	
	enum FlowerType {
		A(0,"鈾�"),//绾㈡
		B(1,"鈾�"),//榛戞
		C(2,"鈾�"),//鏂圭墖
		D(3,"鈾�");//姊呰姳
		
		private int value;
		private String desc;
		
		private FlowerType(int value, String desc) {
			this.value = value;
			this.desc = desc;
		}
		
		private FlowerType(String desc) {
			this.desc = desc;
		}
		
		public static String getDesc(int v) {
			for(FlowerType flowerType : FlowerType.values()) {
				if(v == flowerType.value){
					return flowerType.desc;
				}
			}
			return "";
		}
	}
	
	@Test
	public void test2() {
		// 0 1 2 3 4 5 6 7 8 9 10 11 12
		// 13 14 15 16 17 18 19 20 21 22 23 24 25
		// 26 27 28 29 30 31 32 33 34 35 36 37 38
		// 39 40 41 42 43 44 45 46 47 48 49 50 51
		// 52 53
		
		System.out.println(11%13);
		System.out.println(11/13);
		
		System.out.println(21%13);
		System.out.println(21/13);
		
		System.out.println(51%13);
		System.out.println(51/13);
	}
}
