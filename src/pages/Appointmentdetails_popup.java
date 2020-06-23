package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Appointmentdetails_popup {
	WebDriver driver3;
	//data to be passed
	String nameofPatient = "Ram";
	String phoneNumber = "12345345678";
	// String purposeofVisit = " ";(optional)
	String dateofAppointment ="06-July-20";
	
	//Check Availability pop-up elements
	@FindBy(xpath="//*[@id=\"ctl00_ContentPlaceHolder1_pnlapnmnt\"]/div[2]/table/tbody/tr[2]/td/table/tbody/tr[1]/td[1]/label/text()")
	public WebElement dateField;
	@FindBy(id="ctl00_ContentPlaceHolder1_txtapoinmntdate")
	public WebElement dateDropdown;
	@FindBy(xpath="//*[@id=\"ctl00_ContentPlaceHolder1_pnlapnmnt\"]/div[2]/table/tbody/tr[2]/td/table/tbody/tr[2]/td[1]/label/text()")
	public WebElement specializationField;
	@FindBy(xpath="//*[@id=\"ctl00_ContentPlaceHolder1_ddldoctorspecliz\"]")
	public WebElement specializationDropdown;
	@FindBy(xpath="//*[@id=\"ctl00_ContentPlaceHolder1_pnlapnmnt\"]/div[2]/table/tbody/tr[2]/td/table/tbody/tr[3]/td[1]/label/text()")
	public WebElement selectDoctorfield;
	@FindBy(xpath="//*[@id=\"ctl00_ContentPlaceHolder1_ddldoctorname\"]")
	public WebElement selectDoctordropdown;
	@FindBy(id="ctl00_ContentPlaceHolder1_grdbookstatus_ctl02_CheckBox1")
	public WebElement availableCheckbox;
	@FindBy(id="ctl00_ContentPlaceHolder1_btncloseApoinmnt")
	public WebElement availabilityPopup_closeBtn;
	
	//Book Appointment pop-up elements
	@FindBy(xpath="//*[@id=\"ctl00_ContentPlaceHolder1_Panel9\"]/div[2]/table/tbody/tr[2]/td[1]/label/text()")
	public WebElement patientName_field;
	@FindBy(id="ctl00_ContentPlaceHolder1_txtpname")
	public WebElement patientName_fieldTextbox;
	@FindBy(xpath="//*[@id=\"ctl00_ContentPlaceHolder1_Panel9\"]/div[2]/table/tbody/tr[3]/td[1]/label/text()")
	public WebElement contactNumber_field;
	@FindBy(id="ctl00_ContentPlaceHolder1_txtcontact")
	public WebElement contactNumber_fieldTextbox;
	@FindBy(xpath="//*[@id=\"ctl00_ContentPlaceHolder1_Panel9\"]/div[2]/table/tbody/tr[5]/td[1]/label/text()")
	public WebElement purpose_field;
	@FindBy(id="ctl00_ContentPlaceHolder1_txtprpse")
	public WebElement purpose_fieldTextbox;
	@FindBy(id="ctl00_ContentPlaceHolder1_btnclsbook")
	public WebElement bookAppointmentPopup_closeBtn;
	@FindBy(id="ctl00_ContentPlaceHolder1_btnbookok")
	public WebElement bookAppointment_Okbtn;
	
	public Appointmentdetails_popup (WebDriver driver3) 
	  {
		  this.driver3 = driver3; 
			PageFactory.initElements(driver3, this);
	  }
	
	public void checkAvailability_Method() 
	{
		dateSelect();
		try 
		{ 
			  Select specialznValue= new Select(specializationDropdown);
			  specialznValue.selectByVisibleText("Endocrinology");
			  explicitWait();
			  String docNameSelect="Dr. Gaurang Joshi";
			  selectDoctordropdown.click();
			  explicitWait();
			  List<WebElement> docNameList = driver3.findElements(By.xpath("//*[@id=\"ctl00_ContentPlaceHolder1_ddldoctorname\"]"));
			  for(int i=0; i<docNameList.size(); i++)
				{
					 String docnameArray=docNameList.get(i).getText();
							// Printing All the options from the drop-down
							//System.out.println(docnameArray);
					 if(docnameArray.contains(docNameSelect)) 
						{
							WebElement nametoClick=driver3.findElement(By.xpath("//*[@id=\"ctl00_ContentPlaceHolder1_ddldoctorname\"]/option[2]"));
							nametoClick.click();
							System.out.println("The name selected is "+nametoClick.getText());
						}
				}
			  explicitWait();
				WebDriverWait wait = new WebDriverWait(driver3, 3000);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceHolder1_grdbookstatus_ctl02_CheckBox1")));
				driver3.findElement(By.id("ctl00_ContentPlaceHolder1_grdbookstatus_ctl02_CheckBox1")).sendKeys(Keys.ENTER);
				WebElement available_Checkbox=availableCheckbox;
						
				if(!available_Checkbox.isSelected())
					available_Checkbox.click();//check-box gets selected and book appointment pop-up is displayed
					
				String booking_Handle=driver3.getWindowHandle();
							//clicking on an icon or button to go to a book appointment pop-up  window
				for(String booking_windowHandle:driver3.getWindowHandles())
							//creating an instance for book appointment pop-up window and then switching to the new window
					{
						driver3.switchTo().window(booking_windowHandle);			
					}
				bookAppointment_Method(nameofPatient,phoneNumber);
				driver3.switchTo().window(booking_Handle);
							
		}
		  catch (InterruptedException e)
		  {
				System.out.println("Interrupted Exception caught!");
				e.printStackTrace();
		  }	 
	}

	public void bookAppointment_Method(String patientName, String contactNumber) 
	{
		patientName_fieldTextbox.sendKeys(patientName);
		contactNumber_fieldTextbox.sendKeys(contactNumber);
		//purpose_fieldTextbox.sendKeys(visitPurpose);
		bookAppointment_Okbtn.click();
	}
	public void dateSelect() {
		 /* JavascriptExecutor Interface is use to handle date picker control */
		  JavascriptExecutor js = (JavascriptExecutor)driver3;
		  WebElement dateAppt=driver3.findElement(By.id("ctl00_ContentPlaceHolder1_txtapoinmntdate"));
		  
		  js.executeScript("document.getElementById('ctl00_ContentPlaceHolder1_txtDOB').removeAttribute('readonly')",dateAppt);
		 
		  dateAppt.clear();
		  dateAppt.click();
		  dateAppt.sendKeys(dateofAppointment);	
	}
	public void explicitWait() throws InterruptedException 
	{
		Thread.sleep(3000);
	}

}
