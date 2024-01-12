package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }
    }

    @Test
    public void RegistrationSuccess() {
        Random random = new Random();
        int z = (int) System.currentTimeMillis() / 1000 % 3600;
        User user = new User()
                .withEmail("volodar" + z + "@gmail.com")
                .withPassword("Vvolodar12345$");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isRegistered());
        //Assert.assertTrue(app.getHelperUser().isNoContactsHereDisplayed());

    }

    //@Test(description = "Bug report N ***", enabled = false)
    @Test
    public void registrationWrongEmail() {
        User user = new User()
                .withEmail("volodargmail.com")
                .withPassword("Vvolodar12345$");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password format"));

    }

    @Test
    public void registrationWrongPassword() {
        User user = new User()
                .withEmail("volodar@gmail.com")
                .withPassword("2345$");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password format"));

    }

    @Test
    public void registrationExistUser() {
        User user = new User()
                .withEmail("alimych65@gmail.com")
                .withPassword("Yv030665@");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("User already exist"));

    }

}
