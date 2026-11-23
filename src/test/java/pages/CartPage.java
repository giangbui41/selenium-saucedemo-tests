package pages;

import org.openqa.selenium.*;
import java.util.List;

public class CartPage extends BasePage {
    private final By items = By.className("cart_item");
    private final By checkoutBtn = By.id("checkout");
    private final By removeBtn = By.cssSelector("button[id^='remove']");

    public CartPage(WebDriver driver) { super(driver); }

    public int getItemCount() {
        List<WebElement> list = driver.findElements(items);
        return list.size();
    }

    public void removeFirstItem() { click(removeBtn); }

    public void checkout() { click(checkoutBtn); }
}