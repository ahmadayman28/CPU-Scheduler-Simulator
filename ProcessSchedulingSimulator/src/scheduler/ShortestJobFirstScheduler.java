package scheduler;

import model.Process;
import simulation.SimulationMode;

import java.util.List;
import java.util.PriorityQueue;

public class ShortestJobFirstScheduler implements Scheduler {

    @Override
    public void schedule(List<Process> processes) {
        if (SimulationMode.PREEMPTIVE.equals(SimulationMode.PREEMPTIVE)) {
            preemptiveSJF(processes);
        } else {
            nonPreemptiveSJF(processes);
        }
    }

    // Non-preemptive Shortest Job First Scheduling
    private void nonPreemptiveSJF(List<Process> processes) {
        processes.sort((p1, p2) -> {
            if (p1.getArrivalTime() != p2.getArrivalTime()) {
                return Integer.compare(p1.getArrivalTime(), p2.getArrivalTime());
            }
            return Integer.compare(p1.getBurstTime(), p2.getBurstTime());
        });

        int currentTime = 0;

        for (Process process : processes) {
            if (currentTime < process.getArrivalTime()) {
                currentTime = process.getArrivalTime();  // Wait for process arrival
            }

            System.out.println("Process " + process.getId() + " starts at time " + currentTime);
            currentTime += process.getBurstTime();  // Execute the process
            process.setRemainingTime(0);  // Mark as completed
            System.out.println("Process " + process.getId() + " ends at time " + currentTime);
        }
    }

    // Preemptive Shortest Job First Scheduling
    private void preemptiveSJF(List<Process> processes) {
        PriorityQueue<Process> pq = new PriorityQueue<>((p1, p2) -> {
            if (p1.getRemainingTime() != p2.getRemainingTime()) {
                return Integer.compare(p1.getRemainingTime(), p2.getRemainingTime());
            }
            return Integer.compare(p1.getArrivalTime(), p2.getArrivalTime());
        });

        int currentTime = 0;
        int completedProcesses = 0;
        int totalProcesses = processes.size();

        while (completedProcesses < totalProcesses) {
            // Add all processes that have arrived by the current time to the priority queue
            for (Process process : processes) {
                if (process.getArrivalTime() <= currentTime && !pq.contains(process) && !process.isCompleted()) {
                    pq.offer(process);
                }
            }

            if (!pq.isEmpty()) {
                Process currentProcess = pq.poll();  // Get the process with the shortest remaining time

                // If process starts execution
                if (currentProcess.getRemainingTime() == currentProcess.getBurstTime()) {
                    System.out.println("Process " + currentProcess.getId() + " starts at time " + currentTime);
                }

                // Execute the process for 1 time unit
                currentProcess.executeFor(1);
                currentTime++;

                if (currentProcess.isCompleted()) {
                    System.out.println("Process " + currentProcess.getId() + " ends at time " + currentTime);
                    completedProcesses++;
                } else {
                    pq.offer(currentProcess);  // Re-add the process with updated remaining time
                }
            } else {
                // If no process is ready, move forward in time
                currentTime++;
            }
        }
    }
}