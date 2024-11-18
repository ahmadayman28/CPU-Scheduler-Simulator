package scheduler;

import model.Process;
import java.util.List;

public interface Scheduler {
    // Method to schedule processes based on the selected algorithm
    void schedule(List<Process> processes);
}
