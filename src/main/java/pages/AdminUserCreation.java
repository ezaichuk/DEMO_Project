package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import util.UserRoles;

public class AdminUserCreation extends Page {

    @FindBy(how = How.XPATH, using = ".//div[@class='wrap']/h1")
    public WebElement headerofTab;

    @FindBy(how = How.ID, using = "user_login")
    public WebElement username;

    @FindBy(how = How.ID, using = "email")
    public WebElement email;

    @FindBy(how = How.ID, using = "first_name")
    public WebElement firstname;

    @FindBy(how = How.ID, using = "last_name")
    public WebElement lastname;

    @FindBy(how = How.ID, using = "url")
    public WebElement website;

    @FindBy(how = How.XPATH, using = "//*[@id='createuser']/table/tbody/tr[6]/td/button")
    public WebElement showPasswordButton;

    @FindBy(how = How.ID, using = "pass1-text")
    public WebElement password;

    @FindBy(how = How.XPATH, using = "//*[@id='createuser']/table/tbody/tr[6]/td/div/button[1]")
    public WebElement showhideButton;

    @FindBy(how = How.XPATH, using = "//*[@id='createuser']/table/tbody/tr[6]/td/div/button[2]")
    public WebElement cancelShowPasswordButton;

    @FindBy(how = How.NAME, using = "pw_weak")
    public WebElement useWeakPassword;

    @FindBy(how = How.ID, using = "send_user_notification")
    public WebElement sendNotification;

    @FindBy(how = How.ID, using = "role")
    public WebElement roleSelect;

    @FindBy(how = How.ID, using = "createusersub")
    public WebElement createUserButton;

    public AdminUserCreation(WebDriver driver) {
        super(driver);
    }

    public void SelectRole (String role){
        new Select(roleSelect).selectByValue(role);
    }

    public void CreateUser (String username, String email){
        this.username.sendKeys(username);
        this.email.sendKeys(email);
        createUserButton.click();
    }

    public void CreateUser (String username, String email, String password){
        showPasswordButton.click();
        this.password.clear();
        this.password.sendKeys("");
        this.password.sendKeys(password);
        System.out.println("pwd is : " + this.password.getText());
        useWeakPassword.click();
        CreateUser (username, email);
    }

    public void CreateUser (String username,
                            String email,
                            String firstname,
                            String lastname,
                            String website,
                            String password,
                            Boolean enableNotification,
                            String role) {
        this.firstname.sendKeys(firstname);
        this.lastname.sendKeys(lastname);
        this.website.sendKeys(website);
        SelectRole(role);
        if (!sendNotification.isSelected() != enableNotification ){
            sendNotification.click();
        }
        CreateUser(username, email, password);
    }

}
