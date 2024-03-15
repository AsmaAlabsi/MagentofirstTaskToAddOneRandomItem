import java.time.Duration;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddOneRandomItem {

	WebDriver driver = new ChromeDriver();
	String url = "https://magento.softwaretestingboard.com/";
	String Password = "Asma5!@";

	@BeforeTest
	public void mySetup() {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		driver.get(url);

	}

	@Test(invocationCount = 1, priority = 1, description = "First Test")
	public void AddOneRandomItemToThecart() throws InterruptedException {
		Random rand = new Random();
		driver.get(url);
		WebElement Container = driver.findElement(By.className("product-items"));
		List<WebElement> myList = Container.findElements(By.tagName("li"));
		int RandomIndex = rand.nextInt(myList.size());

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
			Thread.sleep(4000);

		}
		WebElement Msg = driver
				.findElement(By.cssSelector("div[data-bind='html: $parent.prepareMessageForHtml(message.text)']"));
		boolean actual = Msg.getText().contains("You added");
		boolean expected = true;
		Assert.assertEquals(actual, expected);
	}

	@Test(priority = 2, description = "Second Test", enabled = false)
	public void CheckOutProcess() throws InterruptedException {
		String CheckOutPage = "https://magento.softwaretestingboard.com/checkout/cart/";
		Thread.sleep(2000);
		driver.get(CheckOutPage);
		WebElement proceedButton = driver.findElement(By.cssSelector("button[data-role='proceed-to-checkout']"));
		proceedButton.click();
	}

	@Test(priority = 3, enabled = false)

	public void SignupProcess() throws InterruptedException {
		String ExpectedMsg = "Thank you you for registering with Main Website Store.";
		Thread.sleep(4000);
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

		email.sendKeys("asma.alabsi2023@gmail.com");
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
		select.selectByVisibleText("Jordan");
		Thread.sleep(4000);
		WebElement NextButton = driver.findElement(By.cssSelector(".button.action.continue.primary"));
		NextButton.click();
		Thread.sleep(4000);
		WebElement PlaceOrder = driver.findElement(By.cssSelector(".action.primary.checkout"));
		PlaceOrder.click();
		Thread.sleep(5000);
		WebElement CreateAnAccountButton = driver.findElement(
				By.xpath("//a[@href='https://magento.softwaretestingboard.com/checkout/account/delegateCreate/']"));
		CreateAnAccountButton.click();
		Thread.sleep(5000);

		WebElement PasswordButton = driver.findElement(By.id("password"));
		WebElement ConfirmPassword = driver.findElement(By.id("password-confirmation"));
		PasswordButton.sendKeys(Password);
		ConfirmPassword.sendKeys(Password);

		WebElement CreatAnAccountfinal = driver.findElement(By.cssSelector(".action.submit.primary"));
		CreatAnAccountfinal.click();
		Thread.sleep(3000);
		WebElement SuccesfullMsg = driver
				.findElement(By.cssSelector("div[data-bind='html: $parent.prepareMessageForHtml(message.text)']"));
		String ActualMsg = SuccesfullMsg.getText();
		Assert.assertEquals(ActualMsg, ExpectedMsg);
	}

}
