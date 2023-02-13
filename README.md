**This is a web automation framework built using Java, Selenium, TestNG, Extent Reports and log4j.This framework by default runs on Chrome browser but it is also capable of running on Chrome headless mode, Edge, Mozilla and on cloud (Browserstack)**
**

**To run the E2E test:**

Precondition: Java and Maven have been installed in your system.

1)Download project

2)Open Terminal

3)Go to Project Directory

4)Type "mvn test" to run the test

You can view the result of the test on Extent reports in /reports folder

**To run in Chromeheadless mode, give this command:**

mvn test -Dbrowser=Chromeheadless

**To run on Edge, give this command:**

mvn test -Dbrowser=Edge

**To run on Firefox, give this command:**

mvn test -Dbrowser=Firefox

**To run on Browserstack:**

Precondition: You must input your BrowserStack username and accesskey in Capabilities class.

Give this command:

mvn test -Dbrowser=cloudtest




