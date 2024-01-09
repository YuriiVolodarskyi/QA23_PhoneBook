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
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isRegistered());

    }

}
