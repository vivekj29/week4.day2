package week4.day2assignments;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Resizeable {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get("https://jqueryui.com/resizable");
		driver.switchTo().frame(0);
		WebElement resize = driver.findElement(By.xpath("//div[contains(@class,'ui-resizable-handle ui-resizable-se')]"));
		Actions builder = new Actions(driver);
		builder.dragAndDropBy(resize, 30, 30).perform();
		//driver.close();

	}

}
