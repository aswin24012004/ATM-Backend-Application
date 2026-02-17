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
│       └── webapp/WEB-INF/web.xml  # Deployment descripton


## ⚙️ Setup Instructions

### 1. Clone the repo
```bash
git clone https://github.com/your-username/ATM-Backend.git
cd ATM-Backend
```
### 2. Configure Database
Create a MySQL database atmdb and add a users table:

sql
CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(50) UNIQUE NOT NULL,
  pin VARCHAR(10) NOT NULL,
  balance DOUBLE DEFAULT 0
);

Update DBUtil.java with your MySQL username/password.

3. Build the project
bash
mvn clean package
4. Deploy to Tomcat
Copy the generated WAR file:

```Code
target/ATM-Backend.war → <TOMCAT_HOME>/webapps/
Start Tomcat:
```
```bash
./bin/startup.sh   # Linux/Mac
.\bin\startup.bat  # Windows

```
5. Test APIs
Login:

```bash
curl -X POST -d "username=testuser&pin=1234" http://localhost:8080/ATM-Backend/api/login
Balance:
```
```bash
curl "http://localhost:8080/ATM-Backend/api/balance?username=testuser"
```

🧪 Example Responses
json
# Login success
{"status":"success","message":"Login successful"}

# Balance inquiry
{"balance":5000.0}

# Withdraw error
{"status":"error","message":"Insufficient funds"}
📜 License
This project is for educational purposes. Modify and extend as needed.

---

This version removes the duplicated “Code / Copy” markers and ensures all commands are properly fenced in code blocks.  

👉 Would you like me to also generate a **Postman collection JSON file** with all five endpoints (`login`, `balance`, `withdraw`, `deposit`, `transactions`) preconfigured, so you can import it directly into Postman and test your API without typing each request manually?
