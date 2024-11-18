package scheduler;

import model.Process;
import simulation.SimulationMode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RoundRobinScheduler implements Scheduler {

    private int timeQuantum;  // Time slice for round robin scheduling

    public RoundRobinScheduler(int timeQuantum) {
        this.timeQuantum = timeQuantum;
    }

    @Override
    public void schedule(List<Process> processes) {
        Queue<Process> queue = new LinkedList<>();
        int time = 0;
        int index = 0;

        // Sort processes by arrival time
        processes.sort((p1, p2) -> Integer.compare(p1.getArrivalTime(), p2.getArrivalTime()));

        while (index < processes.size() || !queue.isEmpty()) {
            // Add all processes that have arrived by the current time to the queue
            while (index < processes.size() && processes.get(index).getArrivalTime() <= time) {
                queue.add(processes.get(index));
                index++;
            }

            if (!queue.isEmpty()) {
                Process current = queue.poll();

                if (current.getRemainingTime() > 0) {
                    int executionTime = Math.min(current.getRemainingTime(), timeQuantum);
                    current.setRemainingTime(current.getRemainingTime() - executionTime);
                    time += executionTime;

                    System.out.println("Process " + current.getId() + " executed for " + executionTime + " units at time " + time);

                    // If the process is not finished, re-add it to the queue
                    if (current.getRemainingTime() > 0) {
                        queue.add(current);
                    } else {
                        System.out.println("Process " + current.getId() + " ends at time " + time);
                    }
                }
            } else {
                // If no processes are ready to execute, increment time
                time++;
            }
        }
    }


    // Non-preemptive Round Robin (just executes each process in turn)
    private void nonPreemptiveRoundRobin(List<Process> processes) {
        Queue<Process> queue = new LinkedList<>();
        int currentTime = 0;

        // Sort processes by arrival time
        processes.sort((p1, p2) -> Integer.compare(p1.getArrivalTime(), p2.getArrivalTime()));

        int index = 0;
        while (index < processes.size() || !queue.isEmpty()) {
            // Add processes that have arrived to the ready queue
            while (index < processes.size() && processes.get(index).getArrivalTime() <= currentTime) {
                queue.add(processes.get(index));
                index++;
            }

            if (!queue.isEmpty()) {
                Process currentProcess = queue.poll();

                // Execute the process for its entire burst time
                System.out.println("Process " + currentProcess.getId() + " starts at time " + currentTime);
                currentTime += currentProcess.getBurstTime();
                System.out.println("Process " + currentProcess.getId() + " ends at time " + currentTime);
            } else {
                currentTime++;  // No process is ready, increment time
            }
        }
    }

    // Preemptive Round Robin (interrupts processes based on time quantum)
    private void preemptiveRoundRobin(List<Process> processes) {
        Queue<Process> queue = new LinkedList<>();
        int currentTime = 0;

        // Sort processes by arrival time
        processes.sort((p1, p2) -> Integer.compare(p1.getArrivalTime(), p2.getArrivalTime()));

        int index = 0;
        while (index < processes.size() || !queue.isEmpty()) {
            // Add processes that have arrived to the ready queue
            while (index < processes.size() && processes.get(index).getArrivalTime() <= currentTime) {
                queue.add(processes.get(index));
                index++;
            }

            if (!queue.isEmpty()) {
                Process currentProcess = queue.poll();

                // Execute the process for one time quantum (if it doesn't complete)
                int remainingTime = currentProcess.getRemainingTime();
                int timeSlice = Math.min(timeQuantum, remainingTime);
                currentProcess.executeFor(timeSlice);
                currentTime += timeSlice;

                System.out.println("Process " + currentProcess.getId() + " executed for " + timeSlice + " units at time " + currentTime);

                if (currentProcess.isCompleted()) {
                    System.out.println("Process " + currentProcess.getId() + " ends at time " + currentTime);
                } else {
                    queue.add(currentProcess);  // Re-add process to the queue if not completed
                }
            } else {
                currentTime++;  // No process is ready, increment time
            }
        }
    }
}