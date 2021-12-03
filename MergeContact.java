package week4.day2assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;

public class MergeContact {

	public static void main(String[] args) throws InterruptedException {
		
		ChromeDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("http://leaftaps.com/opentaps/control/login");
		driver.findElement(By.id("username")).sendKeys("DemoSalesManager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();
		driver.findElement(By.linkText("CRM/SFA")).click();
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//a[text()='Merge Contacts']")).click();
		String currentWin = driver.getWindowHandle();
		driver.findElement(By.xpath("(//img[@src='/images/fieldlookup.gif'])[1]")).click();
		Set<String> allHandle1 = driver.getWindowHandles();
		System.out.println(allHandle1);
		List<String> newWindow1 = new ArrayList<String>(allHandle1);
		driver.switchTo().window(newWindow1.get(1));
		System.out.println(driver.getTitle());
 		driver.findElement(By.xpath("//table[@class='x-grid3-row-table']/tbody[1]/tr[1]/td[1]/div[1]/a[1]")).click();
 		driver.switchTo().window(currentWin);
 		driver.findElement(By.xpath("(//img[@src='/images/fieldlookup.gif'])[2]")).click();
 		Set<String> allHandle2 = driver.getWindowHandles();
 		System.out.println(allHandle2);
 		List<String> newWindow2 = new ArrayList<String>(allHandle2);
 		driver.switchTo().window(newWindow2.get(1));
 		System.out.println(driver.getTitle());
 		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']//a)[2]")).click();
 		driver.switchTo().window(currentWin);
 		driver.findElement(By.xpath("//a[text()='Merge']")).click();
 		Alert alert = driver.switchTo().alert();
 		String text = alert.getText();
 		System.out.println("the alert text is " + text);
 		alert.accept();
 		System.out.println("the title is " + driver.getTitle());
 		driver.quit();
 		
 		
	}

}
