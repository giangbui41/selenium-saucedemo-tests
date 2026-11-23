package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;

public class LoginTest extends BaseTest {

    @Test
    public void loginWithValidAccount() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        Assert.assertEquals(new InventoryPage(driver).getTitle(), "Products");
    }

    @Test
    public void loginWithWrongPassword() {
        LoginPage login = new LoginPage(driver);
        login.login("standard_user", "wrong_pass");
        Assert.assertTrue(login.getErrorMessage().contains("do not match"));
    }

    @Test
    public void loginWithEmptyFields() {
        LoginPage login = new LoginPage(driver);
        login.login("", "");
        Assert.assertTrue(login.getErrorMessage().contains("Username is required"));
    }

    @Test
    public void loginWithLockedOutUser() {
        LoginPage login = new LoginPage(driver);
        login.login("locked_out_user", "secret_sauce");
        Assert.assertTrue(login.getErrorMessage().contains("locked out"));
    }
}