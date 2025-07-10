# QA Automation - Insight Portal

This project contains an end-to-end Selenium TestNG framework for automating Insight Portal workflows, particularly focusing on Adobe eSign flows and Export Control management.

---

## 📁 Project Structure

```
src/
 ├── main/
 │    └── java/
 │         ├── base/        # Base reusable components for framework
 │         └── pages/       # Page Object classes
 └── test/
      └── java/
           ├── base/       # Test-level base classes
           ├── constants/  # Environment/test constants
           ├── drivers/    # WebDriver setups
           ├── pages/      # Page-specific test implementations
           ├── resources/  # Supporting assets
           ├── tests/      # Test classes
           └── utils/      # Utility classes
```

---

## 🧪 Reports

Test execution reports are generated under:
```
/test_reports/
 - Adding_Esign_Flow_From_Email.html
 - Adobe_ESign_Flow.html
 - Esign_Agreement_PDF_Attachment_Flow.html
 - Esign_Agreement_PDF_Attachment_Positive_Flow.html
 - Forms_Management_Create_New_Form.html
```

---

## 📄 Test Data

PDF test data is located under:
```
/Test_Data/
 - Agreement Info 2025_03.pdf
```

---

## ⚙️ Technologies Used

- Java
- Selenium WebDriver
- TestNG
- ExtentReports
- Maven
- Page Object Model (POM)

---

## 🧠 Author

Shankar Venkatesan  
Feel free to raise issues or fork the repository.

---

## 📥 Cloning the Project

```bash
git clone https://github.com/SankarTechnossus/github-QA_Automation_InsightPortal.git
```
