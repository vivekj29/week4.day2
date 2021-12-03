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

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;

public class ServicenowFrames {

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
		
		driver.findElement(By.xpath("(//div[text()='All'])[2]")).click();
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("sysverb_new")).click();
		driver.findElement(By.id("lookup.incident.caller_id")).click();
		
		Set<String> winhandles = driver.getWindowHandles();
		List<String> wh = new ArrayList<String>();
		wh.addAll(winhandles);
		driver.switchTo().window(wh.get(1));
		driver.findElement(By.className("glide_ref_item_link")).click();
		
		driver.switchTo().window(wh.get(0));
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("incident.short_description")).sendKeys("New Incident");
		
		String incidentnum = driver.findElement(By.id("incident.number")).getAttribute("value");
		System.out.println("The new incident number is " +incidentnum);
		
		driver.findElement(By.id("sysverb_insert")).click();
		
		driver.findElement(By.xpath("//span[contains(text(),'Press Enter from within')]/following-sibling::input")).sendKeys(incidentnum);
		
		Actions builder2 = new Actions(driver);
		builder2.sendKeys(Keys.ENTER).perform();
		
		String resultincident = driver.findElement(By.xpath("//a[@class='linked formlink']")).getText();
		System.out.println("The result incident number is " + resultincident);
		
		if (incidentnum.equals(resultincident)) {
			System.out.println("Incident is created successfully");
		}
		
		File source = driver.getScreenshotAs(OutputType.FILE);
		File dest = new File("snap/snap5.png");
		FileUtils.copyFile(source, dest);
		

	}

}
