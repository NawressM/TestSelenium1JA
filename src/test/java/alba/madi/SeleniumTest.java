package alba.madi;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.TestCase;

public class SeleniumTest extends TestCase {
	private static final String URL = "https://www.fr.jal.co.jp/frl/en/";
	private static final String PATH_CHROME_DRIVER = "C:\\Program Files\\chromedriver.exe";
	public static WebDriver driver;

	@Test
	public static void testfirstTest() {
		System.setProperty("webdriver.chrome.driver", PATH_CHROME_DRIVER);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// declarations des variables
		String villedepart = "villedepart";
		String Villearrivee = "villearrivee";
		String Flightsnumbers = " ", horaires, prix;
		driver.get(URL);

		pausa(3);

//driver.findElement(By.className("JS_ciBox_submit")).click();
		driver.findElements(By.cssSelector("#JS_ciBox_contents img")).get(1).click();
		Select s = new Select(driver.findElement(By.id("mdlDepLocation1")));
		s.selectByValue("NCE");
		Select s1 = new Select(driver.findElement(By.id("mdlArrLocation1")));
		s1.selectByValue("TYO");
//DEPART
		Select s2 = new Select(driver.findElement(By.id("DEPARTURE_DATE_1_MONTH")));
		s2.selectByValue("10");
		Select s3 = new Select(driver.findElement(By.id("DEPARTURE_DATE_1_DAY")));
		s3.selectByValue("14");
//RETURN
		Select s4 = new Select(driver.findElement(By.id("DEPARTURE_DATE_2_MONTH")));
		s4.selectByValue("11");
		Select s5 = new Select(driver.findElement(By.id("DEPARTURE_DATE_2_DAY")));
		s5.selectByValue("18");
//CLASSE
		Select s6 = new Select(driver.findElement(By.id("CFF_1")));
		s6.selectByValue("1YE");
//NB PASSAGERS
		Select s7 = new Select(driver.findElement(By.id("mdlNbAdt")));
		s7.selectByValue("1");
		Select s8 = new Select(driver.findElement(By.id("mdlNbChd")));
		s8.selectByValue("0");
		Select s9 = new Select(driver.findElement(By.id("mdlNbInf")));
		s9.selectByValue("0");

//Rechercher
		driver.findElement(By.id("mdlFormSubmit")).click();
//recuperer les donnees
		String VD = "villedepart";
		VD = driver.findElement(By.id("bound-departure-0")).getText();
		System.out.println("la ville de depart est = " + VD);

		String VA = "villearrivee";
		VA = driver.findElement(By.id("bound-arrival-0")).getText();
		System.out.println("la ville darrivée est = " + VA);

		// AVAI PAGE
		/*
		 * WebElement elementAction =
		 * driver.findElement(By.cssSelector("#flightNumber-0-0.flight-identifier")) ;
		 * Actions action = new Actions(driver) ;
		 * action.moveToElement(elementAction).build().perform();
		 */

	/* la 1ere phase de comparaison*/
		//waiting time to click
		//protected void waitForThePage() {
		//on voit bien selenium time out exception,on lui dit tu va essayer de faire ça et dans le casa que tu trouve time out excp : on utilise catch
		try {
			System.out.println(LocalDateTime.now()) ;
		    WebDriverWait Wait = new WebDriverWait(driver, 10) ;
			Wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#flightNumber-0-0 .flight-identifier"))));
		} catch (TimeoutException e) {
			System.out.println("J'ai pas trouvé ton webelement et maintenant est :" +LocalDateTime.now()) ;
		}
			//chercher l'element pour clicker et le faire sinon
			if (driver.findElement(By.cssSelector("#flightNumber-0-0 .flight-identifier")).isDisplayed()) {
			System.out.println("Le flight number s'affiche");
			
		} else {
			System.out.println("Le flight number s'affiche pas, clicker pour l'afficher");
			 driver.findElements(By.cssSelector(".medium-pattern.mb1.p5")).get(0).click();
		}
			String FN1= driver.findElement(By.cssSelector("#flightNumber-0-0 .flight-identifier")).getText().trim() ;
			System.out.println("Le flight number 1 est : " +FN1);

		try {
				System.out.println(LocalDateTime.now()) ;
			    WebDriverWait Wait = new WebDriverWait(driver, 10) ;
				Wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#flightNumber-0-0 .flight-identifier"))));
		} catch (TimeoutException e) {
				System.out.println("J'ai pas trouvé ton webelement et maintenant est :" +LocalDateTime.now()) ;
		}
		    
		    
		    pausa(20) ;
		    
		    
		/*
		 * String FN ="Flightsnumbers" ; WebElement FN1 =
		 * (driver.findElement(By.cssSelector(".screenreader-only"))).get(0).click(); FN
		 * = FN1.getText() ; System.out.println("FLIGHT numbers 1 = " +FN );
		 */

		/*
		 * String FN1 = driver.findElement(By.className("screenreader-only")).getText()
		 * ; System.out.println("NUMERO DE VOL 1= " +FN1);
		 * 
		 * /*String FN3 = driver.findElement(By.id("flightNumber-1-0")).getText() ;
		 * System.out.println("FLIGHT numbers 3 = " +FN3);
		 * 
		 * String FN4 = driver.findElement(By.id("flightNumber-1-1")).getText() ;
		 * System.out.println("FLIGHT numbers 4 = " +FN4); String H1 =
		 * driver.findElement(By.tagName("datetime")).getText() ;
		 * System.out.println("HORAIRE de depart 1 est = " +H1);
		 */

		String H1 = driver.findElement(By.id("segmentOriginDate-0-0")).getText();
		System.out.println("HORAIRE de depart 1 est = " + H1);

		// PRICE
		String PRICE1 = driver.findElement(By.id("sidebarPriceSummaryTotalPrice")).getText();
		System.out.println("LE PRIX 1 est = " + PRICE1);

//continuer
		pausa(5);
		driver.findElement(By.id("continueButton")).click();
//INFOS PASSAGERS
		Select s10 = new Select(driver.findElement(By.id("0-title")));
		s10.selectByValue("MRS");

		// nom prenom middle
		driver.findElement(By.id("0-last-name")).sendKeys("Nawress");
		driver.findElement(By.id("0-first-name")).sendKeys("MADI");
		driver.findElement(By.id("0-middle-name")).sendKeys("ABDEL");

		// selectionner les dates de naissances et le sexe
		Select s11 = new Select(driver.findElement(By.id("0-gender")));
		s11.selectByValue("string:FEMALE");

		Select s12 = new Select(driver.findElement(By.id("0-birth-date-day")));
		s12.selectByValue("string:06");

		Select s13 = new Select(driver.findElement(By.id("0-birth-date-month")));
		s13.selectByValue("string:04");

		Select s14 = new Select(driver.findElement(By.id("0-birth-date-year")));
		s14.selectByValue("string:1992");

		// PASSPORTS
		driver.findElement(By.id("0-passport-number")).sendKeys("FY4728");
		Select s18 = new Select(driver.findElement(By.id("0-issuing-country")));
		s18.selectByValue("string:TN");
		Select s19 = new Select(driver.findElement(By.id("0-validity-day")));
		s19.selectByValue("string:10");
		Select s20 = new Select(driver.findElement(By.id("0-validity-month")));
		s20.selectByValue("string:10");
		Select s21 = new Select(driver.findElement(By.id("0-validity-year")));
		s21.selectByValue("string:2023");

		// NATIONALITY
		Select s15 = new Select(driver.findElement(By.id("0-nationality")));
		s15.selectByValue("string:TN");

		// CONTACT INFOS
		// PHONE
		Select s16 = new Select(driver.findElement(By.id("phone1-phone-type-0")));
		s16.selectByValue("string:APM");

		Select s17 = new Select(driver.findElement(By.id("phone1-phone-country-0")));
		s17.selectByValue("FRA");

		driver.findElement(By.id("phone1-phone-number-0")).sendKeys("772147037");

		// EMAIL
		driver.findElement(By.id("email-guest-address")).sendKeys("alba.madi@gmail.com");
		driver.findElement(By.id("email-confirm-new")).sendKeys("alba.madi@gmail.com");

		// CONTINUE
		pausa(2);
		driver.findElement(By.id("continueButton")).click();
		driver.findElement(By.id("continueButton-PCOF")).click();
		// seat continue
		driver.findElement(By.id("seatContinue")).click();

/* 2eme phase de comparaison:recuperer les données ici*/

		String FN2 = driver.findElement(By.id("flightNumber-0-0")).getText();
		System.out.println("Flight number 2 est:" + FN2);
		// String H ="horaires" ;
		String H2 = driver.findElement(By.id("segmentOriginDate-0-0")).getText();
		System.out.println("HORAIRE de depart 2 est = " + H2);
		// PRICE
		String PRICE2 = driver.findElement(By.id("sidebarPriceSummaryTotalPrice")).getText();
		System.out.println("LE PRIX 2 est = " + PRICE2);

		// PAYMENT
		pausa(3);
		driver.findElement(By.id("purchaseButton")).click();
		// card payment
		driver.findElement(By.id("CCnb")).sendKeys("4012888888881881");

		Select s22 = new Select(driver.findElement(By.id("expiration-month-id")));
		s22.selectByValue("number:3");

		Select s23 = new Select(driver.findElement(By.id("expiration-year-id")));
		s23.selectByValue("number:2022");

		Select s24 = new Select(driver.findElement(By.id("CCname")));
		s24.selectByValue("object:151");

		driver.findElement(By.id("sec-code")).sendKeys("335");

		// CONTINUE PAYMENT
		pausa(3);
		driver.findElement(By.id("continueButton")).click();
		WebElement element = driver.findElement(By.id("confirm"));
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().build().perform();
		// driver.findElement(By.id("confirm")).click() ;
		driver.findElement(By.id("continueButton")).click();

		// recuperer des données
		// WebElement elementName =
		// driver.findElement(By.LocatorStrategy("LocatorValue"));
		/*
		 * String nomSTRING ="valeurInitiale" ; nomSTRING =
		 * driver.findElement(By.id("")).gettext() ;
		 */

		
/*faire les comparaisons */		

		assertEquals("les prix sont les memes ?",PRICE1,PRICE2);
		//assertEquals("les flight numbers sont les memes?" ,FN1,FN2) ;
		assertTrue(FN2.contains(FN1)) ;
		System.out.println("bon site :D olalalaaa");
		
	}

	private static void waitForThePage() {
		// TODO Auto-generated method stub
		
	}

	private static void pausa(int second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}

	}

}
