/*
 * 2) Store all keywords into a text file 
		read the text file and  repeat above steps.

		store keyword and results count into an arraylist.
		----

		after closing browser.
		print contents of arraylist that was updated each time 
		we looped.

		commit > push > share your github link
 */
package com.dice;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobSearch2 {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().fullscreen();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		List<String> lst = new ArrayList<>();
		try (FileReader fr = new FileReader("jobs.txt");
				BufferedReader br = new BufferedReader(fr);) 
			{
				String value = "";
				while((value=br.readLine()) != null) {
					lst.add(value);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		
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
