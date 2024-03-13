import java.time.Duration;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddOneRandomItem {

	WebDriver driver = new ChromeDriver();
	String url = "https://magento.softwaretestingboard.com/";

	@BeforeTest
	public void mySetup() {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		driver.get(url);

	}

	@Test(invocationCount = 1, priority = 1, description = "First Test")
	public void AddOneRandomItemToThecart() throws InterruptedException {
		Random rand = new Random();
		int RandomIndex = rand.nextInt(4);
		driver.get(url);
		WebElement Container = driver.findElement(By.className("product-items"));
		List<WebElement> myList = Container.findElements(By.tagName("li"));
		myList.get(RandomIndex).click();
		Thread.sleep(2000);
		if (driver.getCurrentUrl().contains("fusion") || driver.getCurrentUrl().contains("push")) {
			WebElement addtoCartbutton = driver.findElement(By.id("product-addtocart-button"));
			addtoCartbutton.click();
		} else {
			WebElement sizeContainer = driver
					.findElement(By.cssSelector("div[class='swatch-attribute size'] div[role='listbox']"));
			List<WebElement> theListOfSizes = sizeContainer.findElements(By.tagName("div"));
			int RandomSize = rand.nextInt(theListOfSizes.size());
			theListOfSizes.get(RandomSize).click();
			WebElement colorsContainer = driver
					.findElement(By.cssSelector("div[class='swatch-attribute color'] div[role='listbox']"));
			// List<ُWebElement> theListOfColors =
			// colorsContainer.findElements(By.tagName("div"));

			List<WebElement> theListColors = colorsContainer.findElements(By.tagName("div"));
			int RandomColor = rand.nextInt(theListColors.size());
			theListColors.get(RandomColor).click();
			WebElement addtoCartbutton = driver.findElement(By.id("product-addtocart-button"));
			addtoCartbutton.click();
		}
	}

	@Test(priority = 2, description = "Second Test")
	public void CheckOutProcess() throws InterruptedException {
		String CheckOutPage = "https://magento.softwaretestingboard.com/checkout/cart/";
		Thread.sleep(2000);
		driver.get(CheckOutPage);
		WebElement proceedButton = driver.findElement(By.cssSelector("button[data-role='proceed-to-checkout']"));
		proceedButton.click();
	}

	@Test(priority = 3)

	public void SignupProcess() throws InterruptedException {
		Thread.sleep(1000);
		WebElement email = driver.findElement(By.id("customer-email"));
		WebElement firstName = driver.findElement(By.name("firstname"));
		WebElement lastName = driver.findElement(By.name("lastname"));
		WebElement StreetAddress = driver.findElement(By.name("street[0]"));
		WebElement city = driver.findElement(By.name("city"));
		WebElement state = driver.findElement(By.name("region_id"));
		WebElement postalCode = driver.findElement(By.name("postcode"));
		WebElement Country = driver.findElement(By.name("country_id"));
		WebElement phoneNumber = driver.findElement(By.name("telephone"));
		WebElement nextButton = driver.findElement(By.cssSelector(".button.action.continue.primary"));

		email.sendKeys("asma.alabsi2022@gmail.com");
		firstName.sendKeys("asma");
		lastName.sendKeys("alabsi");
		StreetAddress.sendKeys("amman Dabouq");
		city.sendKeys("amman");
		state.sendKeys("Dabouq");
		postalCode.sendKeys("19995");
		Country.sendKeys("Jordan");
		phoneNumber.sendKeys("962782931383");
		nextButton.click();

	Select select = new Select(Country);
		
//		select.selectByValue("CN");
//		select.selectByIndex(1);
		select.selectByVisibleText("Italy");
	}


 
	}


