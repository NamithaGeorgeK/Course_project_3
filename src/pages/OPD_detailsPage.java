package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class OPD_detailsPage {
WebDriver driver1;
	
	@FindBy(xpath="//*[@id=\"aspnetForm\"]/table/tbody/tr[3]/td/table/tbody/tr[1]/td/table/tbody/tr[1]/td")
	public WebElement welcomeMsg;
	
	@FindBy(xpath="//*[@id=\"ctl00_ContentPlaceHolder1_LinkButton2\"]")
	public WebElement OPD_Home_PageBtn ;
	
	@FindBy(id="ctl00_ContentPlaceHolder1_lnkIPD")
	public WebElement IPD_Home_PageBtn ;
	
	@FindBy(id="ctl00_ContentPlaceHolder1_lblcapturepadata")
	public WebElement Capture_Patient_DataBtn ;
	
	@FindBy(id="ctl00_ContentPlaceHolder1_LinkButton1")
	public WebElement Send_Sms_ListBtn ;
	
	@FindBy(xpath="//*[@id=\"ctl00_lblMasterDate\"]")
	public WebElement dateDisplayed;
	
	@FindBy(xpath="//*[@id=\"ctl00_ContentPlaceHolder1_LinkButton2\"]")
	public WebElement book_AppointmentBtn;
		
  public OPD_detailsPage (WebDriver driver1) 
  {
	  this.driver1 = driver1; 
		PageFactory.initElements(driver1, this);
  }
  
	  public void pageWait() throws InterruptedException 
	  {
			Thread.sleep(3000);
	  }
  
}
