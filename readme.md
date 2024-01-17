# Mower Service Navigation

## Overview

The Mower Service Navigation application is designed to simulate the navigation of multiple lawn mowers on a rectangular lawn. Each lawn mower is controlled by a set of instructions, and the application outputs the final positions of the mowers after following the instructions.

## Getting Started

### Prerequisites

- Java 17 or higher
- Gradle 8.5

### Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/landryfomena/SG_TONDEUSE_TEST.git
    ```

2. Navigate to the project directory:

    ```bash
    cd mower-service-navigation
    ```

3. Build the project with Gradle:

    ```bash
    ./gradlew build
    ```

### Usage

1. Create a text file with the instructions for the mowers. The file should follow the specified format:

    ```
    5 5
    1 2 N
    GAGAGAGAAAAA
    1 2 N
    GAGAGAGAAAAAAA
    ```

   - The first line represents the top-right coordinates of the rectangular lawn.
   - Each subsequent pair of lines represents the initial position and instructions for a mower.

2. Run the application with the test files under src/test :

    ```bash
    ./gradlew test
    ```

3. View the output to see the final positions of the mowers.

## Structure

The project is structured as follows:

- **src/main/java**: Contains the main Java source code.
- **src/test/java**: Contains test classes.
- **build**: Contains compiled classes and executable JAR file after building.

## Classes

- **Entry**: Represents the input data for the mowers, including lawn dimensions and a list of mowers.
- **Mow**: Represents a single mower with initial position and instructions.
- **Position**: Represents the position of a mower on the lawn.

## Tests

The `SgTondeuseTestApplicationTests` class in `src/test/java` includes tests for the `getData` function of the `DataRepositoryUtils` class, 
the tests for the `mow` function of the `MowerServiceUtils`


## License

This project is a test project for SOCIETE GENERALE's It intellectual service.
