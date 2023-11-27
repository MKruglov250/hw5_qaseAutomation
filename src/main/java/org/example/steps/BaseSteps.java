package org.example.steps;

import lombok.extern.log4j.Log4j2;
import org.example.LoginPage;
import org.example.TestCasePage;
import org.example.TestPlanPage;

@Log4j2
public class BaseSteps {
    LoginPage loginPage = new LoginPage();
    TestCasePage testCasePage = new TestCasePage();
    TestPlanPage testPlanPage = new TestPlanPage();
}
