package ui;

import simulation.*;
import model.Process;

import java.util.Random;
import java.util.Scanner;

public class SimulatorUI {

    private Simulator simulator;  // The simulator instance

    // Constructor to initialize the simulator
    public SimulatorUI() {
        this.simulator = new Simulator();
    }

    // Method to display the menu for user interaction
    public void displayMenu() {
        System.out.println("=== CPU Scheduling Simulator ===");
        System.out.println("1. Select Scheduling Algorithm");
        System.out.println("2. Set Simulation Mode (PREEMPTIVE or NON_PREEMPTIVE)");
        System.out.println("3. Set Number of Processes");
        System.out.println("4. Run Simulation");
        System.out.println("5. Exit");
    }

    // Method to generate a list of processes for the simulation
    private void generateProcesses(int numberOfProcesses) {
        Random random = new Random();
        for (int i = 0; i < numberOfProcesses; i++) {
            int arrivalTime = random.nextInt(10);  // Random arrival time (0-9)
            int burstTime = random.nextInt(10) + 1;  // Random burst time (1-10)
            int priority = random.nextInt(5) + 1;  // Random priority (1-5)
            Process process = new Process(i + 1, arrivalTime, burstTime, priority);  // Process ID starts from 1
            simulator.addProcess(process);
            System.out.println("Generated Process: " + process);
        }
    }

    // Method to get user inputs for the simulation
    public void getUserInputs() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayMenu();
            System.out.print("Please select an option (1-5): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Select Scheduling Algorithm
                    System.out.println("Select Scheduling Algorithm (FCFS, SJF, Priority, RoundRobin): ");
                    String algorithm = scanner.next();
                    int timeQuantum = 0;
                    boolean isPreemptive = false;  // Default to non-preemptive

                    if ("RoundRobin".equals(algorithm)) {
                        // Ask for time quantum when RoundRobin is selected
                        System.out.print("Enter time quantum for Round Robin: ");
                        timeQuantum = scanner.nextInt();
                    }

                    // Call selectScheduler with all required parameters
                    simulator.selectScheduler(algorithm, isPreemptive, timeQuantum);
                    break;

                case 2:
                    // Set Simulation Mode (PREEMPTIVE or NON_PREEMPTIVE)
                    System.out.println("Select Simulation Mode (PREEMPTIVE or NON_PREEMPTIVE): ");
                    String mode = scanner.next().toUpperCase();
                    if ("PREEMPTIVE".equals(mode)) {
                        simulator.setSimulationMode(SimulationMode.PREEMPTIVE);
                    } else {
                        simulator.setSimulationMode(SimulationMode.NON_PREEMPTIVE);
                    }
                    break;

                case 3:
                    // Set Number of Processes
                    System.out.print("Enter the number of processes: ");
                    int numberOfProcesses = scanner.nextInt();
                    generateProcesses(numberOfProcesses);  // Generate random processes
                    break;

                case 4:
                    // Run Simulation
                    simulator.runSimulation();
                    break;

                case 5:
                    // Exit
                    System.out.println("Exiting simulation...");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    // Method to start the UI (entry point for the program)
    public static void main(String[] args) {
        SimulatorUI ui = new SimulatorUI();
        ui.getUserInputs();
    }
}