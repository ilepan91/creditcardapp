# **Credit Card Issuance Management Application**

## **Overview**

The Credit Card Issuance Management Application is a Spring Boot-based application designed to manage the issuance of credit cards for individuals. The application allows users to:

- Add new individuals with relevant details.
- Search for individuals using their OIB (Unique Identifier).
- Generate a text file containing the individual's details, which can be used for further processing (e.g., card production).
- Delete individuals and mark their associated files as inactive.

## **Features**

- **Add Individuals**: Store records of individuals, including their first name, last name, OIB, and status.
- **Search Individuals**: Search for an individual by their OIB and retrieve their details.
- **Generate Text Files**: Create a text file containing the individual's details. The file name includes the OIB and a timestamp for uniqueness.
- **Delete Individuals**: Remove an individual’s record and mark their associated file as inactive.
- **RESTful API**: All operations are exposed as RESTful endpoints for easy integration.

## **Technologies Used**

- **Java**
- **Spring Boot**
- **MySQL Database**
- **Maven** (for dependency management)

## **Getting Started**

### **Prerequisites**

- Java 8 or higher
- Maven
- Git (for cloning the repository)
- MySQL Database

### **Setup Instructions**

## Getting Started

1. **Install MySQL:**

   Download and install MySQL and MySQL Workbench if you don't have it installed already. You can download the MySQL Community Server from the official website: [MySQL Downloads](https://dev.mysql.com/downloads/mysql/)

2. **Create a MySQL Database:**

   Open MySQL Workbench, and open `create_user.sql` script.
   After executing it, create a new connection: `creditcardapp`, where password should be set as `user1234`.

   If you're using different connecetion and credentials then:
   - Reconfigure the application's database connection properties in the `src/main/resources/application.properties` file.
   - Update the following properties with your MySQL credentials: <br/>
   `spring.datasource.url=jdbc:mysql://localhost:3306/user_directory` <br/>
   `spring.datasource.username=creditcarduser` <br/>
   `spring.datasource.password=your-mysql-password`

3. **Execute sql script(s) and run the application**

   - Download project to your file system or clone the repository: `git clone https://github.com/ilepan91/creditcardapp.git`
   - Login in created MySQL connection with correct credentials and go to `File -> Open SQL script`. Choose `credit_card_db.sql` and Execute it.
   - Open _creditcardapp_ project in your IDE and run the _CreditCardApplication.java_
     You can also run it from the command line:
        - `cd creditcardapp`
        - `./mvnw spring-boot:run` (or `mvnw spring-boot:run` for MicrosoftWin)

4. **Application uses Swagger/OpenApi and will start running on**  [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
   
### **API Endpoints**

Here are the key RESTful API endpoints:

- **Add a new individual:**
  - **Method**: `POST`
  - **Endpoint**: `/api/user/{userOib}`
  - **Request Body**:
    ```json
    {
      "firstName": "Ivan",
      "lastName": "Horvat",
      "oib": "05552352140",
      "status": "1"
    }
    ```

- **Search for an individual by OIB:**
  - **Method**: `GET`
  - **Endpoint**: `/api/user/{userOib}`

- **Generate a text file for an individual:**
  - **Method**: `GET`
  - **Endpoint**: `/api/generate/{userOib}`

- **Delete an individual:**
  - **Method**: `DELETE`
  - **Endpoint**: `/api/user/{userOib}`

