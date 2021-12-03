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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;

public class OneplusAmazon {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://www.amazon.in/");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro");
		driver.findElement(By.id("nav-search-submit-button")).click();
		String price = driver.findElement(By.xpath("(//span[@class='a-price-whole'])[2]")).getText();
		System.out.println("The price of first oneplus 9 pro is: " +price);
		String ratings = driver.findElement(By.xpath("(//span[@class='a-size-base'])[2]")).getText();
		System.out.println("The total rating of first oneplus 9 pro is: " +ratings);
		driver.findElement(By.xpath("(//i[@class='a-icon a-icon-popover'])[2]")).click();
		String fivestar = driver.findElement(By.xpath("(//a[contains(@title,'5 stars')])[3]")).getText();
		System.out.println("5 Star rating percentage is: " +fivestar);
		driver.findElement(By.xpath("(//span[@class='a-size-medium a-color-base a-text-normal'])[2]")).click();
		Set<String> windowhandles = driver.getWindowHandles();
		List<String> winhandles = new ArrayList<String> (windowhandles);
		driver.switchTo().window(winhandles.get(1));
		System.out.println("The title is: " + driver.getTitle());
		File source = driver.getScreenshotAs(OutputType.FILE);
		File dest = new File("snap/snap3.jpg");
		FileUtils.copyFile(source, dest);
		driver.findElement(By.id("add-to-cart-button")).click();
		driver.findElement(By.xpath("//span[@id='attach-sidesheet-view-cart-button']")).click();
		String cartTotal = driver.findElement(By.xpath("//span[@id='sc-subtotal-amount-activecart']/span")).getText();
		cartTotal = cartTotal.replaceAll("\\.\\d+$", "");
		cartTotal = cartTotal.replaceAll("^[ \\t]+", "");
		System.out.println("the cart total is: " +cartTotal);
		
		if(price.equals(cartTotal)) {
			System.out.println("the cart price matches phone price");
		}
		else System.out.println("the cart price and phone price does not match");
		
		
		
		
 		
 		
	}

}
