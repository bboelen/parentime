# Parentime

Parentime is a proof of concept application designed for the management of alternating parental custody schedules in a cascading manner. This application is built to facilitate the organization and visualization of custody schedules, ensuring that both parents are adequately involved in their child's life.

## Key Components and Features

### 1. Constraint Definition and Encoding:
- Hard and Soft Constraints: The application incorporates both hard and soft constraints to ensure flexibility and adaptability in creating custody schedules.
- CFE and Its Constraints: The CFE is encoded in JSON format, indicating a structured approach to handling complex data and constraints.
### 2. Problem Modeling and Optimization:
- Constraint Programming: The application employs constraint programming, a key technique in artificial intelligence, to model and solve the custody scheduling problem.
- Metaheuristics: It also utilizes metaheuristics for optimization, ensuring efficient and effective solutions.
### 3. Variable Neighborhood Search:
- Solution Generation: The application is equipped with mechanisms for generating solutions and employs local search movements and shaking movements to explore the solution space effectively.
- Experimentation with JAMES: The project includes experiments with the JAMES framework, indicating a multi-faceted approach to solution exploration and optimization.
### 4. Parentime Application:
- Objective: The primary goal is to provide a practical and user-friendly platform for parents to manage and visualize custody schedules.
- Implementation: It allows the definition of a “circle” either through an “object” method or an “XML” method, offering flexibility in data input and management.
- Constraint Addition and Schedule Calculation: Users can add constraints and compute schedules, making the application adaptable to varying needs and complexities.
### 5. Technical Details:
- XML Encoding: The application supports XML encoding, as seen in the detailed example provided in the document, where persons (children and tutors) and agendas are defined with specific attributes and entries.

## Application Workflow

- Circle Definition: Users can define a circle, which likely represents a group of individuals involved in the custody schedule, including children and parents or guardians.
- Constraint Addition: Users have the flexibility to add various constraints to tailor the custody schedules according to specific needs, requirements, and agreements.
- Schedule Calculation: The application calculates optimal or feasible schedules based on the defined circles and constraints, ensuring that the resulting schedules are practical and adhere to the set constraints.
- Visualization: With features like the PrettyPrinter utility class, users can visualize the custody schedules, making them easy to understand and follow.
- Optimization: The application employs advanced optimization techniques, including variable neighborhood search and metaheuristics, to ensure that the custody schedules are not only feasible but also optimized for the involved parties.

## Construction and Execution

Parentime is a Maven 3 project composed of various libraries. To use the application, the first step is to build the project using the following command (within the parentime directory):

``` bash
$ mvn package
```

The project's pom.xml file is configured to create a jar archive without dependencies, as well as another archive containing all the necessary dependencies for launching the project: this is the parentime-jar-with-dependencies.jar file. To run the application, simply execute the following command from the shell.

``` bash
$ java -jar parentime-jar-with-dependencies.jar
```

Although it is a self-executable jar file that can be launched with a simple double-click, it is strongly advised to use the application while continuing to view the console. Indeed, numerous information about the problem-solving process is displayed in real time on the standard output and allows, among other things, to visualize the behavior of the different algorithms.

## Conclusion

Parentime is a Proof of Concept (POC) designed to address the intricate and dynamic nature of managing alternating parental custody schedules. It encapsulates a blend of advanced artificial intelligence and optimization techniques to ensure the creation of feasible and optimized custody schedules, considering all legal and personal constraints.

As a POC, Parentime demonstrates the potential and foundational structure for a comprehensive solution. It is intricately designed, showcasing features and capabilities that can be expanded and enhanced to cater to a broader spectrum of scenarios and complexities. The application is not just a tool but a starting point, a blueprint ripe for further development and refinement.

We openly invite developers, AI enthusiasts, and individuals with a keen interest in this domain to explore, critique, and improve upon this project. Every piece of feedback, every enhancement, and every innovative addition propels this POC closer to a comprehensive, user-friendly, and highly effective tool for managing parental custody schedules.

The future of Parentime is not just in the code and algorithms that constitute its current state but in the collaborative efforts and innovative contributions that will define its evolution. We look forward to witnessing the transformation and enhancement of Parentime, driven by a community dedicated to leveraging technology for the welfare of children and the convenience of parents and guardians involved.
