package com.tests;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;




public class DemoWebShopTest {
	
	WebDriver driver;
	XSSFCell cell;
	@BeforeTest
	@Parameters("browser")
	public void setup(String browser) throws Exception{
		
		if(browser.equalsIgnoreCase("firefox")){
		
			System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		else if(browser.equalsIgnoreCase("chrome")){
			
			System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver.exe");
			
			driver = new ChromeDriver();
		}
		
				else if(browser.equalsIgnoreCase("IE")){
					
					System.setProperty("webdriver.ie.driver","src/test/resources/drivers/IEDriverServer.exe");
					
					driver = new InternetExplorerDriver();
				}
		else{
			
			throw new Exception("Browser is not correct");
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	@Test
	(dataProvider="reg")
		
		   public  void TestDatafromExcel() throws IOException
		 {
		
driver.get("http://demowebshop.tricentis.com");
		
		driver.findElement(By.linkText("Register")).click();
			    File file =new File("src/test/resources/drivers/UserRegistrationData.xlsx");

try {
	
			  
			    InputStream inputStream = new FileInputStream(file);

			    XSSFWorkbook workbook =new XSSFWorkbook(inputStream);
			    XSSFSheet sheet=workbook.getSheet("RegistrationDetails");
			  		    
			   //	obj=new Object[sheet.getLastRowNum()][];
			   	for(int i=1; i<=sheet.getLastRowNum(); i++) { 

			   
			   	cell = sheet.getRow(i).getCell(1); 

			   	if(cell.equals("Male"))
			   	{
			   
			   		driver.findElement(By.id("gender-male")).click();
			   	
			   		}
			   		else
			   		{
			   			driver.findElement(By.id("gender-female")).click();
			   	}
			   	
   		cell = sheet.getRow(i).getCell(i); 
		   			   	driver.findElement(By.id("FirstName")).sendKeys(cell.getStringCellValue());
			 	cell = sheet.getRow(i).getCell(3); 
				driver.findElement(By.id("LastName")).sendKeys(cell.getStringCellValue()); 
			 	cell = sheet.getRow(i).getCell(4); 
				driver.findElement(By.id("Email")).sendKeys(cell.getStringCellValue()); 
			 	cell = sheet.getRow(i).getCell(5); 
				driver.findElement(By.id("Password")).sendKeys(cell.getStringCellValue()); 
			 	cell = sheet.getRow(i).getCell(6); 
				driver.findElement(By.id("ConfirmPassword")).sendKeys(cell.getStringCellValue()); 
				
			   /*	for(int i=0;i<=sheet.getLastRowNum();i++)
			   	{
			   		obj[i-1]=new Object[sheet.getRow(i).getPhysicalNumberOfCells()];
			   		for(int j=0;j<sheet.getRow(i).getPhysicalNumberOfCells();j++)
			   		{
			   			System.out.println(sheet.getRow(i).getCell(j).getStringCellValue());
			   			
			   		}
			   		System.out.println();
			   	}*/
				driver.findElement(By.id("register-button")).click();
			   	}
			   	
		
inputStream.close();
FileOutputStream outputStream = new FileOutputStream(file);
File file1 =new File("src/test/resources/drivers/UserLoginDetails.xlsx");


workbook.write(outputStream);

workbook.close();

outputStream.close();
			   	}


catch(FileNotFoundException e)
{
	e.printStackTrace();
}


}
	     
  	   
	
	@Test
	public void Login() throws IOException {
		driver.findElement(By.linkText("Log in")).click(); 
		
	    File file =new File("src/test/resources/drivers/UserLoginDetails.xlsx");

try {

	  
	    InputStream inputStream = new FileInputStream(file);

	    XSSFWorkbook workbook =new XSSFWorkbook(inputStream);
	    XSSFSheet sheet=workbook.getSheet("Sheet1");
	    for(int i=1; i<=sheet.getLastRowNum(); i++) { 
	    	cell = sheet.getRow(i).getCell(i); 
	    	driver.findElement(By.name("Email")).sendKeys(cell.getStringCellValue());
	    	cell = sheet.getRow(i).getCell(i); 
	    	driver.findElement(By.name("password")).sendKeys(cell.getStringCellValue());
	    	driver.findElement(By.className("button-1 login-button")).submit();
	    	
	    }
	
		
}
catch(FileNotFoundException e)
{
	e.printStackTrace();
}
	}	
	
}


