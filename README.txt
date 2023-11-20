This is a project to automate QASE.io website testing. 

Tests include:
1. Test Cases: create, read, update, delete.
2. Test Plans: create, read, update, delete.
3. E2E test to create Test Plan with 3 Test Cases.

Using:
a. Listener, Retry for TestNG
b. Parallel launch (several browsers)
c. Page-Object approach.
d. Model classes for User, Test Plan, Test Case.
e. Patterns: Builder, Page Step, Chain of Responsibilities.
f. Store common settings in a Property-file and read from it.

Additionally:
I. Separate log-file for each launch.
II. Store data for Test Case, Test Plan, User in a JSON-file.
III. Add Screenshoter to the Listener file.