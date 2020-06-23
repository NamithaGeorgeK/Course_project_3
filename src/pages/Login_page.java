package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login_page {
	WebDriver driver;
	
	@FindBy(id="txtUserName")
	public WebElement userName;
	
	@FindBy(id="txtPassword")
	public WebElement password ;
	
	@FindBy(css="#LoginButton")
	public WebElement loginButton ;
	
	@FindBy(xpath="//*[@id=\"imgReset0\"]")
	public WebElement resetButton;
	
	@FindBy(name="chkstaysignedin")
	public WebElement staySignedinCheckbox;
	
	@FindBy(xpath="//*[@id=\"form1\"]/div[3]/table/tbody/tr[4]/td[2]/table/tbody/tr[4]/td/a")
	public WebElement forgotPasswordlink ;
	
	@FindBy(xpath="//*[@id=\"header\"]/img")
	public WebElement pageName;	

	public Login_page(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		
	}
	  

}
