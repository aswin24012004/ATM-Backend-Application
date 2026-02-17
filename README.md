# ATM Backend Application

A secure backend application for ATM operations built with **Java Servlets**, **MySQL**, and deployed on **Apache Tomcat 11**.

## 🚀 Features
- User authentication (`/api/login`)
- Balance inquiry (`/api/balance`)
- Withdraw funds (`/api/withdraw`)
- Deposit funds (`/api/deposit`)
- Transaction history (`/api/transactions`)

## 🛠️ Tech Stack
- Java 17
- Jakarta Servlet API 6.1
- MySQL 8.x
- Apache Tomcat 11
- Maven (WAR packaging)
- Gson (JSON responses)

## 📂 Project Structure
ATM-Backend/
│── pom.xml
│── src/
│   └── main/
│       ├── java/com/atm/servlet/   # Servlets
│       ├── java/com/atm/util/      # DB Utility
│       ├── resources/              # Config files
│       └── webapp/WEB-INF/web.xml  # Deployment descriptor

Code


Copy

## ⚙️ Setup Instructions

1. **Clone the repo**
   ```bash
   git clone https://github.com/your-username/ATM-Backend.git
   cd ATM-Backend
Configure Database

Create a MySQL database atmdb.

Add a users table:

sql


Copy
CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(50) UNIQUE NOT NULL,
  pin VARCHAR(10) NOT NULL,
  balance DOUBLE DEFAULT 0
);
Update DBUtil.java with your MySQL username/password.

Build the project

bash


Copy
mvn clean package
Deploy to Tomcat

Copy target/ATM-Backend.war into tomcat/webapps/.

Start Tomcat:

bash


Copy
./bin/startup.sh   # Linux/Mac
.\bin\startup.bat  # Windows
Test APIs

Login:

bash


Copy
curl -X POST -d "username=testuser&pin=1234" http://localhost:8080/ATM-Backend/api/login
Balance:

bash


Copy
curl "http://localhost:8080/ATM-Backend/api/balance?username=testuser"
🧪 Example Responses
json


Copy
# Login success
{"status":"success","message":"Login successful"}

# Balance inquiry
{"balance":5000.0}

# Withdraw error
{"status":"error","message":"Insufficient funds"}
📜 License
This project is for educational purposes. Modify and extend as needed.

Code


Copy

---

👉 I kept the README practical: setup, build, deploy, and test instructions. Would you like me to also create a **Postman collection JSON** with all five endpoints preconfigured, so you can import it and test your API instantly?






