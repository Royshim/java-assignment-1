public class CarRentalSystem {
    public static void main(String[] args) {
        RentalAgency agency = new RentalAgency();
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        agency.addCar(new Car("kBC123", "Toyota", "Corolla", 5000));
        agency.addCar(new Car("kYZ789", "Honda", "Civic", 5500));
        agency.addCar(new Car("kdF456", "Ford", "Focus", 4800));
        agency.addCar(new Car("kHI789", "Mazda", "3", 5200));
        agency.addCar(new Car("kKL012", "Nissan", "Sentra", 4700));
        agency.addCar(new Car("kNO345", "Hyundai", "Elantra", 4900));
        agency.addCar(new Car("kQR678", "Volkswagen", "Jetta", 5300));

        System.out.println("Welcome to Ruiru Kenya Car Rental !");
        
        System.out.print("Please enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Please enter your contact information (email or phone): ");
        String contactInfo = scanner.nextLine();
        
        Customer customer = new Customer("C" + (agency.getCustomers().size() + 1), name, contactInfo);
        agency.addCustomer(customer);

        System.out.println("\nAvailable cars:");
        java.util.List<Car> availableCars = agency.getAvailableCars();
        for (int i = 0; i < availableCars.size(); i++) {
            System.out.println((i + 1) + ". " + availableCars.get(i));
        }

        System.out.print("\nEnter the number of the car you want to rent: ");
        int carChoice = scanner.nextInt();
        Car selectedCar = availableCars.get(carChoice - 1);

        System.out.print("Enter the number of days for rental: ");
        int days = scanner.nextInt();

        double totalCost = selectedCar.getDailyRate() * days;
        System.out.printf("Total cost for %d days: KES %.2f%n", days, totalCost);

        System.out.print("Do you want to proceed with the rental? (yes/no): ");
        scanner.nextLine(); // Consume newline
        String decision = scanner.nextLine();

        if (decision.equalsIgnoreCase("yes")) {
            agency.rentCar(customer, selectedCar);
            System.out.println("\nRental Confirmation:");
            System.out.println("Customer: " + customer.getName());
            System.out.println("Contact: " + customer.getContactInfo());
            System.out.println("Car: " + selectedCar);
            System.out.printf("Total Cost: KES %.2f%n", totalCost);
        } else {
            System.out.println("Thank you for considering our service.");
        }

        scanner.close();
    }
}

class Car {
    private String licensePlate;
    private String make;
    private String model;
    private boolean isAvailable;
    private double dailyRate;

    public Car(String licensePlate, String make, String model, double dailyRate) {
        this.licensePlate = licensePlate;
        this.make = make;
        this.model = model;
        this.isAvailable = true;
        this.dailyRate = dailyRate;
    }

    public String getLicensePlate() { return licensePlate; }
    public String getMake() { return make; }
    public String getModel() { return model; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
    public double getDailyRate() { return dailyRate; }

    @Override
    public String toString() {
        return make + " " + model + " (" + licensePlate + ") - KES " + dailyRate + "/day";
    }
}

class Customer {
    private String id;
    private String name;
    private String contactInfo;

    public Customer(String id, String name, String contactInfo) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getContactInfo() { return contactInfo; }
}

class RentalAgency {
    private java.util.List<Car> cars;
    private java.util.List<Customer> customers;
    private java.util.Map<Customer, Car> rentals;

    public RentalAgency() {
        cars = new java.util.ArrayList<>();
        customers = new java.util.ArrayList<>();
        rentals = new java.util.HashMap<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public boolean rentCar(Customer customer, Car car) {
        if (car.isAvailable() && !rentals.containsKey(customer)) {
            car.setAvailable(false);
            rentals.put(customer, car);
            return true;
        }
        return false;
    }

    public java.util.List<Car> getAvailableCars() {
        java.util.List<Car> availableCars = new java.util.ArrayList<>();
        for (Car car : cars) {
            if (car.isAvailable()) {
                availableCars.add(car);
            }
        }
        return availableCars;
    }

    public java.util.List<Customer> getCustomers() {
        return new java.util.ArrayList<>(customers);
    }
} 


