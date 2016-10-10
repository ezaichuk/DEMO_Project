package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AdminUserManagement extends Page {

    @FindBy(xpath = ".//a[@class='page-title-action']")
    public WebElement addNewElement;

    @FindBy(how = How.ID, using = "user-search-input" )
    public WebElement userSearch;

    @FindBy(how = How.ID, using = "search-submit")
    public WebElement searchButton;

    @FindBys({@FindBy (xpath = ".//tbody[@id='the-list']/tr")})
    public List<WebElement> userList;

    @FindBy(how = How.ID, using = "cb-select-all-1")
    public WebElement selectAllCheckbox;

    @FindBy(how = How.ID, using = "bulk-action-selector-top")
    public WebElement bulkActions;

    @FindBy(how = How.ID, using = "doaction")
    public WebElement submitActionButton;

    @FindBy(how = How.XPATH, using = ".//*[@id='message']/p")
    public WebElement message;

    @FindBy(how = How.XPATH, using = ".//*[@id='the-list']/tr/td")
    public WebElement searchMessage;

    public void doSearch(String search){
        userSearch.clear();
        userSearch.sendKeys(search);
        searchButton.click();
    }

    public AdminUserManagement(WebDriver driver) {
        super(driver);
    }

    public boolean deleteUser(String username) {
        doSearch(username);
        if (userList.size() != 1) {
            return false;
        }else{
            selectAllCheckbox.click();
            selectBulkAction("delete");
            submitActionButton.click();
            return true;
        }
    }

    public boolean deleteUsers(String filter) {
        doSearch(filter);
        if (userList.size() == 0) {
            return false;
        }else{
            selectAllCheckbox.click();
            selectBulkAction("delete");
            submitActionButton.click();
            return true;
        }
    }

    public void addNewElement(){
        addNewElement.click();
    }

    public void selectBulkAction (String action){
        new Select(bulkActions).selectByValue(action);
    }
}