package jdbc;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClientDAO dao = new ClientDAO();

        while (true) {
            System.out.println("\nClient Info Management");
            System.out.println("1. Add Client");
            System.out.println("2. View All Clients");
            System.out.println("3. Update Client");
            System.out.println("4. Delete Client");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Phone: ");
                    String phone = scanner.nextLine();
                    dao.addClient(new Client(name, email, phone));
                    break;
                    
                case 2:
                    List<Client> clients = dao.getAllClients();
                    for (Client c : clients) {
                        System.out.println("ID: " + c.getId() + ", Name: " + c.getName() +
                                ", Email: " + c.getEmail() + ", Phone: " + c.getPhone());
                    }
                    break;
                    
                case 3:
                    System.out.print("Enter Client ID to update: ");
                    int idToUpdate = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    System.out.print("Enter new Name: ");
                    String newName = scanner.nextLine();

                    System.out.print("Enter new Email: ");
                    String newEmail = scanner.nextLine();

                    System.out.print("Enter new Phone: ");
                    String newPhone = scanner.nextLine();

                    dao.updateClient(idToUpdate, newName, newEmail, newPhone);
                    break;

                case 4:
                    System.out.print("Enter Client ID to delete: ");
                    int idToDelete = scanner.nextInt();
                    scanner.nextLine();
                    dao.deleteClient(idToDelete);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
                    scanner.close();
            }
        }
    }
}
