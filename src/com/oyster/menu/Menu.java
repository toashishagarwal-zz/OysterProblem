package com.oyster.menu;

import java.util.Scanner;

import com.oyster.model.Card;
import com.oyster.model.Mode;
import com.oyster.model.Trip;
import com.oyster.processor.TripProcessor;

public class Menu {
	public void show(Scanner scanner) {
		String command;
		char key;
		
		System.out.println("***********************************************");
		System.out.println("*     Welcome to Oyster Fare Card System      *");
		System.out.println("***********************************************");
		
		Card c = new Card();
		
		do {
			System.out.println();
			System.out.println("[L]oad Card\t [T]ake a trip \t [Q]uit");
			command = scanner.nextLine();
			if(command.length() == 0) {
				key = '.';
				continue;
			}
			
			key = command.charAt(0);
			if(key == 'Q') {
				if(c.getInitialAmount() == null) {
					System.out.println("Looks like you did not take any trips !! ");
				} else {
					System.out.println("Your Initial card amount (in pounds) was :\t" + c.getInitialAmount());
					System.out.println("Your card balance (in pounds) is :\t" + c.getBalance());
				}
				System.out.println("Thank you for using Oyster Cards Fare System !! ");
				break;
			} else if (key == 'L') {
				System.out.println("Enter your the amount to load in your card (in pounds) : ");
				Double amount = scanner.nextDouble();
				c.setInitialAmount(amount);
				c.setBalance(amount);
				scanner.nextLine();
			} else if (key == 'T') {
				if(c.getInitialAmount() == null) 
					System.out.println("Your card seems empty. Please load the card first !!");
				else if(c.getBalance() < 1.8d)
					System.out.println("You dont seem to have sufficient balance. Load more amount!!");
				else {
					System.out.println("Enter the mode (BUS/TUBE):" );
					String mode = scanner.next();
					scanner.nextLine();
					if(!mode.equalsIgnoreCase("Bus") && !mode.equalsIgnoreCase("Tube")) {
						System.out.println("Invalid mode. Please enter Bus or Tube");
					} else {
						System.out.println("Enter source point: ");
						String source = scanner.nextLine();
						
						System.out.println("Enter destination point: ");
						String destination = scanner.nextLine();
						
						System.out.println("Enter the station (Y/N)? : ");
						String isEnter = scanner.next();
						
						System.out.println("Exit the destination (Y/N)? : ");
						String isExit = scanner.next();
						scanner.nextLine();
						
						Trip trip = createTrip(mode, source, destination);
						Double fare = TripProcessor.calculateFare(trip, isEnter, isExit);
						c.setBalance(c.getBalance() - fare);
					}
				}
			}
		} while (key != 'Q');
	}

	private Trip createTrip(String mode, String source, String destination) {
		Trip t = new Trip();
		Mode m = (mode.equalsIgnoreCase("BUS")) ? Mode.BUS: Mode.TUBE ;
		t.setCharge(Double.valueOf(3.20d));
		t.setSource(source);
		t.setDestination(destination);
		t.setMode(m);
		return t;
	}
}
