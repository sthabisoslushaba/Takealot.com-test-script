package pageobjects;

import org.openqa.selenium.By;

public class AddToCartPage {


    public static final By CookieButton = By.cssSelector(
            "#shopfront-app > div.header-module_bottom-banners-container_3F8RC > div > div > button"
    );

    public static final By LoginButton = By.cssSelector(
            "#shopfront-app > div.top-nav.top-nav-module_top-nav_2cmJW > div > div > div.auto.cell > div > div.auto.cell > ul > li:nth-child(1) > a"
    );
    public static final By UserEmailInput = By.cssSelector(
            "#customer_login_email"
    );
    public static final By UserPasswordInput = By.cssSelector(
            "#customer_login_password"
    );

    public static final By SignInButton = By.xpath(
            "/html/body/div[5]/div/div/div/div/div/div/div[1]/div/div/div[1]/form/div[6]/div/button"
    );

    public static final By LoggedInUser = By.cssSelector(
            "#shopfront-app > div.top-nav.top-nav-module_top-nav_2cmJW > div > div > div.auto.cell > div > div.auto.cell > ul > li.top-nav-module_name-item_3ROu0"
    );

    public static final By search = By.name("search");
    public static final By search_button = By.xpath("//*[@id=\"shopfront-app\"]/header/div/div/div[2]/form/div[1]/div[2]/button");
    public static final By AddToCartButton = By.xpath("//*[@id=\"42520364\"]/article/div[1]/div[3]/div/button");
    public static final By GoToCartButton = By.xpath("/html/body/div[2]/div[1]/div/div/div/div/div[2]/div/div[1]/div/div[1]/section/a");
}
