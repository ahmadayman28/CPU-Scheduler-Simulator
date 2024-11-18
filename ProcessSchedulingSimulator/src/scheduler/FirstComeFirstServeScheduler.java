package scheduler;

import model.Process;
import java.util.List;

public class FirstComeFirstServeScheduler implements Scheduler {

    @Override
    public void schedule(List<Process> processes) {
        // First-Come, First-Served (FCFS) scheduling
        processes.sort((p1, p2) -> Integer.compare(p1.getArrivalTime(), p2.getArrivalTime()));

        int currentTime = 0;
        for (Process process : processes) {
            if (process.getArrivalTime() > currentTime) {
                currentTime = process.getArrivalTime();  // If a process arrives later, wait until it arrives
            }

            System.out.println("Process " + process.getId() + " starts at time " + currentTime);
            currentTime += process.getBurstTime();  // Simulate the process execution
            System.out.println("Process " + process.getId() + " ends at time " + currentTime);
        }
    }
}
