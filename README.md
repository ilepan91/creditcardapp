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

### **Getting Started**

1. **Access the Application:**

   The application is deployed on AWS Elastic Beanstalk and can be accessed at the following URL:

   [Credit Card App](http://creditcardtestapp-env.eba-3pibv92s.us-east-1.elasticbeanstalk.com/swagger-ui/index.html)

2. **Explore the API:**

   The application uses Swagger/OpenAPI. You can explore and interact with the API using the Swagger UI available at the URL above.

   
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

