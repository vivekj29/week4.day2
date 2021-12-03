package week4.day2assignments;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;

public class Snapdeal {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://www.snapdeal.com/");
		WebElement fashion = driver.findElement(By.xpath("//div[@id='leftNavMenuRevamp']/div[1]/div[2]/ul[1]/li[7]/a[1]/span[1]"));
		
		Actions builder = new Actions(driver);
		builder.moveToElement(fashion).perform();
		
		driver.findElement(By.xpath("//span[text()='Sports Shoes']")).click();
		String count = driver.findElement(By.xpath("//span[@class='category-count']")).getText();
		count = count.replaceAll("[^0-9]","");
		System.out.println("Count of sports shoes = " +count);
		
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		driver.findElement(By.className("sort-selected")).click();
		driver.findElement(By.xpath("(//li[@class='search-li'])")).click();
		
		WebElement fromval = driver.findElement(By.name("fromVal"));
		fromval.clear();
		fromval.sendKeys("900");
		WebElement toval = driver.findElement(By.name("toVal"));
		toval.clear();
		toval.sendKeys("1200");
		driver.findElement(By.xpath("//div[contains(text(),'GO')]")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//label[@for='Color_s-Black']")).click(); //Selected black as Navy is not available
		
		String price = driver.findElement(By.xpath("//a[@data-key='Price|Price']")).getText();
		System.out.println(price);
		String color = driver.findElement(By.xpath("//a[@data-key='Color_s|Color']")).getText();
		System.out.println(color);
		
		if(price.contains("Rs. 900 - Rs. 1200") && color.contains("Black")) {
			System.out.println("All the applied filter matches");
		}
		else System.out.println("There's a mismatch");
		
		WebElement img = driver.findElement(By.xpath("//img[@class='product-image wooble']"));
		Actions builder2 = new Actions(driver);
		builder2.moveToElement(img).perform();
		
		driver.findElement(By.xpath("//div[contains(text(),'Quick View')]")).click();
		String cost = driver.findElement(By.className("payBlkBig")).getText();
		System.out.println("Cost is " +cost);
		String disc = driver.findElement(By.className("percent-desc")).getText();
		disc = disc.replaceAll("[^0-9]","");
		System.out.println("Discount % is " +disc);
		
		WebElement shoes = driver.findElement(By.xpath("//img[@itemprop='image']"));
		
		File source = shoes.getScreenshotAs(OutputType.FILE);
		File dest = new File("snap/snap4.jpg");
		FileUtils.copyFile(source, dest);
		driver.close();
		driver.quit();
 		
 		
	}

}
