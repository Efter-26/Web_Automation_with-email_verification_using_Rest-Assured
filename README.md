# Web Automation Project

This project automates a series of user actions on the [Daily Finance](https://dailyfinance.roadtocareer.net/) site, including registration, password reset, profile updates, and admin management tasks. The automation is built using Selenium, TestNG, and Rest-Assured for email verification. Standard test cases are documented in an Excel file, and the full automation process has been recorded.

## Project Overview
This automation project covers the following main functionalities:

1. Visit the site and register a new user wuth valid email. Assert that the "Congratulations" email is received.
2. Click on the reset password link. Write and assert 2 negative test cases.
3. Input the registered Gmail account and click "Send reset link."
4. Retrieve the reset email from Gmail and set a new password.
5. Log in with the new password to verify successful login.
6. Add two random items and assert that they appear on the item list.
7. Update the user's email to a new Gmail in the profile.
8. Logout and login with the updated email to confirm successful login; assert that login with the previous email fails.
9. Logout, then login with the admin account, retrieving credentials securely from the terminal.
10. Search by the updated email in the admin dashboard and assert that it displays correctly.

## Technologies and Libraries

- **Java**: Main programming language.
- **Selenium WebDriver**: For automating browser interactions.
- **Rest-Assured**: To interact with Gmail API and verify the registration email.
- **Google API**: For accessing Gmail data to verify email confirmation.
- **TestNG**: Testing framework for structuring and running tests.
- **JSON-Simple**: Library for reading and writing JSON files.
- **Faker**: For generating random test data.
- **Gradle**: Build automation tool.

## Setup and Execution

1. **Install Dependencies**: Ensure necessary dependencies are installed as specified in `build.gradle`.
2. **Configure Properties**: Set Gmail API access tokens and other configuration details in `config.properties`.
3. **Run Tests**: Execute tests with Admin email and password:
   ```bash
   gradle clean test -Pemail="admin@test.com" -Ppassword="admin123"
   allure generate allure-results --clean -output
   allure serve allure-results
   ```
## Allure Report
![image](https://github.com/user-attachments/assets/24d62f40-718f-4567-b364-5b648b28bad0)
![image](https://github.com/user-attachments/assets/0c90b7f5-d622-4649-aa22-205e423a7b0d)
![image](https://github.com/user-attachments/assets/daf751d9-df86-4c6d-ba27-9a098b522d35)
