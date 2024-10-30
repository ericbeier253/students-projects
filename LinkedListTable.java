package comp511;
import java.util.Scanner;

public class assignment1 {

	public static void main(String[] args) {


		LinkedListTable table = new LinkedListTable();
		Scanner scanner = new Scanner(System.in);
		String option;
		
		do {
            System.out.println("Add a name (n)");
            System.out.println("Look up a name (l)");
            System.out.println("Update address (u)");
            System.out.println("Delete an entry (d)");
            System.out.println("Display all entries (a)");
            System.out.println("Quit (q)");
            System.out.print("-> ");
            option = scanner.nextLine().trim().toLowerCase();
            
            switch(option) {
            
            case "n":
                System.out.print("Name: ");
                String name = scanner.nextLine().trim();
                System.out.print("Address: ");
                String address = scanner.nextLine().trim();
                if (table.insert(name, address)) {
                    System.out.println("Entry added.");
                } else {
                    System.out.println("Entry already exists.");
                }
                break;
              
            case "l":
                System.out.print("Name: ");
                name = scanner.nextLine().trim();
                address = table.lookup(name);
                if (address != null) {
                    System.out.println("Address is " + address);
                } else {
                    System.out.println("Name not found.");
                }
                break;
           
            case "u":
                System.out.print("Name: ");
                name = scanner.nextLine().trim();
                System.out.print("New Address: ");
                address = scanner.nextLine().trim();
                if (table.update(name, address)) {
                    System.out.println("Entry updated.");
                } else {
                    System.out.println("Name not found.");
                }
                break;
                
            case "d":
                System.out.print("Name: ");
                name = scanner.nextLine().trim();
                if (table.delete(name)) {
                    System.out.println("Entry deleted.");
                } else {
                    System.out.println("Name not found.");
                }
                break;
                
            case "a":
                System.out.println("All entries:");
                System.out.printf("%d total entries.\n", table.displayAll());
                break;

            case "q":
                System.out.println("Quitting...");
                break;
                
            default:
                System.out.println("Invalid option. Please try again.");
                break;
            }
            
		}while(!option.equals("q"));
		
	}
}


