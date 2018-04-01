package com.oyster.runner;

import java.util.Scanner;

import com.oyster.menu.Menu;

public class DriverProgram {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		Menu menu = new Menu();
		menu.show(scanner);
	}
}
