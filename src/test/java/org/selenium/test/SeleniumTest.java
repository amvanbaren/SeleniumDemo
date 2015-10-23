//package org.selenium.test;
//
//import java.net.URL;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.concurrent.TimeUnit;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.Parameterized;
//import org.junit.runners.Parameterized.Parameter;
//import org.junit.runners.Parameterized.Parameters;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.remote.RemoteWebDriver;
//
//@RunWith(Parameterized.class)
//public class SeleniumTest {
//
//	@Parameters
//	public static Collection<String> browsers(){
//		return Arrays.asList(new String[]{"firefox", "chrome", "ie"});
//	}
//	
//	@Parameter
//	public String browser;
//	
//	public WebDriver driver;
//	protected ThreadLocal<RemoteWebDriver> threadDriver = null;
//
//	@Before
//	public void setUp() throws Exception {
//		String Url = "http://www.calculator.net";
//		String Node = null;
//		
//		DesiredCapabilities cap = null;
//
//		if (browser.equalsIgnoreCase("firefox")) {
//			System.out.println(" Executing on FireFox");
//
//			cap = DesiredCapabilities.firefox();
//			cap.setBrowserName("firefox");
//
//			Node = "http://10.112.66.52:5555/wd/hub";
//
//		} else if (browser.equalsIgnoreCase("chrome")) {
//			System.out.println(" Executing on CHROME");
//
//			cap = DesiredCapabilities.chrome();
//			cap.setBrowserName("chrome");
//			
//			Node = "http://10.112.66.52:5557/wd/hub";
//			
//		} else if (browser.equalsIgnoreCase("ie")) {
//			System.out.println(" Executing on IE");
//
//			cap = DesiredCapabilities.chrome();
//			cap.setBrowserName("ie");
//
//			Node = "http://10.112.66.52:5558/wd/hub";
//		} else {
//			throw new IllegalArgumentException("The Browser Type is Undefined");
//		}
//
//		if (cap != null) {
//			driver = new RemoteWebDriver(new URL(Node), cap);
//			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//
//			// Launch website
//			driver.navigate().to(Url);
//			driver.manage().window().maximize();
//		}
//	}
//
//	@After
//	public void tearDown() throws Exception {
//		driver.quit();
//	}
//
//	@Test
//	public void test() {
//	      // Click on Math Calculators
//	      driver.findElement(By.xpath(".//*[@id='menu']/div[3]/a")).click();     	
//	      // Click on Percent Calculators
//	      driver.findElement(By.xpath(".//*[@id='menu']/div[4]/div[3]/a")).click();
//	      // Enter value 10 in the first number of the percent Calculator
//	      driver.findElement(By.id("cpar1")).sendKeys("10");
//	      // Enter value 50 in the second number of the percent Calculator
//	      driver.findElement(By.id("cpar2")).sendKeys("50");
//	      
//	      // Click Calculate Button driver.findElement(By.xpath(".//*[@id='content']/table/tbody/tr/td[2]/input")).click();
//	      // Get the Result Text based on its xpath
//	      String result = driver.findElement(By.xpath(".//*[@id='content']/p[2]/span/font/b")).getText();
//	      // Print a Log In message to the screen
//	      System.out.println(" The Result is " + result);
//	      if(result.equals("5"))
//	      {
//	         System.out.println(" The Result is Pass");
//	      }
//	      else
//	      {
//	         System.out.println(" The Result is Fail");
//	      }
//	}
//
//}
