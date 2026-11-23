package pages;

import org.openqa.selenium.*;

public class InventoryPage extends BasePage {
    private final By title = By.className("title");
    private final By cartLink = By.className("shopping_cart_link");
    private final By cartBadge = By.className("shopping_cart_badge");

    public InventoryPage(WebDriver driver) { super(driver); }

    public String getTitle() { return getText(title); }

    public void addToCart(String productId) {
        // productId ví dụ: "sauce-labs-backpack"
        click(By.id("add-to-cart-" + productId));
    }

    public String getCartCount() { return getText(cartBadge); }

    public void openCart() { click(cartLink); }
}