package scheduler;

import model.Process;
import simulation.SimulationMode;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class PriorityScheduler implements Scheduler {

    @Override
    public void schedule(List<Process> processes) {
        if (SimulationMode.PREEMPTIVE.equals(SimulationMode.PREEMPTIVE)) {
            preemptivePriorityScheduling(processes);
        } else {
            nonPreemptivePriorityScheduling(processes);
        }
    }

    // Non-preemptive Priority Scheduling
    private void nonPreemptivePriorityScheduling(List<Process> processes) {
        // Sort processes by arrival time first, and then by priority (lowest priority number = highest priority)
        processes.sort(Comparator.comparingInt(Process::getArrivalTime)
                .thenComparingInt(Process::getPriority));

        int currentTime = 0;
        List<Process> readyQueue = new ArrayList<>();
        int index = 0;

        while (index < processes.size() || !readyQueue.isEmpty()) {
            // Add processes that have arrived to the ready queue
            while (index < processes.size() && processes.get(index).getArrivalTime() <= currentTime) {
                readyQueue.add(processes.get(index));
                index++;
            }

            // If there are processes in the ready queue, execute the one with the highest priority (lowest priority number)
            if (!readyQueue.isEmpty()) {
                // Sort ready queue based on priority
                readyQueue.sort(Comparator.comparingInt(Process::getPriority));

                Process currentProcess = readyQueue.remove(0);  // Get the highest priority process

                // Execute the process
                System.out.println("Process " + currentProcess.getId() + " starts at time " + currentTime);
                currentTime += currentProcess.getBurstTime();  // Simulate execution for the burst time
                System.out.println("Process " + currentProcess.getId() + " ends at time " + currentTime);
            } else {
                // If no process is ready, increment time
                currentTime++;
            }
        }
    }

    // Preemptive Priority Scheduling (same as before)
    private void preemptivePriorityScheduling(List<Process> processes) {
        // Sort processes by arrival time initially
        processes.sort(Comparator.comparingInt(Process::getArrivalTime));
        int currentTime = 0;
        List<Process> readyQueue = new ArrayList<>();
        int index = 0;
        Process runningProcess = null;

        while (index < processes.size() || !readyQueue.isEmpty() || runningProcess != null) {
            // Add processes that have arrived to the ready queue
            while (index < processes.size() && processes.get(index).getArrivalTime() <= currentTime) {
                readyQueue.add(processes.get(index));
                System.out.println("Process " + processes.get(index).getId() + " enters ready queue at time " + currentTime);
                index++;
            }

            // If a process is running, check if it has completed
            if (runningProcess != null && runningProcess.isCompleted()) {
                System.out.println("Process " + runningProcess.getId() + " ends at time " + currentTime);
                runningProcess = null;
            }

            // Select the next process to execute if none is running
            if (runningProcess == null && !readyQueue.isEmpty()) {
                // Sort ready queue by priority (lower number = higher priority)
                readyQueue.sort(Comparator.comparingInt(Process::getPriority));

                // Select the process with the highest priority
                runningProcess = readyQueue.remove(0);

                // Log the process starting execution
                System.out.println("Process " + runningProcess.getId() + " starts at time " + currentTime);
            }

            // Execute the running process for one unit of time
            if (runningProcess != null) {
                runningProcess.executeFor(1);
            }

            // Increment the time step
            currentTime++;
        }
    }
}