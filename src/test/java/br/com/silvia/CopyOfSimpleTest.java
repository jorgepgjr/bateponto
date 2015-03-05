package br.com.silvia;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class CopyOfSimpleTest {

	@Test
	public void sucessTest() throws InterruptedException, IOException {
//		WebDriver driver = new HtmlUnitDriver();
        // And now use this to visit Google
//        driver.get("http://www.google.com");		
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd_MM-HH_mm_ss");
		System.out.println(dateFormat.format(date));
	}

}
