package oopLes9_hw_a;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

	public static void main(String[] args) {

		List<Integer> listOne = new ArrayList<>();
		
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			listOne.add(random.nextInt(500));
		}
		System.out.println(listOne);
				
		listOne.remove(0);
		listOne.remove(0);
		listOne.remove(listOne.size()-1);
		System.out.println(listOne);

	}

}
