package tests;

import manager.HelperBase;
import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;


public class RemoveContactTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().withEmail("alimych65@gmail.com").withPassword("Yv030665@"));
        }
        app.getHelperContact().provideContacts();
    }

    @Test
    public void removeFirstContact() {
        int sizeBefore = app.getHelperContact().sizeOfContactsList();
        // logger.info("Size before);
        app.getHelperContact().removeContact();
        app.getHelperContact().pause(3000);
        int sizeAfter = app.getHelperContact().sizeOfContactsList();
        // logger.info("Size before);
        Assert.assertTrue(sizeBefore - sizeAfter == 1);
        logger.info("Assert checks is quantity of contacts decreased by 1");
    }

    @Test
    public void removeAllContacts() {
        while (app.getHelperContact().isElementPresent(By.cssSelector(".contact-item_card__2SOIM"))) {
            app.getHelperContact().removeContact();
            app.getHelperUser().pause(3000);
        }
        Assert.assertTrue(app.getHelperUser().isRegistered());
        logger.info("Assert checks is element 'No contacts here' present");
    }


}
