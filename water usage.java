import java.util.*;

class WaterUsageTracker {
    private Map<String, Double> dailyUsage; // stores activity -> liters used
    private List<Double> weeklyTotal; // stores daily total for 7 days
    private final double LIMIT = 150.0; // liters/day recommended max

    public WaterUsageTracker() {
        dailyUsage = new HashMap<>();
        weeklyTotal = new ArrayList<>();
    }

    // Add usage for an activity
    public void addUsage(String activity, double liters) {
        dailyUsage.put(activity, dailyUsage.getOrDefault(activity, 0.0) + liters);
    }

    // Display daily report
    public void displayDailyReport() {
        double total = 0;
        System.out.println("\n----- Daily Water Usage -----");
        for (Map.Entry<String, Double> entry : dailyUsage.entrySet()) {
            System.out.printf("%-15s : %.2f liters\n", entry.getKey(), entry.getValue());
            total += entry.getValue();
        }
        System.out.println("------------------------------");
        System.out.printf("Total Usage Today: %.2f liters\n", total);
        if (total > LIMIT)
            System.out.println("⚠️  Warning: You exceeded the daily limit! Try to save water.");
        else
            System.out.println("✅  Good job! You're within the recommended limit.");

        weeklyTotal.add(total);
    }

    // Display weekly average
    public void displayWeeklyAverage() {
        if (weeklyTotal.isEmpty()) {
            System.out.println("No data recorded yet.");
            return;
        }
        double sum = 0;
        for (double d : weeklyTotal) sum += d;
        double avg = sum / weeklyTotal.size();
        System.out.printf("\n📊 Weekly Average Usage: %.2f liters/day\n", avg);
    }

    // Reset for next day
    public void resetDailyUsage() {
        dailyUsage.clear();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        WaterUsageTracker tracker = new WaterUsageTracker();

        System.out.println("💧 Water Usage Tracker 💧");

        boolean running = true;
        while (running) {
            System.out.println("\nMENU:");
            System.out.println("1. Add water usage");
            System.out.println("2. Display daily report");
            System.out.println("3. Show weekly average");
            System.out.println("4. Reset for next day");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter activity name: ");
                    String activity = sc.nextLine();
                    System.out.print("Enter liters used: ");
                    double liters = sc.nextDouble();
                    tracker.addUsage(activity, liters);
                    break;

                case 2:
                    tracker.displayDailyReport();
                    break;

                case 3:
                    tracker.displayWeeklyAverage();
                    break;

                case 4:
                    tracker.resetDailyUsage();
                    System.out.println("✅ Ready for next day!");
                    break;

                case 5:
                    running = false;
                    System.out.println("👋 Exiting Water Tracker. Save water, save life!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again!");
            }
        }

        sc.close();
    }
}
