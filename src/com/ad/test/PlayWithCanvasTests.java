package com.ad.test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.io.IOException;
import java.lang.*;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PlayWithCanvasTests {

	WebDriver driver;
	public static String USERNAME;
	public static String ACCESS_KEY;
	public static String URL;

	// WebDriver driver = new InternetExplorerDriver();

	@BeforeClass
	public void f() throws InterruptedException, MalformedURLException {
		driver = new FirefoxDriver();
		// driver = new InternetExplorerDriver();

		// DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		// caps.setCapability("requireWindowFocus", true);
		// caps.setCapability("enablePersistentHover ", true);
		// caps.setCapability("nativeEvents", false);
		// caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
		// true);
		// driver = new InternetExplorerDriver(caps);
		//
		// driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// // driver.get("http://canvasjs.com/");
		// driver.get("https://quickstart.quickmobile-qa4.com/login");

		// USERNAME = "shradaem";
		// ACCESS_KEY = "a3625cd0-b736-4587-8213-3826b35d2c7e";
		// String URL = "http://" + USERNAME + ":" + ACCESS_KEY
		// + "@ondemand.saucelabs.com:80/wd/hub";
		//
		// DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		// caps.setCapability("platform", "Windows 7");
		// caps.setCapability("version", "10.0");
		// caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		// driver = new RemoteWebDriver(new URL(URL), caps);
		// driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// // driver.get("http://canvasjs.com/");

		driver.get("https://quickstart.quickmobile-qa4.com/login");

	}

	// @Test
	public void playWithCanvasGraph() throws InterruptedException {

		WebElement el = driver.findElement(By.cssSelector("#username"));

		el.sendKeys("TEST USER");

		Thread.sleep(5000);

		driver.get("http://canvasjs.com/");
		Thread.sleep(8000);

		WebElement iframe = driver.findElement(By.id("preview1"));
		driver.switchTo().frame(iframe);

		Actions action = new Actions(driver);
		WebElement canvas = driver.findElement(By.xpath(".//canvas[1]"));

		Point point = canvas.getLocation();
		System.out.println(" X - cordinate : " + point.getX());
		System.out.println(" Y - cordinate : " + point.getY());
		System.out.println(" Y - cordinate : " + point.getY());
		System.out.println(" Y - cordinate : " + point.getY());
		System.out.println(" Y - cordinate : " + point.getY());
		
		Dimension point1 = canvas.getSize();
		System.out.println("Width of the canvas : " + point1.width);
		action.moveToElement(canvas).build().perform();
		Thread.sleep(2000);

		for (int i = 20; i <= 350; i++) {

			System.out.println(i);
			action.moveToElement(canvas, i, 150).build().perform();
			// action.moveByOffset(i, 50).build().perform();
			Thread.sleep(1000);
			i = i + 20;
			// i=i+40;
		}

		String s = driver.findElement(By.xpath("//canvas[1]//..//div//div"))
				.getAttribute("innerHTML").toString();
		System.out.println(s);
		String s1 = driver.findElement(By.xpath("//canvas[1]//..//div//div"))
				.getText().toString();
		System.out.println(s1);
		String s2 = driver.findElement(
				By.xpath("//canvas[1]//..//div//div//span")).getText();
		System.out.println(s2);
		Thread.sleep(2000);

	}

	@Test
	public void LoginTest() throws InterruptedException {

		WebElement loginButton = dynamicElementUsingGenericXpath("button",
				"class", "form-submit");

		loginButton.click();

		Thread.sleep(3000);

	}

	/**
	 * Method to locate a dynamic web element.
	 * 
	 * @author adarshem
	 * @since 30-June-2016
	 * 
	 * @param elementTag
	 *            common tags are
	 *            button,input,a,li,ul,span,label,textarea,div,iframe
	 *            ,table,td,tr
	 * @param elementAtrribute
	 *            common attributes are
	 *            class,data-name,for,href,id,name,src,style,type,value,text
	 * @param containsValue
	 *            Value of the attribute using. E.g: if id=test_123, then pass
	 *            'id' as attribute and 'test' as contains value since id is
	 *            dynamic.
	 * @return
	 */
	public WebElement dynamicElementUsingGenericXpath(String elementTag,
			String elementAtrribute, String containsValue) {
		String genericXpath = null;
		if (!elementAtrribute.equals("text")) {
			genericXpath = ".//" + elementTag + "[contains(@"
					+ elementAtrribute + ",'" + containsValue + "')]";
		} else {

			genericXpath = ".//" + elementTag + "[contains(.,'" + containsValue
					+ "')]";

		}

		return driver.findElement(By.xpath(genericXpath));

	}
}
