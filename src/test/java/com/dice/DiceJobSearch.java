/*
 * 1)Create arraylist of keywords.
		add 20 different keywords
		list.add("java");

		pass each item to search box and print accordingly.
		modify your arraylist 

		java-1234
 */
package com.dice;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobSearch {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().fullscreen();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		List<String> lst = new ArrayList<>();
		lst.add("Geological Engineer");
		lst.add("Automation Specialist I");
		lst.add("Structural Analysis Engineer");
		lst.add("Director of Sales");
		lst.add("Help Desk Technician");
		lst.add("Desktop Support Technician");
		lst.add("Assistant Professor");
		lst.add("GIS Technical Architect");
		lst.add("Engineer IV");
		lst.add("Design Engineer");
		lst.add("Librarian");
		lst.add("Recruiter");
		lst.add("Executive Secretary");
		lst.add("Operator");
		lst.add("Senior Cost Accountant");
		lst.add("Environmental Tech");
		lst.add("Database Administrator IV");
		lst.add("Geological Engineer");
		lst.add("VP Sales");
		lst.add("Human Resources Manager");

		for (int i = 0; i < lst.size(); i++) {
			driver.get("http://dice.com");
			String actualTitle = driver.getTitle();
			String expectedTitle = "Job Search for Technology Professionals | Dice.com";
			if (actualTitle.equals(expectedTitle)) {
				System.out.println("Step PASS. Dice homepage successfully loaded");
			} else {
				System.out.println("Step FAIL. Dice homepage did not load successfully");
				throw new RuntimeException("Step FAIL. Dice homepage did not load successfully");
			}
			driver.findElement(By.id("search-field-keyword")).clear();
			driver.findElement(By.id("search-field-keyword")).sendKeys(lst.get(i));
			String location = "11230";
			driver.findElement(By.id("search-field-location")).clear();
			driver.findElement(By.id("search-field-location")).sendKeys(location);
			driver.findElement(By.id("findTechJobs")).click();
			String count = driver.findElement(By.id("posiCountId")).getText();
			int countResult = Integer.parseInt(count.replace(",", ""));
			if (countResult > 0) {
				System.out.println("Step PASS: Keyword : " + 
						lst.get(i) + 
						" search returned \"" + 
						countResult + 
						"\" results in \"" + 
						location + "\"");
			} else {
				System.out.println("Step FAIL: Keyword : " + 
						lst.get(i) + 
						" search returned \"" + 
						countResult + 
						"\" result in \"" + 
						location + "\"");
			}
			lst.set(i, lst.get(i) + "-" + count);
		}
		driver.close();
		System.out.println("TEST COMPLETED : " + LocalDateTime.now());
		System.out.println("Modified ArrayList:");
		System.out.println(lst);
	}
}