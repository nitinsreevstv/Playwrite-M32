package M32.TestCases;

import M32.Page.Login;
import M32.setup.SetUp;
import com.microsoft.playwright.Page;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static M32.setup.SetUp.logger;

public class LoginLogoutTest {
    SetUp setup;
    Page page;

    @BeforeClass
    public void setUp() {
        setup = new SetUp();
        page = setup.launchBrowser();
    }

    @Test
    public void loginLogoutFlow() {
        Login login = new Login(page);
        login.loginDashboard();
        login.logoutDashboard();
        logger.info("Login & Logout Test Passed");
    }

    @AfterClass
    public void tearDown() {
        setup.closeBrowser();
    }
}
