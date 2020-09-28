package Bookpillow;

import java.io.IOException;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BookpillowSuccess {

	WebDriver driver;
	String excelPath="../excelfiles/cardDetails_success.xlsx";	
	String applicationUrl="https://demo.midtrans.com/";
	String pillowValue="20000";
	String name="Mohit Sarsar";
	String email="smohit111@gmail.com";
	String phone="9403195422";
	String city="Pune";
	String address="Baner,Pune";
	String postalCode="411021";
	
	@DataProvider(name="MyDataProvider")
	public Object[][] getDataBy() throws IllegalFormatException, IOException{
			
	ReadExcelSheet excelObj = new ReadExcelSheet(excelPath);
			
	int rowCount = excelObj.getRowCount(0);
	int colCount =excelObj.sheet.getRow(0).getPhysicalNumberOfCells();
	
	Object[][] data = new Object[rowCount+1][colCount];
		
		 for(int i=0;i<=rowCount;i++ ){ 
			 for(int j=0;j<colCount;j++){ 
				 double type =excelObj.getCellType(0,i,j);
				 
				 	if(j==1) {
					 	  data[i][j]=excelObj.getCellDate(0,i,j); 
				 	}
				 	if(type==1) {
				 	  data[i][j]=excelObj.getCellData(0,i,j); 
				    }else if(j!=1 && type==0){
				 	  data[i][j]=excelObj.getCellIntData(0,i,j); 
		            } 
	             } 
           }
			return data;
 }
	
	@Test(dataProvider="MyDataProvider")
	public void BookpillowSucessCase(String cardNumber,String expiry,int CVV, int OTP,String browser) throws InterruptedException{
		
		if(browser.equals("Chrome")){
				System.setProperty("webdriver.chrome.driver", "../driver/chromedriver.exe");
				driver = new ChromeDriver();
			}
		if(browser.equals("Firefox")){
			System.setProperty("webdriver.gecko.driver", "../driver/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		String cvv_str = Integer.toString(CVV); 
		String otp_str = Integer.toString(OTP); 
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		driver.get(applicationUrl);
	
		try {
			driver.findElement(By.xpath("//*[@class='btn buy']")).click();
		}catch (NoSuchElementException e) {
			System.out.println(e+"Element not found");	
		}
		
		try {
			driver.findElement(By.xpath("//input[@class='text-right']")).clear();
			driver.findElement(By.xpath("//input[@class='text-right']")).sendKeys(pillowValue);
		}catch (NoSuchElementException e) {
			System.out.println(e+"Element not found");
		}
	
		try {
			driver.findElement(By.xpath("//*[@value='Budi']")).clear();
			driver.findElement(By.xpath("//*[@value='Budi']")).sendKeys(name);
		}catch (NoSuchElementException e) {
			System.out.println(e+"Element not found");
		}
		
		try {
			driver.findElement(By.xpath("//*[@type='email']")).clear();
			driver.findElement(By.xpath("//*[@type='email']")).sendKeys(email);
		}catch (NoSuchElementException e) {
			System.out.println(e+"Element not found");
		}

		try {
			driver.findElement(By.xpath("//input[@value='081808466410']")).clear();
			driver.findElement(By.xpath("//input[@value='081808466410']")).sendKeys(phone);
		}catch (NoSuchElementException e) {
			System.out.println(e+"Element not found");
		}
		
		try {
			driver.findElement(By.xpath("//input[@value='Jakarta']")).clear();
			driver.findElement(By.xpath("//input[@value='Jakarta']")).sendKeys(city);
		}catch (NoSuchElementException e) {
			System.out.println(e+"Element not found");
		}
		
		try {
			driver.findElement(By.xpath("//*[@data-reactid='.0.0.1.0.3.0.0.4.1.0']")).clear();
			driver.findElement(By.xpath("//*[@data-reactid='.0.0.1.0.3.0.0.4.1.0']")).sendKeys(address);
		}catch (NoSuchElementException e) {
			System.out.println(e+"Element not found");
		}
		
		try {
			driver.findElement(By.xpath("//*[@value='10220']")).clear();
			driver.findElement(By.xpath("//*[@value='10220']")).sendKeys(postalCode);
		}catch (NoSuchElementException e) {
			System.out.println(e+"Element not found");
		}
		
		driver.findElement(By.xpath("//*[@class='cart-checkout-settings']")).click();
		
		driver.findElement(By.xpath("//*[@class='label main_setting_custom']")).click();
		driver.findElement(By.xpath("//*[@id='is_snap_pop_up:false']")).click();
		driver.findElement(By.xpath("//*[@class='btn btn-primary']")).click();
		
		driver.findElement(By.xpath("//*[@class='credit-card-full sprite']")).click();
		
		driver.findElement(By.xpath("//*[@name='cardnumber']")).sendKeys(cardNumber);
		driver.findElement(By.xpath("//*[@class='input-group col-xs-7']")).click();
		driver.findElement(By.xpath("//input[@placeholder='MM / YY']")).sendKeys(expiry);
		driver.findElement(By.xpath("//*[@class='input-group col-xs-5']")).click();
		driver.findElement(By.xpath("//*[@placeholder='123']")).sendKeys(cvv_str);
		
		List<WebElement> element=driver.findElements(By.xpath("//*[@name='promo']"));
		for(int i=0;i<element.size();i++) {
			if(element.get(i).isSelected()) {
				element.get(i).click();
			}
		}
		driver.findElement( By.xpath("//*[@class='button-main-content']")).click();

		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//*[@id='PaRes']")).click();
		driver.findElement(By.xpath("//*[@id='PaRes']")).sendKeys(otp_str);
		
		driver.findElement(By.xpath("//*[@class='btn btn-sm btn-success']")).click();
		
		try {
			WebDriverWait wait=new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.urlContains("success"));
		}catch(TimeoutException e) { 
			System.out.println("Failed to load url"+e);
		}
		
		Assert.assertTrue(driver.getCurrentUrl().endsWith("success"));
		
		driver.quit();
		}
	}
	
