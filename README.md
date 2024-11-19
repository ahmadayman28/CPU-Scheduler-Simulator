<h1>CPU Scheduler Simulator</h1>
<p>The <strong>CPU Scheduler Simulator</strong> is a Java-based project designed to simulate various CPU scheduling algorithms. It provides an educational tool for understanding how different scheduling techniques affect process execution in operating systems. The project implements object-oriented principles, SOLID principles, and design patterns for a clean and modular architecture.</p>
<h2>Features</h2>
    <ul>
      <li>Simulates multiple CPU scheduling algorithms:
          <ul>
              <li>First-Come, First-Served (FCFS)</li>            
              <li>Shortest Job First (SJF)</li>
              <li>Priority Scheduling (Preemptive and Non-Preemptive)</li>
              <li>Round Robin (Preemptive and Non-Preemptive)</li>
          </ul>
        </li>
        <li>Allows users to:
            <ul>
                <li>Choose the scheduling algorithm</li>
                <li>Set the simulation mode (Preemptive or Non-Preemptive)</li>
                <li>Define time quantum for Round Robin scheduling</li>
                <li>Add and manage simulated processes</li>
            </ul>
        </li>
        <li>Modular and extendable design for adding new scheduling algorithms.</li>
    </ul>
<h2>Applied Principles</h2>
  <h3>Object-Oriented Principles</h3>
      <ul>
              <li><strong>Abstraction:</strong> The project uses abstract classes and interfaces (e.g., <code>Scheduler</code> interface) to define common behavior for different scheduling algorithms.</li>
              <li><strong>Encapsulation:</strong> The <code>Process</code> class encapsulates all process-related attributes and methods, ensuring data integrity and modularity.</li>
              <li><strong>Inheritance:</strong> Different scheduling algorithms (e.g., <code>FirstComeFirstServeScheduler</code>, <code>PriorityScheduler</code>) inherit from the <code>Scheduler</code> interface.</li>
              <li><strong>Polymorphism:</strong> The project allows dynamic selection of scheduling algorithms at runtime using polymorphic behavior.</li>
      </ul>
      
    <h3>SOLID Principles</h3>
      <ul>
              <li><strong>Single Responsibility Principle:</strong> Each class has a single, well-defined responsibility (e.g., <code>Process</code> for process representation, <code>Scheduler</code> for scheduling algorithms).</li>
              <li><strong>Open/Closed Principle:</strong> The system is open for extension but closed for modification, allowing new scheduling algorithms to be added without changing existing code.</li>
              <li><strong>Liskov Substitution Principle:</strong> Subclasses and implementations of the <code>Scheduler</code> interface can replace their parent without altering the application's behavior.</li>
              <li><strong>Interface Segregation Principle:</strong> Interfaces are designed to include only relevant methods, ensuring that implementations are not forced to define unused methods.</li>
              <li><strong>Dependency Inversion Principle:</strong> High-level modules (e.g., <code>Simulator</code>) depend on abstractions (e.g., <code>Scheduler</code>) rather than concrete implementations.</li>
      </ul>
      
      <h3>Design Patterns</h3>
        <ul>
              <li><strong>Strategy Pattern:</strong> Different scheduling algorithms are implemented as strategies that can be selected dynamically at runtime.</li>
              <li><strong>Factory Pattern:</strong> Used to instantiate specific schedulers based on user selection in the simulator.</li>
        </ul>
      
<h2>Architecture</h2>
          <ul>
              <li><strong>Model:</strong> Represents the core data structures (e.g., <code>Process</code>).</li>
              <li><strong>Scheduler:</strong> Contains various CPU scheduling algorithms implementing the <code>Scheduler</code> interface.</li>
              <li><strong>Simulation:</strong> Manages the simulation flow and user interactions.</li>
              <li><strong>UI:</strong> Provides a command-line interface for configuring and running simulations.</li>
          </ul>
      
<h2>Getting Started</h2>
    <h3>Prerequisites</h3>
          <ul>
              <li>Java Development Kit (JDK) 8 or higher</li>
              <li>An Integrated Development Environment (IDE) such as IntelliJ IDEA or Eclipse</li>
          </ul>
      
    <h3>Installation</h3>
          <ol>
              <li>Clone the repository: <code>git clone https://github.com/your-username/cpu-scheduler-simulator.git</code></li>
              <li>Open the project in your preferred IDE.</li>
              <li>Build the project to resolve dependencies.</li>
          </ol>
      
      <h3>Usage</h3>
          <ol>
              <li>Run the <code>Simulator</code> class.</li>
              <li>Select the desired scheduling algorithm and configuration.</li>
              <li>Add processes with attributes such as arrival time, burst time, and priority.</li>
              <li>Run the simulation to see the scheduling results.</li>
          </ol>
      
<h2>Contributing</h2>
          <p>Contributions are welcome! Please fork the repository and submit a pull request with your changes.</p>
