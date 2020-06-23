package pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Patient_registration_page {
	WebDriver driver4;
	@FindBy(id="ctl00_ContentPlaceHolder1_txtsrchP")
	public WebElement patientName_Searchbox;
	@FindBy(id="ctl00_ContentPlaceHolder1_imgBtnPatientSearch")
	public WebElement patientName_Searchbtn;
	@FindBy(id="ctl00_ContentPlaceHolder1_grdreceptionist_ctl02_imgbtnreg")
	public WebElement registrationBtn;
	
	 public Patient_registration_page (WebDriver driver4) 
	  {
		  this.driver4 = driver4; 
			PageFactory.initElements(driver4, this);
	  }
	public void searchMethod() {
		try {
		patientName_Searchbox.click();
		patientName_Searchbox.sendKeys("Ram");
		patientName_Searchbtn.click();
		Thread.sleep(3000);
		registrationBtn.click();
		Thread.sleep(2000);
		}
		 catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		
	}
	public void registerDetails() {
		
	try {
		 /* Code for fetching / reading data from Excel Application */
		FileInputStream fin = new FileInputStream("D:\\Course Project Data\\Patient Registration Master.xlsx");
		XSSFWorkbook wrkBk1 = new XSSFWorkbook(fin);		
		XSSFSheet sheet1=wrkBk1. getSheetAt (0);
		
		  /* Code for fetching first rows data */
		XSSFCell fn=sheet1. getRow (1). getCell (4);
		XSSFCell mn=sheet1. getRow (1). getCell (5);
		XSSFCell ln=sheet1. getRow (1). getCell (6);
		//XSSFCell age=sheet1. getRow (1). getCell (9);
		XSSFCell address=sheet1. getRow (1). getCell (12);
		XSSFCell email=sheet1. getRow (1). getCell (13);
		XSSFCell contact = sheet1. getRow (1). getCell (14);
		XSSFCell birthDate=sheet1. getRow (1). getCell (8);
		  WebElement fName=driver4. findElement(By.id("ctl00_ContentPlaceHolder1_txtFName"));
		  WebElement mName= driver4. findElement(By.id("ctl00_ContentPlaceHolder1_txtMName"));
		  WebElement lName=driver4.findElement(By.id("ctl00_ContentPlaceHolder1_txtLName"));
		  WebElement ageBox= driver4. findElement(By.id("ctl00_ContentPlaceHolder1_txtAge"));
		  WebElement emailBox= driver4.findElement(By.id("ctl00_ContentPlaceHolder1_txtEmail"));
		  WebElement addrBox=driver4.findElement(By.id("ctl00_ContentPlaceHolder1_txtAddress"));
		  WebElement mobContact=driver4.findElement(By.id("ctl00_ContentPlaceHolder1_txtMContact"));
		  fName. clear ();
		  fName.sendKeys(fn.toString());
		  mName. clear ();
		  mName.sendKeys(mn.toString());
		  lName.clear();
		  lName.sendKeys(ln.toString());
		  ageBox. clear ();
		  
		  
		  /* JavascriptExecutor is used to edit the date*/
		  JavascriptExecutor js = (JavascriptExecutor)driver4;
		  WebElement dobSelect=driver4.findElement(By.id("ctl00_ContentPlaceHolder1_txtDOB"));
		  
		  js.executeScript("document.getElementById('ctl00_ContentPlaceHolder1_txtDOB').removeAttribute('readonly')",dobSelect);
		  dobSelect.click();
		  dobSelect.sendKeys(birthDate.toString());	  
		  
		 // ageBox.sendKeys(age.toString());
		  emailBox.clear();
		  emailBox.sendKeys(email.toString());
		  addrBox.clear();
		  addrBox.sendKeys(address.toString());
		  DataFormatter dataFormatter = new DataFormatter();
	        String value = dataFormatter.formatCellValue(contact);
		  mobContact.clear();
		  mobContact.sendKeys(value);
		
		  wrkBk1.close();
		  fin.close();
		  
	} catch (FileNotFoundException fne) {
		
		fne.printStackTrace();
	}
	  
	 catch (IOException io) {
		
		io.printStackTrace();
	}
	
}

}
