package cacsE2E;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CACSLogin {
  @Test (priority = 1)
  
  public void Intralink_Login_page() {
	  
	  
	  DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
      cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		
      //Open Intralink
      System.setProperty("webdriver.ie.driver","C:\\Users\\S4622781\\Desktop\\Per\\AA_NEW Learning\\Kal_Sel\\Software\\Internet Exp\\IEDriverServer.exe");
      
      WebDriver cacslogin = new InternetExplorerDriver(cap);
      cacslogin.manage().window().maximize();
      
      cacslogin.get("http://uat16.uat.intralink.bns/");
      cacslogin.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
      
    //Validate Intralink title
      
		String title = cacslogin.getTitle();
		
		System.out.println("Intralink HomePage Title "+title);
		
	// assert from org.testNG
		try {
				Assert.assertEquals(title, "BNS Employee Login");  
			 }
		
		catch(Exception e)
			{
				System.out.println(e);
			
			}
		
		//WebElement userid = cacslogin.findElement(By.id("IDToken1"));
		
		//userid.sendKeys("3141470");
		
	JavascriptExecutor js =((JavascriptExecutor)cacslogin);
	
	// User "Supervisor" Login
				
	js.executeScript("document.getElementById('idToken1').value='3141470'");
			
				
	//Password 
				
	js.executeScript("document.getElementById('idToken2').value='Password12'");
		        
		
		//Click Login button
				
	 WebElement loginbutton = cacslogin.findElement(By.xpath("html/body/div[2]/div/div[2]/div/form/fieldset/div[4]/input"));
				
	//	WebElement loginbutton = cacslogin.findElement(By.xpath(".//*[@class='RUIFW-btn-primary RUIFW-btn-block btn btn-primary btn-block']"));
		loginbutton.click();
		cacslogin.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		        
		//     driver.findElement(By.xpath("html/body/div[2]/div/div[2]/div/form/fieldset/div[4]/input")).click();
		     
				
		//Find the Transit
				
				
		List<WebElement> Transitlink = cacslogin.findElements(By.tagName("a"));
				
		//Need to Find the Dynamic Xpath for this
				
		// WebElement TransitName = cacslogin.findElement(By.xpath("html/body/form/center/table[2]/tbody/tr[2]/td[2]"));
				
		WebElement TransitName = cacslogin.findElement(By.xpath(".//*[@id='Table1']/tbody[1]/tr[2]/td[2]"));
				
				for(WebElement link:Transitlink)
				{
					try{			
					if(link.getText().equalsIgnoreCase("07112"))
					{
						System.out.println("Sucessfull Login into Collections Tranist : " +link.getText()+"/"+TransitName.getText());
						File ScreenImage1 = ((TakesScreenshot)cacslogin).getScreenshotAs(OutputType.FILE);
						FileUtils.copyFile(ScreenImage1, new File("C:/Users/S4622781/Desktop/Per/AA_NEW Learning/Kal_Sel/ScreenPrint/TransitLogin_Successful.png"));
					}
					else
						
					{
						System.out.println("Login not Sucessfull " +link.getText());
						File ScreenImage2 = ((TakesScreenshot)cacslogin).getScreenshotAs(OutputType.FILE);
						FileUtils.copyFile(ScreenImage2, new File("C:/Users/S4622781/Desktop/Per/AA_NEW Learning/Kal_Sel/ScreenPrint/TransitLogin_Failed.png"));
					}
					}
					catch(Exception e)
					{
						System.out.println(e);
					}
				}
	 
	  
 }
  
}
