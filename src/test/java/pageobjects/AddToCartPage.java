package pageobjects;

import org.openqa.selenium.By;

public class AddToCartPage {

    public static final By search = By.name("search");
//    public static final By close_ad;

    public static final By item_name_found = By.xpath("//*[text()='OZtrail Genesis 3 Person Dome Tent']");

    public static final By search_button = By.xpath("//*[@id=\"shopfront-app\"]/header/div/div/div[2]/form/div[1]/div[2]/button");
    public static final By AddToCartButton = By.xpath("//*[@id=\"42520364\"]/article/div[1]/div[3]/div/button");
    public static final By GoToCartButton = By.xpath("/html/body/div[2]/div[1]/div/div/div/div/div[2]/div/div[1]/div/div[1]/section/a");
}
