//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.*;
import java.io.*;

abstract class Ticket implements Serializable {
    private String eventName;
    private double basePrice;

    public Ticket(String eventName, double basePrice) {
        this.eventName = eventName;
        this.basePrice = basePrice;
    }

    public abstract double calculatePrice();

    public String getEventName() { return eventName; }
    public double getBasePrice() { return basePrice; }

    @Override
    public String toString() {
        return "Event: " + eventName + " | Final Price: " + calculatePrice();
    }
}

class VipTicket extends Ticket {
    public VipTicket(String eventName, double basePrice) {
        super(eventName, basePrice);
    }

    @Override
    public double calculatePrice() {
        return getBasePrice() * 1.5;
    }
}

class RegularTicket extends Ticket {
    public RegularTicket(String eventName, double basePrice) {
        super(eventName, basePrice);
    }

    @Override
    public double calculatePrice() {
        return getBasePrice();
    }
}

public class Main {
    private static List<Ticket> tickets = new ArrayList<>();
    private static final String DATA_FILE = "tickets_data.bin";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadFromFile();

        while (true) {
            System.out.println("\n--- Event Ticket Sales Manager ---");
            System.out.println("1. Create Ticket");
            System.out.println("2. View All Tickets");
            System.out.println("3. Update Ticket Price");
            System.out.println("4. Delete Ticket");
            System.out.println("5. Exit");
            System.out.print("Choice: ");

            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1" -> addTicket();
                    case "2" -> showTickets();
                    case "3" -> updateTicket();
                    case "4" -> deleteTicket();
                    case "5" -> { saveToFile(); return; }
                    default -> System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void addTicket() {
        System.out.print("Event Name: ");
        String name = scanner.nextLine();

        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        System.out.print("Base Price: ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.print("Type (1-Regular, 2-VIP): ");
        int type = Integer.parseInt(scanner.nextLine());

        if (type == 2) {
            tickets.add(new VipTicket(name, price));
        } else {
            tickets.add(new RegularTicket(name, price));
        }
        System.out.println("Success.");
    }

    private static void showTickets() {
        if (tickets.isEmpty()) {
            System.out.println("No records found.");
        } else {
            for (int i = 0; i < tickets.size(); i++) {
                System.out.println(i + ": " + tickets.get(i));
            }
        }
    }

    private static void updateTicket() {
        showTickets();
        if (tickets.isEmpty()) return;

        System.out.print("Enter index to update: ");
        int index = Integer.parseInt(scanner.nextLine());

        System.out.print("New Base Price: ");
        double newPrice = Double.parseDouble(scanner.nextLine());

        Ticket old = tickets.get(index);
        if (old instanceof VipTicket) {
            tickets.set(index, new VipTicket(old.getEventName(), newPrice));
        } else {
            tickets.set(index, new RegularTicket(old.getEventName(), newPrice));
        }
        System.out.println("Updated.");
    }

    private static void deleteTicket() {
        showTickets();
        if (tickets.isEmpty()) return;

        System.out.print("Enter index to delete: ");
        int index = Integer.parseInt(scanner.nextLine());
        tickets.remove(index);
        System.out.println("Deleted.");
    }

    private static void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(tickets);
            System.out.println("Data saved to " + DATA_FILE);
        } catch (IOException e) {
            System.out.println("Save error: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static void loadFromFile() {
        File file = new File(DATA_FILE);
        if (!file.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            tickets = (List<Ticket>) ois.readObject();
        } catch (Exception e) {
            System.out.println("Load error.");
        }
    }
}

