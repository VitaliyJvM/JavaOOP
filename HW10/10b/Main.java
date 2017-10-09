package mvs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {

		Integer[] array = new Integer[100];
		for (int i = 0; i < array.length; i++) {
			array[i] = (int) (Math.random()*10);
		}
	
		System.out.println(Arrays.asList(array));
		Map<Integer,Integer> myMap = new HashMap<>();
		
		for (int i = 0; i < array.length; i++) {
			myMap.put(array[i],  (myMap.containsKey(array[i]))?myMap.get(array[i])+1:1);
		}
		
		System.out.println(myMap);

	}

}
