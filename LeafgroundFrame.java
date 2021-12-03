package week4.day2assignments;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;

public class LeafgroundFrame {

	public static void main(String[] args) throws IOException {
		ChromeDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("http://leafground.com/pages/frame.html");
		driver.switchTo().frame(0);
		WebElement ele = driver.findElement(By.xpath("//button[text()='Click Me']"));
		File src = ele.getScreenshotAs(OutputType.FILE);
		File dest = new File("Snap/snap2.jpg");
		FileUtils.copyFile(src, dest);
		driver.switchTo().defaultContent();
		List<WebElement> count = driver.findElements(By.tagName("iframe"));
		System.out.println("Total no. of frames in page = " + count.size());
		

	}

}
