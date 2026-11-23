package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;

public class CheckoutTest extends BaseTest {

    @BeforeMethod
    public void prepareCart() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        InventoryPage inventory = new InventoryPage(driver);
        inventory.addToCart("sauce-labs-backpack");
        inventory.openCart();
        new CartPage(driver).checkout();
    }

    @Test
    public void checkoutSuccess() {
        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.fillInfo("Giang", "Bui", "700000");
        checkout.finish();
        Assert.assertEquals(checkout.getCompleteMessage(), "Thank you for your order!");
    }

    @DataProvider(name = "invalidInfo")
    public Object[][] invalidInfo() {
        return new Object[][] {
                {"", "Bui", "700000", "First Name is required"},
                {"Giang", "", "700000", "Last Name is required"},
                {"Giang", "Bui", "", "Postal Code is required"}
        };
    }

    @Test(dataProvider = "invalidInfo")
    public void checkoutWithMissingInfo(String f, String l, String zip, String expected) {
        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.fillInfo(f, l, zip);
        Assert.assertTrue(checkout.getErrorMessage().contains(expected));
    }
}