import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddOneRandomItem {

	WebDriver driver = new ChromeDriver();
	String url = "https://magento.softwaretestingboard.com/";

	@BeforeTest
	public void mySetup() {
		driver.manage().window().maximize();
		driver.get(url);

	}

	@Test(invocationCount = 1)
	public void AddOneRandomItemToThecart() throws InterruptedException {
		Random rand = new Random();
		int RandomIndex = rand.nextInt(6);
		driver.get(url);
		WebElement Container = driver.findElement(By.className("product-items"));
		List<WebElement> myList = Container.findElements(By.tagName("li"));
		myList.get(RandomIndex).click();
		Thread.sleep(2000);
		if (driver.getCurrentUrl().contains("fusion")|| driver.getCurrentUrl().contains("push")) {
			WebElement addtoCartbutton = driver.findElement(By.id("product-addtocart-button"));
			addtoCartbutton.click();}
		else {
			WebElement sizeContainer = driver.findElement(By.cssSelector("div[class='swatch-attribute size'] div[role='listbox']"));
			List<WebElement> theListOfSizes  = sizeContainer.findElements(By.tagName("div"));
			int RandomSize = rand.nextInt(theListOfSizes.size());
			theListOfSizes.get(RandomSize).click();
			WebElement colorsContainer = driver.findElement(By.cssSelector("div[class='swatch-attribute color'] div[role='listbox']"));
			//List<ُWebElement> theListOfColors = colorsContainer.findElements(By.tagName("div"));
			
			List<WebElement> theListColors = colorsContainer.findElements(By.tagName("div"));
			int RandomColor = rand.nextInt( theListColors.size());
			theListColors.get(RandomColor).click();
			WebElement addtoCartbutton = driver.findElement(By.id("product-addtocart-button"));
			addtoCartbutton.click();}
			
			
		}
}
