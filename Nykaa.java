package week4.day2assignments;

import static org.testng.Assert.assertEquals;

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

public class Nykaa {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.nykaa.com/");
		WebElement brands = driver.findElement(By.xpath("//a[text()='brands']"));
		
		Actions builder = new Actions(driver);
		builder.moveToElement(brands).perform();
		
		driver.findElement(By.id("brandSearchBox")).sendKeys("L'Oreal Paris");
		Thread.sleep(2000);
		driver.findElement(By.linkText("L'Oreal Paris")).click();
		String title = driver.getTitle();
		if(title.contains("L'Oreal Paris")){
			System.out.println("The title contains L'Oreal Paris");
		}
		else System.out.println("Title mismatch");
		Thread.sleep(2000);
		
		driver.findElement(By.className("sort-name")).click();
		driver.findElement(By.xpath("//span[text()='customer top rated']")).click();
		
		driver.findElement(By.xpath("//span[text()='Category']")).click();
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		driver.findElement(By.xpath("//span[text()='Shampoo']")).click();
		
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		driver.findElement(By.xpath("//span[text()='Color Protection']")).click();
		
		String filter1 = driver.findElement(By.xpath("(//span[@class='filter-value'])[1]")).getText();
		System.out.println(filter1);
		String filter2 = driver.findElement(By.xpath("(//span[@class='filter-value'])[2]")).getText();
		System.out.println(filter2);
		if(filter1.contains("Shampoo") && filter2.contains("Color Protection")) {
			System.out.println("the color Protection filter is applied with Shampoo");
		}
		else System.out.println("the color Protection filter is NOT applied with Shampoo");
		
		driver.findElement(By.xpath("(//div[contains(text(),'Paris Colour Protect Shampoo')])[1]")).click();
		
		Set<String> windowhandles = driver.getWindowHandles();
		List<String> winhandles = new ArrayList<String> (windowhandles);
		driver.switchTo().window(winhandles.get(1));
		System.out.println(driver.getTitle());
		
		WebElement size = driver.findElement(By.xpath("//select[@title='SIZE']"));
		Select ml = new Select(size);
		ml.selectByVisibleText("175ml");
		
		String mrp = driver.findElement(By.xpath("//span[@class='css-1888qy']/following-sibling::span[1]")).getText();
		mrp = mrp.replaceAll("[^0-9]", "");
		System.out.println("The MRP of shampoo is " +mrp);
		driver.findElement(By.xpath("//span[text()='ADD TO BAG']")).click();
		driver.findElement(By.xpath("//span[@class='cart-count']")).click();
		Thread.sleep(3000);
		
		driver.switchTo().frame(0);
		String total = driver.findElement(By.xpath("//span[text()='Grand Total']/following-sibling::div")).getText();
		total = total.replaceAll("[^0-9]", "");
		System.out.println("The total from shopping bag is " +total);
		
		driver.findElement(By.xpath("//span[text()='PROCEED']")).click();
		driver.findElement(By.xpath("//button[text()='CONTINUE AS GUEST']")).click();
		
		String finaltotal = driver.findElement(By.xpath("(//div[@class='value']//span)[2]")).getText();
		System.out.println("The final total is " + finaltotal);
		assertEquals(total, finaltotal);
		driver.quit();
		
}
			
}
