package com.michaelsmith.unit6;

import java.util.concurrent.Semaphore;

public class DressingRooms {
	private int availableRooms;
	private Semaphore semaphore;
	
	DressingRooms(){
		this.availableRooms = 3;
		this.semaphore = new Semaphore(3);
	}
	
	DressingRooms(int availableRooms){
		this.availableRooms = availableRooms;
		this.semaphore = new Semaphore(availableRooms);
	}
	
	public void ReleaseRoom(){
		semaphore.release();
	}
	
	public void RequestRoom() throws InterruptedException{
		semaphore.acquire();
	}
	
	
}
