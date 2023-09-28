package com.michaelsmith.unit6;

import java.util.Random;

public class Customer extends Thread {
	private DressingRooms dressingRooms;
	private int numberOfItems;
	private int id;
	Random random;

	Customer(DressingRooms dressingRooms, int numberOfItems, int id) {
		Random random = new Random();
		this.dressingRooms = dressingRooms;
		this.numberOfItems = numberOfItems != 0 ? numberOfItems : random.nextInt(20);
		this.id = id;
	}

	@Override
	public void run() {
		long startTime = System.currentTimeMillis();
		int timeSpent = 0;
		Random random = new Random();
		for (int i = 0; i < numberOfItems; i++) {
			int tryOnTime = random.nextInt(3) + 1; // Random time between 1 and 3 minutes per item
			timeSpent += tryOnTime;
			try {
				int sleepTime = random.nextInt(5) + 1; //Random wait time to simulate different customer paces.
				sleepTime *= 1000;
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				System.err.println("Customer has gotten lost in the dressing room area");
				e.printStackTrace();
			}
		}
		long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.printf("Customer %d { %n      changing time (Min): %d %n      Number of items: %d %n  } %n", this.id, (elapsedTime / 1000), numberOfItems);
		Scenario.addAverageItemsList(numberOfItems);
		// Customer is done trying on clothes
		dressingRooms.ReleaseRoom();
	}
}
