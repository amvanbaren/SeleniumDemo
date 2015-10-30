package org.selenium.test;

import static org.junit.Assert.assertEquals;

import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

@RunWith(Parameterized.class)
public class SeleniumTest {

	@Parameters
	public static Collection<String> browsers(){
		return Arrays.asList(new String[]{"firefox", "chrome", "ie"});
	}
	
	@Parameter
	public String browser;
	
	public WebDriver driver;
	protected ThreadLocal<RemoteWebDriver> threadDriver = null;

	@Before
	public void setUp() throws Exception {		
		DesiredCapabilities cap = null;

		switch(browser){
			case "firefox":
				cap = DesiredCapabilities.firefox();
				break;
			case "chrome":
				cap = DesiredCapabilities.chrome();
				break;
			case "ie":
				cap = DesiredCapabilities.internetExplorer();
				break;
			default:
				throw new IllegalArgumentException("The Browser Type is Undefined");
		}

		if (cap != null) {
			System.out.printf("Executing on %s\n", browser);
			cap.setBrowserName(browser);
			
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			// Launch website
			final String Url = "https://docs.google.com/forms/d/18nq9YuC0E8p2JOONkqZ5IAMIdP1eytiEDV8hJn_spHk/viewform";
			driver.navigate().to(Url);
			driver.manage().window().maximize();
		}
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void test() throws InterruptedException {
		// check if page has loaded correctly
		assertEquals("What is your name?", driver.findElement(By.cssSelector("div.ss-q-title")).getText());
		assertEquals("Where is the class located?", driver.findElement(By.xpath("//form[@id='ss-form']/ol/div[2]/div/div/label/div")).getText());
		assertEquals("What type of class is it?", driver.findElement(By.xpath("//form[@id='ss-form']/ol/div[3]/div/div/label/div")).getText());
	
		// insert name
		final String name = String.format("Aart %s", browser);
		driver.findElement(By.id("entry_785445797")).clear();
		driver.findElement(By.id("entry_785445797")).sendKeys(name);
		
		// select Staten Island and Brooklyn
		driver.findElement(By.id("group_396363777_4")).click();
		driver.findElement(By.id("group_396363777_2")).click();
		
		// select Hot Yoga
		driver.findElement(By.id("group_277070397_3")).click();
		
		// submit form			
		driver.findElement(By.id("ss-submit")).click();
		
		// check if form is posted
		assertEquals("Your response has been recorded.", driver.findElement(By.cssSelector("div.ss-resp-message")).getText());
	}
}
