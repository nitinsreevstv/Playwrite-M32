package org.example;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class LogOut {
    private final Logger logger;
    Page page;
    public LogOut(Page page, Logger logger) {
        this.page = page;
        this.logger = logger;
    }
    public void logoutDashboard(){
        try{
            Locator usernameDropdown = page.locator("xpath = //div[@class='w-8 h-8 text-sm bg-blue-500 rounded-full flex items-center justify-center text-white font-medium ']");
            usernameDropdown.click();
            logger.info("Clicked on Logout Button");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Logout")).click();
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(page.title()+ ".png")));
            logger.info("Screenshot Captured");
        }catch (Exception e){
            logger.info("Test failure -------------------------------------> " + e.getMessage());
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("FAILED_" + page.title() + ".png")));
        }
    }
}
