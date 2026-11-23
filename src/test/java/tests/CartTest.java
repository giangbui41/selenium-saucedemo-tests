package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;

public class CartTest extends BaseTest {

    private InventoryPage inventory;

    @BeforeMethod
    public void loginFirst() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        inventory = new InventoryPage(driver);
    }

    @Test
    public void addOneProductToCart() {
        inventory.addToCart("sauce-labs-backpack");
        Assert.assertEquals(inventory.getCartCount(), "1");
    }

    @Test
    public void addTwoProductsToCart() {
        inventory.addToCart("sauce-labs-backpack");
        inventory.addToCart("sauce-labs-bike-light");
        inventory.openCart();
        Assert.assertEquals(new CartPage(driver).getItemCount(), 2);
    }

    @Test
    public void removeProductFromCart() {
        inventory.addToCart("sauce-labs-backpack");
        inventory.openCart();
        CartPage cart = new CartPage(driver);
        cart.removeFirstItem();
        Assert.assertEquals(cart.getItemCount(), 0);
    }
}