# Selenium Test Automation – Saucedemo

An automated UI test suite for the demo e-commerce website [saucedemo.com](https://www.saucedemo.com), built with **Java, Selenium WebDriver, TestNG and Maven** using the **Page Object Model (POM)** design pattern.

This is a learning project covering the main user flows: **login, cart and checkout**.

## Tech stack

| Tool | Purpose |
|---|---|
| Java 17+ | Programming language |
| Selenium WebDriver 4.27 | Browser automation |
| TestNG 7.x | Test framework, assertions, data-driven testing |
| Maven | Build tool and dependency management |
| Git / GitHub | Version control |
| Google Chrome | Test browser |

## Project structure

```
selenium-saucedemo-tests
├── pom.xml
├── testng.xml                     # Test suite configuration
└── src/test/java
    ├── base
    │   └── BaseTest.java          # Browser setup and teardown
    ├── pages                      # Page Object classes
    │   ├── BasePage.java          # Common actions with explicit waits
    │   ├── LoginPage.java
    │   ├── InventoryPage.java
    │   ├── CartPage.java
    │   └── CheckoutPage.java
    └── tests                      # Test classes
        ├── LoginTest.java
        ├── CartTest.java
        └── CheckoutTest.java
```

## Design highlights

- **Page Object Model:** each page is a class holding its locators and actions. Test classes contain only test logic and assertions, which keeps tests readable and easy to maintain.
- **Explicit waits:** `WebDriverWait` with `ExpectedConditions` is used in `BasePage` instead of `Thread.sleep`, which makes tests more stable.
- **Data-driven testing:** the checkout validation tests use a TestNG `@DataProvider` to run the same test with different invalid inputs.
- **Clean test environment:** `BaseTest` runs Chrome in incognito mode and disables the password manager, so browser popups do not interfere with the tests. Each test starts with a fresh browser session.

## Test cases

| ID | Area | Scenario | Expected result |
|---|---|---|---|
| TC01 | Login | Log in with a valid account | Products page is displayed |
| TC02 | Login | Log in with a wrong password | Error message is displayed |
| TC03 | Login | Log in with empty username and password | "Username is required" error |
| TC04 | Login | Log in with a locked-out user | "locked out" error |
| TC05 | Cart | Add one product to the cart | Cart badge shows 1 |
| TC06 | Cart | Add two products to the cart | Cart contains 2 items |
| TC07 | Cart | Remove a product from the cart | Cart is empty |
| TC08 | Checkout | Complete checkout with valid information | "Thank you for your order!" is displayed |
| TC09 | Checkout | Leave first name empty | "First Name is required" error |
| TC10 | Checkout | Leave last name empty | "Last Name is required" error |
| TC11 | Checkout | Leave postal code empty | "Postal Code is required" error |

**Total: 11 test cases.**

## Getting started

### Prerequisites

- JDK 17 or later
- Google Chrome (Selenium 4 manages the driver automatically)
- Maven, or an IDE with built-in Maven support such as IntelliJ IDEA

### Run the tests

Clone the repository:

```bash
git clone https://github.com/<your-username>/selenium-saucedemo-tests.git
cd selenium-saucedemo-tests
```

Run the whole suite:

```bash
mvn test
```

Or, in IntelliJ IDEA, open the **Maven** panel, then **Lifecycle → test**. You can also right-click `testng.xml` and choose **Run**.

### View the report

After the run, open the TestNG report in your browser:

```
target/surefire-reports/index.html
```

![Test report](docs/test-report.png)

## Known notes

- Tests run against a public demo site, so they depend on its availability and current text and layout. If the site changes, locators or expected messages may need to be updated.
- You may see `CDP implementation` warnings in the console when your Chrome version is newer than Selenium's supported version. They do not affect the test results.

## Possible improvements

- Capture a screenshot automatically when a test fails (TestNG `ITestListener`)
- Run tests in headless mode and on multiple browsers
- Add product sorting and product detail tests
- Read test data from external files (CSV or JSON)
- Generate richer reports (Allure or ExtentReports)
- Run the suite in a CI pipeline (GitHub Actions)

## Author

**Bui Huong Giang**
Information Technology student, Ton Duc Thang University
Email: buigiang0541@gmail.com
