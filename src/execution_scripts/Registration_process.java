package execution_scripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.Appointmentdetails_popup;
import pages.Login_page;
import pages.OPD_detailsPage;
import pages.Patient_registration_page;

public class Registration_process {
	WebDriver driver;
	String baseURL="http://hms.techcanvass.co";
	
  @Test(dataProvider = "Credentials")
  public void bookingMethod(String userID, String userPwd) 
  {
	  try {
	  Login_page login_Obj=new Login_page(driver);
	  //Login Process
	  login_Obj.userName.sendKeys(userID);
	  login_Obj.password.sendKeys(userPwd);
	  login_Obj.loginButton.click();
	  //Click on OPD home page
	  OPD_detailsPage OPD_obj=new OPD_detailsPage(driver);
	  String date=OPD_obj.dateDisplayed.getText();
	  System.out.println(date);
	
	 // OPD_obj.pageWait();
	  OPD_obj.OPD_Home_PageBtn.click();
	  
	  OPD_obj.pageWait();
	  //Click on Book Appointment Button
	  OPD_obj.book_AppointmentBtn.click();
	  //Fill in the details
	  
	  OPD_obj.pageWait();//wait to switch to the pop-up.
	  String handle=driver.getWindowHandle();
		//clicking on an icon or button to go to a new pop-up  window
				for(String windowHandle:driver.getWindowHandles())
					//creating an instance for a new pop-up window and then switching to the new window
				{
					driver.switchTo().window(windowHandle);			
					
				}
				Appointmentdetails_popup fill = new Appointmentdetails_popup(driver);
				//Check availability pop-up data entry and book the appointment
					fill.checkAvailability_Method();
					driver.switchTo().window(handle);	
				//patient registration
				Patient_registration_page pr=new Patient_registration_page(driver);
				OPD_obj.pageWait();
				 pr.searchMethod();
				 String registerHandle=driver.getWindowHandle();
					//clicking on an icon or button to go to a new pop-up  window
							for(String regPage_windowHandle:driver.getWindowHandles())
								//creating an instance for a new pop-up window and then switching to the new window
							{
								driver.switchTo().window(regPage_windowHandle);			
								
							}
			System.out.println("Inside Registration window");				
					Thread.sleep(1000);				
					pr.registerDetails();
					driver.switchTo().window(registerHandle);	
	} catch (InterruptedException e) {
		System.out.println("Interrupted Exception caught! \nPage did not load properly.");
		e.printStackTrace();
	}    
  }
  
  @BeforeMethod
  public void beforeMethod() 
  {
		System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(baseURL);
		driver.manage().window().maximize();
  }

 @AfterMethod
  public void afterMethod() 
  {
	  driver.quit();
  }

  @DataProvider
  public Object[][] Credentials() {
    return new Object[][]
    {
      new Object[] { "avinash", "avinash" }    
    };
  }

}
