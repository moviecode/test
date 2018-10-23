package com.itaiti.kezi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Poker {
	
	public static Integer[] arr = {0,0,0,0,0,0,0,0,0,0,0,0,0};
	
	public static void main(String[] args) {
		//randomPoker();
		//System.out.println(arr.length);
		/*for(int i : arr){
			System.out.println(i);
		}*/
		System.out.println(Double.parseDouble("one"));
	}
	
	public static void randomPoker(){
		Random random = new Random();    
		boolean flag = true;
		int i = 0;
		int randomNum = 0;
		while(flag){
			randomNum = random.nextInt(13);
			if(exists(randomNum)){
				arr[i] = randomNum;
				i++;
				if(i == 12){
					flag = false;
				}
			}
		}
	}
	
	public static boolean exists(int randomNum){
		if(arr.length == 0){
			return true;
		}
		for(int i=0; i<arr.length; i++){
			if(randomNum == arr[i]){
				return false;
			}
		}
		return true;
	}
	
	public static void  shufflePoker(){
		List<Integer> pokerList = new ArrayList<>();
		Set<Integer> pokerSet = new HashSet<>();
		for(int i=1; i<=13; i++){
			pokerList.add(i);
			pokerSet.add(i);
		}
		for(Integer i : pokerList){
			System.out.println(i);
		}
		
		Collections.shuffle(pokerList);
		System.out.println("Ï´ÅÆºó.................");
		for(Integer i : pokerList){
			System.out.println(i);
		}
		System.out.println("setPoker..............");
		//pokerSet.addAll(pokerList);
		Iterator<Integer> it = pokerSet.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}

}
