package week4.day2assignments;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;

public class ServicenowAssignIncident {

	public static void main(String[] args) throws InterruptedException, IOException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://dev62137.service-now.com/navpage.do");
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("Snow@2021");
		driver.findElement(By.id("sysverb_login")).click();
		driver.findElement(By.id("filter")).sendKeys("Incident");
		
		Actions builder = new Actions(driver);
		builder.sendKeys(Keys.ENTER).perform();
		
		driver.findElement(By.xpath("//div[text()='Open']")).click();
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("INC0010016");
		Actions builder2 = new Actions(driver);
		builder2.sendKeys(Keys.ENTER).perform();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@class='linked formlink']")).click();
		
		driver.findElement(By.id("lookup.incident.assignment_group")).click();
		
		Set<String> winhandles = driver.getWindowHandles();
		List<String> wh = new ArrayList<String>();
		wh.addAll(winhandles);
		driver.switchTo().window(wh.get(1));
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("software");
		Actions builder3 = new Actions(driver);
		builder3.sendKeys(Keys.ENTER).perform();
		Thread.sleep(2000);
		
		driver.findElement(By.className("glide_ref_item_link")).click();
		
		driver.switchTo().window(wh.get(0));
		driver.switchTo().frame("gsft_main");
		
		driver.findElement(By.id("activity-stream-textarea")).sendKeys("Work notes updated");
		
		driver.findElement(By.id("sysverb_update")).click();
		
		driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("INC0010016"); 
		Actions builder4 = new Actions(driver);
		builder4.sendKeys(Keys.ENTER).perform(); Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@class='linked formlink']")).click();
		
		driver.findElement(By.id("viewr.incident.assignment_group")).click();
		String group = driver.findElement(By.id("sys_user_group.name")).getAttribute("value");
		System.out.println("Assignment Group Name: " +group);
		

		driver.quit();

	}

}
