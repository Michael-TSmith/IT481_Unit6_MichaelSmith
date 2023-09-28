package com.michaelsmith.unit6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Scenario {
	private DressingRooms dressingRooms;
    private int numberOfCustomers;
    private int numberOfRooms;
    private int customerTimeTaken;
    private static ArrayList<Integer> averageItems = new ArrayList<>();

    
    //Constructor
    public Scenario(int numberOfRooms, int numberOfCustomers) {
    	this.dressingRooms = new DressingRooms(numberOfRooms);
        this.numberOfCustomers = numberOfCustomers;
        this.numberOfRooms = numberOfRooms;
    }
    
    public static void main(String[] args) throws InterruptedException {
        System.out.println("~~~~ Scenarios Begining ~~~~");
        Scenario scenario1 = new Scenario(3, 10);// Example scenario with 3 rooms and 10 customers
        Scenario scenario2 = new Scenario(6, 21);// Example scenario with 6 rooms and 21 customers
        Scenario scenario3 = new Scenario(4, 5);// Example scenario with 4 rooms and 5 customers
        scenario1.runScenario();
        scenario2.runScenario();
        scenario3.runScenario();
        System.out.println("~~~~ Scenarios Ending ~~~~");
    }
    
    
    
    //Public methods
    public void runScenario() throws InterruptedException {
        long startTime = System.currentTimeMillis();

        // Create and start customer threads
        for (int i = 0; i < numberOfCustomers; i++) {
        	Random random = new Random();
        	int clothingItems = random.nextInt(21);
            Customer customer = new Customer(dressingRooms, clothingItems, i + 1);
            customer.start();
            dressingRooms.RequestRoom();
        }

        // Wait for all customer threads to finish
        for (int i = 0; i < numberOfCustomers; i++) {
            dressingRooms.ReleaseRoom();
        }
        long endTime = System.currentTimeMillis();
        // Calculate and print stats
        long elapsedTime = endTime - startTime;
        double averageItems = calculateAverageItems();
        double averageUsageTime = (double) elapsedTime / numberOfCustomers;
        double waitingTime = averageUsageTime - averageItems;

        System.out.println("Scenario Results: {");
        System.out.println("Total Dressing Rooms: " + this.numberOfRooms);
        System.out.println("Total Customers: " + numberOfCustomers);
        System.out.println("Average Items per Customer: " + averageItems);
        System.out.println("Average Usage Time per Customer (Min): " + averageUsageTime / 1000);
        System.out.println("Average Waiting Time per Customer (Min): " + waitingTime / 1000);
        System.out.println(" }");
    }

    public static void addAverageItemsList(int numberOfItems) {
    	averageItems.add(numberOfItems);
    }
    
    
    
    //Private Methods
    private double calculateAverageItems() {
    	int sum = 0;
    	for(int i = 0; i < averageItems.size(); i++) {
    		sum += averageItems.get(i);
    	}
    	return sum / averageItems.size();
    	
    }
}
