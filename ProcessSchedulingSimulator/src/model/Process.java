package model;

public class Process {
    // Attributes
    private int id;              // Process ID
    private int arrivalTime;      // Arrival time of the process
    private int burstTime;        // Burst time (CPU time required)
    private int priority;         // Priority of the process (for priority scheduling)
    private int remainingTime;    // Remaining burst time (used for Round Robin and Preemptive algorithms)

    // Constructor
    public Process(int id, int arrivalTime, int burstTime, int priority) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.remainingTime = burstTime;  // Initially, the remaining time is the burst time
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    // Method to display process details in a string format
    @Override
    public String toString() {
        return "Process{" +
                "id=" + id +
                ", arrivalTime=" + arrivalTime +
                ", burstTime=" + burstTime +
                ", priority=" + priority +
                ", remainingTime=" + remainingTime +
                '}';
    }

    // Method to check if the process has completed execution
    public boolean isCompleted() {
        return remainingTime == 0;
    }

    // Method to execute the process for a given amount of time (used in Round Robin)
    public void executeFor(int time) {
        if (remainingTime > 0) {
            if (remainingTime > time) {
                remainingTime -= time;
            } else {
                remainingTime = 0;  // Process is completed
            }
        }
    }

    // Method to reset remaining time if needed (used in case of new scheduling)
    public void reset() {
        this.remainingTime = burstTime;
    }
}