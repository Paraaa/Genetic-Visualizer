# Genetic Visualizer
The goal of this tool is to create a visualisation of the optimization process of an evolutionary algorithm. 
The visualisation should provide some deeper inside in the behaviour of the evolutionary algorithm. Mainly, the consequences of changing certain parameters and operators on the optimization process should be visualized with the help of this tool. 
Therefore, this tool can be used as a supporting medium in teaching concepts and behaviour of evolutionary algorithms. 
## Requirements
1. It is necessary to have a _JRE_ installed. You need to have the version 11 or higher. It is recommended to use the latest version found here:  
> https://www.oracle.com/java/technologies/downloads/
3. At least 1 GB of available RAM 

## Operating systems
This tool should work on all commonly used operating system.
This includes _Windows 10/11_, _Linux_ and _macOS_. For each 
platform there is one executable _JAR_ file found.
### Windows 10/11
To run the program you simply double-click the executable for the Windows version.
### Linux 
If you have a graphical interface installed simply double-clicking the executable should start the tool. 
If the tool does not start, check whether you have permission to execute the program.
Simply add the permission to the file, and then it should start:
`chmod +x GeneticVisualizer-Linux.jar`
### macOS
If you are running a macOS system you have to build the project yourself. Check the building part of this readme 
for more information. 

### Building the project 
1. Download the latest version of Maven for your system at: https://maven.apache.org/download.cgi
2. Download the project and unzip it. If you are running a macOS system download the project from the 
`Genetic-Visualizer-macOS-m1` branch of this project 
3. Start a terminal and navigate to the directory of the project there the pom.xml is located
4. If maven was properly installed you can run `mvn clean install` to build the jar executable
5. The new build jar will be located in the folder `/Jar` with the name: `Thesis-1.0-SNAPSHOT-jar-with-dependencies.jar`
6. Simply double-click the file to run the program

### DEBUG information
If you have access to a terminal e.g. _Powershell_ navigate to the directory of the executables.
Executing the program via the terminal shows some additional _DEBUG_ information.
Simply use following command to start the program via the terminal:
`java -jar GeneticVisualizer-<Platform>.jar`

## Implementation details
### Solution space
The solution space is a three-dimensional mathematical function which has to be minimized or maximized. For easier visualization the function is represented as a contour plot. 
Warm colors represent a high value and cold colors represent a low value in the solution space.
### Vector 
The _vector_ representation consist of two coordinates _x_ and _y_.
### Binary
The _binary_ representation consist of a _64 bit_ binary string. The first _32 bits_ represent the _x_ coordinate  and the last _32 bits_ represent the _y_ coordinate.

## License
GNU GPLv3 