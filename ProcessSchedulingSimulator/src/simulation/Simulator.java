package simulation;

import model.Process;
import scheduler.*;

import java.util.ArrayList;
import java.util.List;

public class Simulator {

    private List<Process> processes;
    private Scheduler scheduler;
    private SimulationMode simulationMode;

    public Simulator() {
        this.processes = new ArrayList<>();
        this.scheduler = null;
        this.simulationMode = SimulationMode.NON_PREEMPTIVE; // Default to non-preemptive mode
    }

    public void selectScheduler(String algorithm, boolean isPreemptive, int timeQuantum) {
        switch (algorithm) {
            case "FCFS":
                scheduler = new FirstComeFirstServeScheduler();
                break;
            case "SJF":
                scheduler = new ShortestJobFirstScheduler();
                break;
            case "Priority":
                scheduler = new PriorityScheduler();
                break;
            case "RoundRobin":
                scheduler = new RoundRobinScheduler(timeQuantum);
                break;
            default:
                throw new IllegalArgumentException("Unknown scheduling algorithm");
        }
    }

    public void setSimulationMode(SimulationMode mode) {
        this.simulationMode = mode;
    }

    public void addProcess(Process process) {
        processes.add(process);
    }

    public void runSimulation() {
        System.out.println("Running simulation in " + simulationMode + " mode...\n");
        scheduler.schedule(processes);
    }
}