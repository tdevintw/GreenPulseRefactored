
# GreenPulse

GreenPulse is a Java-based console application designed to help users measure and monitor their carbon footprints. By offering features such as account management, real-time tracking of carbon consumption, and analysis tools, GreenPulse aims to empower individuals to make more sustainable choices. The application provides users with insights into their environmental impact and helps them track their carbon footprint over time.


## 🚀 About Me
Coding enthusiast and full stack developer who loves solving problems and bringing ideas to reality using web development tools.

I'm a developer who loves and uses Laravel to create web applications. My motto in web development is to create something that I want to use as a client because I think that if we understand our customers' needs, we will satisfy them. Web development is not just about a website but instead a brand and an identity that represents the developer and the client. I will do the best I can to develop the best product.


## Features

- **User Registration and Login**: Users can create an account and log in to track their consumption.
- **Manage Consumptions**: Users can add carbon consumption data with start and end dates.
- **Generate Reports**: Users can generate reports showing their average daily, monthly, and yearly carbon consumption.
- **View and Edit Personal Information**: Users can view and update their personal information.
- **View Consumptions and Reports**: Users can view a list of their consumptions and reports.
-**Filter Database** : users can filter users and consumptions  by totalConsumption or consumption within a range of time or by activity...etc 

## Code Overview

The project contains the following key components:


- **Auth/**
  - **Login**: Handles user login functionality.
  - **Register**: Manages user registration processes.

- **config/**
  - **Database**: Contains configuration settings for database connections.

- **Domain/**
  - **Enum/**
    - **AllTypesOfConsumption**: Enum class representing all types of consumption.
    - **ConsumptionType**: Enum class defining different types of consumption.
  - **Accommodation**: Represents accommodation-related consumption data.
  - **Consumption**: Represents a carbon consumption entry with details such as the quantity, start date, and end date.
  - **Food**: Represents food-related consumption data.
  - **Report**: Generates reports based on user consumption data.
  - **Transport**: Represents transport-related consumption data.
  - **User**: Represents a user in the system, including personal information and a list of consumptions and reports.

- **Repository/**
  - **ConsumptionRepository**: Data access layer for handling consumption data.
  - **ReportRepository**: Data access layer for handling report data.
  - **UserRepository**: Data access layer for handling user data.

- **Services/**
  - **Implementations/**
    - **ConsumptionService**: Business logic related to consumption data.
    - **ReportService**: Business logic related to report generation.
    - **UserService**: Business logic related to user management.

- **utils/**
  - **ConsumptionRange**: Utility functions for handling consumption ranges.
  - **TimeUtil**: Utility functions for time-related operations.
  - **TotalConsumption**: Utility functions for calculating total consumption.

- **Main**: Entry point of the application, handles user menus and interactions.




## 🛠 Skills
<p>
    <img src="https://skillicons.dev/icons?i=git,idea,java" height="45" alt="html5 logo"  />

</p>
