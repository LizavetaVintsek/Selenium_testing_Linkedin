package com.linkedin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTests {

	@Test
	public void loginTest() {
		System.out.println("Starting loginTest");

// 		Create driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

//		sleep for 1 sec (just to slow down and visualize the selenium work)
		sleep();

//		maximize browser window
		driver.manage().window().maximize();

//		sleep for 1 sec (just to slow down and visualize the selenium work)
		sleep();

//		open test page
		String url = "https://www.linkedin.com/checkpoint/rm/sign-in-another-account?fromSignIn=true&trk=guest_homepage-basic_nav-header-signin";
		driver.get(url);
		System.out.println("Page is opened");

//		sleep for 1 sec (just to slow down and visualize the selenium work)
		sleep();

//		enter user name
		WebElement username = driver.findElement(By.xpath("//input[@name='session_key']"));
		username.sendKeys("lizaveta.vintsek@gmail.com");
		sleep();
		System.out.println("Username is entered");
		
//		enter password
		WebElement password = driver.findElement(By.xpath("//input[@name='session_password']"));
		password.sendKeys("1025054nature");
		sleep();
		System.out.println("Password is entered");
		
//		click login button
		WebElement logInButton = driver.findElement(By.xpath("//button[@type='submit']"));

		logInButton.click();
		sleep();

//		verification:
//			new url
		String expectedUrl = "https://www.linkedin.com/feed/?trk=guest_homepage-basic_nav-header-signin";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual page url is not the same as expected");
		sleep();
		System.out.println("Expected url is checked");
		
//			'Go to the home page' button is visible
		WebElement goToTheHomePage = driver.findElement(By.xpath("//div[@id='voyager-feed']"));
		Assert.assertTrue(goToTheHomePage.isDisplayed(), "Home page button is not visible");
		System.out.println("Home page button is vissible");
		
//			Jobs button is visible and the text is equal to 'Jobs'
		WebElement jobsButton = driver.findElement(By.xpath("//span[@title='Jobs']"));
		String expectedJobsTitle = "Jobs";
		String actualJobsTitle = jobsButton.getText();
		Assert.assertTrue(actualJobsTitle.contains(expectedJobsTitle),
				"Actual title does not contain expected title.\nActual Title: " + actualJobsTitle + "\nExpected Title: "
						+ expectedJobsTitle);
		System.out.println("Jobs button is vissible");
		
//		close browser
		driver.quit();
	}

	private void sleep() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
