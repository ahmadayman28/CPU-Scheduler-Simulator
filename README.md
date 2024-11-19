# CPU Scheduler Simulator

A Java-based simulation tool for CPU scheduling algorithms, implementing core concepts of operating systems and software design. The simulator supports multiple scheduling algorithms, including both preemptive and non-preemptive modes.

---

## Features
- **Scheduling Algorithms**:
  - **First Come First Serve (FCFS)**
  - **Shortest Job First (SJF)** (Preemptive & Non-preemptive)
  - **Priority Scheduling** (Preemptive & Non-preemptive)
  - **Round Robin** with configurable time quantum
- **Interactive Simulation**:
  - Add processes dynamically.
  - Select algorithms and modes (Preemptive or Non-preemptive).
  - View execution steps and scheduling decisions.

---

## Object-Oriented Design

### Applied Principles
- **Encapsulation**:
  - Process attributes (ID, burst time, priority, etc.) are private with public getters/setters.
  - Scheduler logic is encapsulated within specific classes.
- **Abstraction**:
  - `Scheduler` interface provides a common contract for all scheduling algorithms.
- **Polymorphism**:
  - Algorithms are interchangeable via the `Scheduler` interface.
- **Inheritance**:
  - Shared logic and utility methods are leveraged across scheduling implementations.

### SOLID Principles
- **Single Responsibility**:
  - Each class has a clear and focused responsibility, such as scheduling (`Scheduler`), representing a process (`Process`), or running simulations (`Simulator`).
- **Open-Closed**:
  - New algorithms can be added by implementing the `Scheduler` interface without modifying existing code.
- **Liskov Substitution**:
  - Any `Scheduler` implementation can replace another without altering the simulator’s behavior.
- **Interface Segregation**:
  - The design avoids forcing implementation of unnecessary methods by using lean, focused interfaces.
- **Dependency Inversion**:
  - The simulator depends on abstractions (`Scheduler` interface) rather than concrete implementations.

### Design Patterns
- **Strategy Pattern**:
  - Scheduling algorithms are interchangeable and selected dynamically at runtime using the `Scheduler` interface.
- **Factory Method Pattern**:
  - Scheduler instances are created based on user-selected algorithms.

---


## Package Structure
```plaintext
src/
├── model/
│   └── Process.java           # Represents a process in the system.
├── scheduler/
│   ├── Scheduler.java         # Interface for scheduling algorithms.
│   ├── FirstComeFirstServeScheduler.java
│   ├── ShortestJobFirstScheduler.java
│   ├── PriorityScheduler.java
│   └── RoundRobinScheduler.java
├── simulation/
│   ├── Simulator.java         # Core simulation logic.
│   ├── SimulationMode.java    # Enum for preemptive/non-preemptive modes.
├── ui/
│   └── SimulatorUI.java       # UI for user interaction.
```

Contributions
-------------

Contributions are welcome! Please submit issues or pull requests to improve functionality or add new features.
