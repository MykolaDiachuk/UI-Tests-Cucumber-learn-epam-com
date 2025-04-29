# UI-Tests-Cucumber-learn-epam-com

### Available Settings

| Property      | Description                                | Example Values                                                    | Default Value            |
|---------------|--------------------------------------------|-------------------------------------------------------------------|--------------------------|
| `suiteName`   | Name of the TestNG suite XML file to run   | `CucumberCatalogPageSuit.xml` | `CucumberCatalogPageSuit.xml` |
| `threadCount` | Number of threads to run tests in parallel | `1`, `2`, `6`, `10`                                               | `4`                      |
| `env`         | Type of environment                        | `qa`, `dev`                                                       | `qa`                     |


---

### Run Tests with Maven

To run all tests in parallel use:

```bash
mvn clean test -DthreadCount=6
```
---
After test execution, you can generate the Allure report with:

```bash
mvn allure:report
 ```
Report can be find in `\target\report\allure-report\index.html`
