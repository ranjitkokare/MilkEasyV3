# MilkEasy - Makes Milk Easy

MilkEasy is a web application designed to streamline dairy business operations, enhancing efficiency and transparency between farmers and dairy owners. The platform facilitates user authentication, transaction management, approvals, email notifications, and more, providing a comprehensive solution for managing dairy-related activities.

## Features

- **User Authentication:** Secure login and registration for users.
- **Transaction Management:** Manage financial transactions between farmers and dairy owners.
- **Approval Process:** Streamlined approval system for transactions and activities.
- **Email Notifications:** Automated email alerts for important events (e.g., approvals, password recovery).
- **Dashboard:** Comprehensive dashboard displaying key metrics and data.
- **PDF Statement Generation:** Generate detailed PDF statements for financial records.
- **Profile Management:** Users can manage and update their profiles.

## Technologies Used

- **Frontend:** HTML, CSS
- **Backend:** Java, Spring Boot, Spring Security, Thymeleaf
- **Database:** SQL, Spring Data JPA (Hibernate)
- **Others:** Git, GitHub for version control, Maven for project management

## Installation

### Prerequisites

- Java 8 or higher
- Maven
- MySQL
- Git

### Steps to Setup

1. **Clone the repository:**
   ```sh
   git clone https://github.com/your-username/milkeasy.git
   cd milkeasy
   
2. **Configure MySQL:**
- Create a new database named milkeasy.
- Update the application.properties file in the src/main/resources directory with your MySQL credentials.

3. **Build the project:**
   ```sh
   Copy code
   mvn clean install

4. **Run the application:**
   ```sh
   Copy code
   mvn spring-boot:run

5. **Access the application:**

- Open your browser and navigate to http://localhost:8080.

## Usage
**User Authentication**
- Users can register and log in to the system.
- Password recovery functionality is available via email.
**Transaction Management**
- Manage transactions between farmers and dairy owners.
- Generate and download PDF statements of transactions.
**Dashboard**
- View metrics related to dairy operations, including transaction summaries and user activities.
**Profile Management**
- Users can view and update their personal information.

## Project Structure
   ```bash
   Copy code
   src/main/java/com/milkeasy
│
├── controller          # Handles HTTP requests and responses
├── service             # Contains the business logic
├── repository          # Interfaces for database access
├── model               # Java classes representing database entities
└── exception           # Custom exception handling
│
src/main/resources
├── application.properties  # Main configuration file for the Spring Boot application
└── templates               # Thymeleaf templates for the front-end
