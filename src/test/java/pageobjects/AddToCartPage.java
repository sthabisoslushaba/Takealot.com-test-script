package pageobjects;

import org.openqa.selenium.By;

public class AddToCartPage {


    public static final By CookieButton = By.cssSelector(
            "#shopfront-app > div.header-module_bottom-banners-container_3F8RC > div > div > button"
    );

    public static final By AdButton = By.cssSelector("body > div.ab-iam-root.v3.ab-animate-in.ab-animate-out.ab-effect-modal.ab-show > div.ab-in-app-message.ab-background.ab-modal-interactions.ab-modal.ab-centered > button");

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

    public static final By LoggedInUserName = By.cssSelector(
            "#shopfront-app > div.top-nav.top-nav-module_top-nav_2cmJW > div > div > div.auto.cell > div > div.auto.cell > ul > li.top-nav-module_name-item_3ROu0"
    );
    public static final By addToCartButton = By.cssSelector("#shopfront-app > div.pdp.pdp-module_pdp_1CPrg > div.grid-container.pdp-module_pdp-grid-container_1Rz6k > div:nth-child(2) > aside > div.pdp-module_sidebar-buybox_1m6Sm > div.buybox-actions-container.buybox-module_buybox-actions_2g4b2 > div > div > div.action-cart.buybox-actions-module_button-cell_2dQyM.buybox-actions-module_add-to-cart-cell_3fXyS > button");
    public static final By stayOnTab=  By.cssSelector("#shopfront-app > div.grid-container.search-listings-module_search-listings_2Lw_d > div.grid-x.grid-margin-x > div.cell.auto > div.toolbar > div.grid-x.toolbar-module_container_1Uk1k > div.cell.small-12.medium-auto.medium-order-2.large-shrink > div > div:nth-child(1) > div > div > div > button > div");
    public static final By Item = By.id("54864197");
    public static final By search = By.name("search");
    public static final By search_button = By.xpath("//*[@id=\"shopfront-app\"]/header/div/div/div[2]/form/div[1]/div[2]/button");
    public static final By AddToCartButton = By.xpath("//*[@id=\"42520364\"]/article/div[1]/div[3]/div/button");
    public static final By GoToCartButton = By.xpath("/html/body/div[2]/div[1]/div/div/div/div/div[2]/div/div[1]/div/div[1]/section/a");
}
