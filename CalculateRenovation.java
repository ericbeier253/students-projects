/*Eric Beier
 *2-19-24
 *Assignment 5 - Renovation Problem 4
 */

package programs;

import java.util.Scanner;

public class CalculateRenovation {
	
	//assign field static wallArea and floorArea
	static double wallArea,floorArea;
	static Scanner scanner = new Scanner(System.in);
	
	//doors method
	static void addDoors(){
		
		System.out.print("How many doors?: ");
		int doorCount = scanner.nextInt();
		
		for(int i=1;i<=doorCount;i++) {
			System.out.printf("Enter Door %d Width: ",i);
			double doorWidth = scanner.nextDouble();
			System.out.printf("Enter Door %d Height: ", i);
			double doorHeight = scanner.nextDouble();
			
			wallArea -= doorWidth*doorHeight;
		}
		
		return;
	} //end method
	
	//windows method
	static void addWindows() {
		
		System.out.print("How many windows?: ");
		int windowCount = scanner.nextInt();
		
		for(int i=1;i<=windowCount;i++) {
			System.out.printf("Enter window %d Width: ",i);
			double windowWidth = scanner.nextDouble();
			System.out.printf("Enter window %d Height: ", i);
			double windowHeight = scanner.nextDouble();
			
			wallArea -= windowWidth*windowHeight;
		}
		
		return;
	} //end method
	
	//bookshelf method
	static void addBookshelves() {
		
		System.out.print("How many bookshelves?: ");
		int bookshelfCount = scanner.nextInt();
		
		for(int i=1;i<=bookshelfCount;i++) {
			System.out.printf("Enter bookshelf %d Length: ",i);
			double bookshelfLength = scanner.nextDouble();
			System.out.printf("Enter bookshelf %d Height: ", i);
			double bookshelfHeight = scanner.nextDouble();
			System.out.printf("Enter bookshelf %d depth: ", i);
			double bookshelfDepth = scanner.nextDouble();
			
			wallArea -= bookshelfLength*bookshelfHeight;
			floorArea -= bookshelfLength*bookshelfDepth;
		}		
		
		return;
	} //end method
	
	//determine flooring method
	static void determineFlooring() {
		
		//get budget and floor preference
		int floorPreference;
		
		while (true) {
		    System.out.print("Enter floor Preference (0, 1, 2, 3, 4): ");
		    floorPreference = scanner.nextInt();
		    if (floorPreference >= 0 && floorPreference <= 4) {
		        break;
		    } else {
		        System.out.println("Invalid entry");
		    }
		}
		
		System.out.print("Enter floor budget: ");
		double floorBudget = scanner.nextDouble();
		
		//state floor preference
		switch(floorPreference) {
		case 0:
			System.out.println("No preference selected.");
			break;
		case 1:
			System.out.println("Preference is tile.");
			break;
		case 2:
			System.out.println("Preference is wood.");
			break;
		case 3:
			System.out.println("Preference is carpet.");
			break;
		case 4:
			System.out.println("Preference is linoleum.");
			break;
		}
		
		//find a floor within budget
		switch(floorPreference) {
		case 0:
		case 1:
			if (floorBudget - 4*floorArea >= 0 && floorPreference == 1) {
				System.out.println("Preference is available.");
				floorBudget -= 4*floorArea;
				System.out.printf("Floor budget remaining: %.2f\n", floorBudget);
				break;
			}
			else if(floorBudget - 4*floorArea >= 0) {
				System.out.println("Tile is available.");
				floorBudget -= 4*floorArea;
				System.out.printf("Floor budget remaining: %.2f\n", floorBudget);
				break;
			}
			else {
				System.out.println("Tile is not available.");
			}
		case 2:
			if (floorBudget - 3*floorArea >= 0 && floorPreference == 2) {
				System.out.println("Preference is available.");
				floorBudget -= 3*floorArea;
				System.out.printf("Floor budget remaining: %.2f\n", floorBudget);
				break;
			}
			else if(floorBudget - 3*floorArea >= 0) {
				System.out.println("Wood is available.");
				floorBudget -= 3*floorArea;
				System.out.printf("Floor budget remaining: %.2f\n", floorBudget);
				break;
			}
			else {
				System.out.println("Wood is not available.");
			}
		case 3:
			if (floorBudget - 2*floorArea >= 0 && floorPreference == 3) {
				System.out.println("Preference is available.");
				floorBudget -= 2*floorArea;
				System.out.printf("Floor budget remaining: %.2f\n", floorBudget);
				break;
			}
			else if(floorBudget - 2*floorArea >= 0) {
				System.out.println("Carpet is available.");
				floorBudget -= 4*floorArea;
				System.out.printf("Floor budget remaining: %.2f\n", floorBudget);
				break;
			}
			else {
				System.out.println("Carpet is not available.");
			}
		case 4:
			if (floorBudget - floorArea >= 0 && floorPreference == 4) {
				System.out.println("Preference is available.");
				floorBudget -= floorArea;
				System.out.printf("Floor budget remaining: %.2f\n", floorBudget);
				break;
			}
			else if(floorBudget - floorArea >= 0) {
				System.out.println("Linoleum is available.");
				floorBudget -= floorArea;
				System.out.printf("Floor budget remaining: %.2f\n", floorBudget);
				break;
			}
			else {
				System.out.println("Linoleum is not available.");
			}
		default:
			System.out.println("No flooring options are available");
			System.out.printf("Floor budget remaining: %.2f\n", floorBudget);
						
			} //end switch
		
		return;
	} //end method
	
	
	//main method
	public static void main(String[] args) {



		//input room dimensions
		System.out.print("Enter room length: ");
		double roomLength = scanner.nextDouble();
		System.out.print("Enter room width: ");
		double roomWidth = scanner.nextDouble();
		System.out.print("Enter room height: ");
		double roomHeight = scanner.nextDouble();

		
		//calculate bare wall area
		wallArea = roomHeight*(2*roomLength + 2*roomWidth);
		
		//calculate bare floor area
		floorArea = roomLength*roomWidth;
		
				

		//add doors, windows, and bookshelves
		addDoors();		
		addWindows();	
		addBookshelves();

		
		//print wall and floor area
		System.out.printf("Wall area is: %.2f\n", wallArea);
		System.out.printf("Floor area is: %.2f\n", floorArea);
		
		//determine floors
		determineFlooring();
		
	
		
		//get budget and wall preference
		int wallPreference;
		
		while (true) {
		    System.out.print("Enter wall Preference (0, 1, 2, 3, 4): ");
		    wallPreference = scanner.nextInt();
		    if (wallPreference >= 0 && wallPreference <= 4) {
		        break;
		    } else {
		        System.out.println("Invalid entry");
		    }
		}
		
		System.out.print("Enter wall budget: ");
		double wallBudget = scanner.nextDouble();
		
		scanner.close();
		
		//state wall preference
		switch(wallPreference) {
		case 0:
			System.out.println("No preference selected.");
			break;
		case 1:
			System.out.println("Preference is tile.");
			break;
		case 2:
			System.out.println("Preference is paneling.");
			break;
		case 3:
			System.out.println("Preference is wallpaper.");
			break;
		case 4:
			System.out.println("Preference is paint.");
			break;
		}
		
		
		//find walls within budget
		switch(wallPreference) {
		case 0:
		case 1:
			if (wallBudget - 4*wallArea >= 0 && wallPreference == 1) {
				System.out.println("Preference is available.");
				wallBudget -= 4*wallArea;
				System.out.printf("Wall budget remaining: %.2f\n", wallBudget);
				break;
			}
			else if(wallBudget - 4*wallArea >= 0) {
				System.out.println("Tile is available.");
				wallBudget -= 4*wallArea;
				System.out.printf("Wall budget remaining: %.2f\n", wallBudget);
				break;
			}
			else {
				System.out.println("Tile is not available.");
			}
		case 2:
			if (wallBudget - 3*wallArea >= 0 && wallPreference == 2) {
				System.out.println("Preference is available.");
				wallBudget -= 3*wallArea;
				System.out.printf("wall budget remaining: %.2f\n", wallBudget);
				break;
			}
			else if(wallBudget - 3*wallArea >= 0) {
				System.out.println("Paneling is available.");
				wallBudget -= 3*wallArea;
				System.out.printf("wall budget remaining: %.2f\n", wallBudget);
				break;
			}
			else {
				System.out.println("Paneling is not available.");
			}
		case 3:
			if (wallBudget - 2*wallArea >= 0 && wallPreference == 3) {
				System.out.println("Preference is available.");
				wallBudget -= 2*wallArea;
				System.out.printf("wall budget remaining: %.2f\n", wallBudget);
				break;
			}
			else if(wallBudget - 2*wallArea >= 0) {
				System.out.println("Wallpaper is available.");
				wallBudget -= 2*wallArea;
				System.out.printf("wall budget remaining: %.2f\n", wallBudget);
				break;
			}
			else {
				System.out.println("Wallpaper is not available.");
			}
		case 4:
			if (wallBudget - wallArea >= 0 && wallPreference == 4) {
				System.out.println("Preference is available.");
				wallBudget -= wallArea;
				System.out.printf("wall budget remaining: %.2f\n", wallBudget);
				break;
			}
			else if(wallBudget - wallArea >= 0) {
				System.out.println("Paint is available.");
				wallBudget -= wallArea;
				System.out.printf("wall budget remaining: %.2f\n", wallBudget);
				break;
			}
			else {
				System.out.println("Paint is not available.");
			}
		default:
			System.out.println("No wall options are available");
			System.out.printf("wall budget remaining: %.2f\n", wallBudget);
						
			} //end switch
		
		
		} //end main
		
		
		
		
	} // end class


