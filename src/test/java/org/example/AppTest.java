package org.example;


import com.microsoft.playwright.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AppTest


{
    private static final Logger logger = Logger.getLogger(AppTest.class.getName());
    static {
        try {
            FileHandler fileHandler = new FileHandler("signup.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(java.util.logging.Level.INFO);
        } catch (Exception e) {
            logger.severe("Failed to initialize logger: " + e.getMessage());
        }
    }
    public static void main(String[] args){
        try(Playwright playwrite = Playwright.create()){
            Browser browser = playwrite.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();
            Login login = new Login(page, logger);
            login.loginDashboard();
            LogOut logout = new LogOut(page, logger);
            logout.logoutDashboard();
            Registration register = new Registration(page, logger);
            register.signup();
        }
    }
}
