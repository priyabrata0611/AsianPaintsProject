# AsianPaintsProject
# 🎨 Asian Paints – Inspiration Module Automation

This module automates the **Inspiration** tab of the Asian Paints website using Selenium WebDriver and TestNG. It is designed using the Page Object Model (POM) and supports cross-browser execution and Extent Report generation.

---

## 🔧 Features

- Automates the complete user flow of the **Inspiration** section
- Page Object Model (POM) implementation for scalability
- TestNG for test execution and reporting
- Base class for common setup/teardown logic
- Supports Chrome, Edge, and Firefox browsers
- Extent Reports for detailed HTML reporting
- Configurable via external `config.properties`

---

## 📂 Directory Structure

InspirationModule/
│
├── src/
│ ├── main/
│ │ └── java/
│ │ ├── pages/ # POM classes for Inspiration tab
│ │ ├── base/ # Base class for browser setup
│ │ └── utils/ # Utility classes (ConfigReader, WebDriverUtils, etc.)
│
│ └── test/
│ └── java/
│ └── tests/ # Test classes for Inspiration tab
│
├── config.properties # Configuration file
├── testng.xml # TestNG suite file
├── pom.xml # Maven dependencies
├── ExtentReports/ # Test execution reports
└── README.md # Project documentation


---

## ⚙️ Setup Instructions

### 1️⃣ Prerequisites

- Java 8 or above
- Maven
- Chrome / Edge / Firefox
- IDE with TestNG support (e.g., IntelliJ, Eclipse)
- Internet connection (for WebDriverManager)

---

### 2️⃣ Installation

Clone the repository or download the module files, then install dependencies:

```bash
mvn clean install

👤 Module Owner
Name: Priyabrata Behera

Module: Inspiration Tab

Status: ✅ Completed

Email: priyabratabehera0611@gmail.com

🧰 Tech Stack
Java

Selenium WebDriver

TestNG

Maven

Extent Reports

WebDriverManager

✅ Best Practices
POM design pattern

Reusable utility methods

Externalized configuration

Clean test structure for scalability

📬 Support
For any issues, please reach out to:

📧 priyabratabehera0611@gmail.com
