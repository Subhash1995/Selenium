package com.crm.qa.base;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Test;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;
public class TestBase {
	public static WebDriver driver;
	public static Properties prop;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	@Test
	public TestBase(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/crm"
					+ "/qa/config/config.propertie");
			prop.load(ip);
			System.out.println(System.getProperty("user.dir"));
			System.out.println("subhash TestBase");
		} catch (FileNotFoundException e) {
			e.getMessage();
			//System.out.println(e);
		} catch (IOException e) {
			//e.printStackTrace();
		}
	}
	public static void initialization(){
		//TestBase tes =new TestBase();s
		//prop = new Properties();
		String browserName = prop.getProperty("browser");
		System.out.println(browserName);
		if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", "C:/Users/Raju/Desktop/subhash/chromedriver1.exe");	
			driver = new ChromeDriver(); 
		}
		else if(browserName.equals("FF")){
			System.setProperty("webdriver.gecko.driver", "/Users/naveenkhunteta/Documents/SeleniumServer/geckodriver");	
			driver = new FirefoxDriver(); 
		}
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
	}
	public static void main(String[] args) {
		List<Integer> sub = new ArrayList<Integer>();
		sub.add(0);
		sub.add(1);
		sub.add(2);
		sub.add(3);
		sub.add(0);
		sub.add(0);
		System.out.println("subhash");
		Set<Integer> has =new HashSet<Integer>();
		// Iterator<Integer> has1 = has.iterator();
		//has.addAll(sub);
		for(Integer s: sub) {
			System.out.println("subhash"+s);
			if(has.add(s)==false)
			System.out.println("s is duplicate"+s);
		}
		System.out.println(has);
}
}
	//Iterator<String> it = ar.iterator();
	
	

