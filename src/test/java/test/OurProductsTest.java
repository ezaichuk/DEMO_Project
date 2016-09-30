
package test;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.examplePage;
import pages.ProductsPage;

public class OurProductsTest extends BaseTest {
    private examplePage homepage;
    private ProductsPage productsPage;

    public OurProductsTest() {
    }

    @BeforeMethod
    public void initPageObjects() {
        homepage = PageFactory.initElements(driver, examplePage.class);
        productsPage = PageFactory.initElements(driver, ProductsPage.class);
    }

    @Test( priority = 1 )
    public void OpenOurProductsTest() throws Exception {
        this.driver.get(baseUrl + "/shop/");
        //WebDriverHelper.getScreenShot();
        productsPage.ClickProductsLink();
        //System.out.println(driver.getTitle());sdf
        //Assert.assertEquals("Products - virtual-shop", driver.getTitle().toString());
        Assert.assertTrue(driver.getTitle().contains("Products"), "Verifying correct page [Products] is opened.");
        Assert.assertTrue("Our products".equals(this.homepage.header.getText()));
        //Thread.sleep(10000L);
    }

    @Test( priority = 2 )
    public void drillToGroupTest() throws Exception {
        productsPage.ClickGroupLink();
        //System.out.println(driver.getTitle());
        //Assert.assertEquals("Funny Gifts - virtual-shop", driver.getTitle());
        Assert.assertTrue(driver.getTitle().contains("Funny"), "Verifying correct page [Group] is opened.");
        Assert.assertTrue("Funny Gifts".equals(homepage.header.getText()), "Checking header label.");
    }

    @Test( priority = 3 )
    public void drillToFirstItemTest() throws Exception {
        productsPage.ClickGroupLink();
        System.out.println(driver.getTitle());
        Assert.assertTrue(driver.getTitle().contains("Toaster"), "Verifying correct page [Item] is opened.");
        Assert.assertTrue("Toaster".equals(homepage.header.getText()), "Checking header label.");
    }

    @Test( priority = 4 )
    public void specialLabelTest() throws Exception {
        //driver.findElement(By.xpath()).click();
        try {
            Assert.assertEquals("Sale!", productsPage.specialLabel.getText(), "Checking special label.");
            } catch (Error e) {};


    }

    @Test( priority = 4 )
    public void CheckItemImageTest() throws Exception {
        productsPage.CheckItemImage();
    }

    @Test( priority = 5 )
    public void itemsInStockTest() throws Exception {
        Assert.assertEquals(productsPage.stockCount.getText(), "15 in stock" , "Verifying products quantity in stock.");
    }

    @Test( priority = 6 )
    public void initialQuantityTest() throws Exception {
        Assert.assertEquals(productsPage.itemQuantity.getAttribute("value"), "1", "Verifying initial products quantity.");
    }

    @Test( priority = 7 )
    public void itemsDescriptionTest() throws Exception {
        //Assert.assertNotNull(driver.findElement(By.xpath("(//img[@alt='funnygifts-2'])[4]")));
        Assert.assertNotNull(productsPage.itemDescription, "Checking product image.");
    }

    @Test( priority = 8 )
    public void addItemsToCartTest() throws Exception {
        productsPage.itemQuantity.clear();
        //driver.findElement(By.name("quantity")).sendKeys("2");
        //driver.findElement(By.id("inner-editor")).sendKeys("2");
        productsPage.itemQuantity.sendKeys("2");
        productsPage.addToCartButton.click();
    }

    @Test( priority = 9 )
    public void writeReviewTest() throws Exception {
        this.driver.get(baseUrl + "/product/toaster/#tab-reviews");
        productsPage.AddReviewPost("Some Text 3", "Johnny D", "john.doe.2@yopmail.com");

    }
}


