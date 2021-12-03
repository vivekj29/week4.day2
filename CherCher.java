package week4.day2assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;

public class CherCher {

	public static void main(String[] args) throws InterruptedException {
		
		ChromeDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://chercher.tech/practice/frames-example-selenium-webdriver");
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//b[text()='Topic :']/following-sibling::input")).sendKeys("Selenium");
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		driver.switchTo().defaultContent();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(1);
		WebElement dd = driver.findElement(By.id("animals"));
		Select animals = new Select(dd);
		animals.selectByValue("avatar");
		driver.switchTo().defaultContent();
		System.out.println(driver.getTitle());
		driver.close();
		
		
 		
 		
	}

}
