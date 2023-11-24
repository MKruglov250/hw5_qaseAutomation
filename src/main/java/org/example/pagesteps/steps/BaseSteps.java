package org.example.pagesteps.steps;

import lombok.extern.log4j.Log4j2;
import org.example.pagesteps.LoginPage;
import org.example.pagesteps.TestCasePage;
import org.example.pagesteps.TestPlanPage;

@Log4j2
public class BaseSteps {
    LoginPage loginPage = new LoginPage();
    TestCasePage testCasePage = new TestCasePage();
    TestPlanPage testPlanPage = new TestPlanPage();
}
