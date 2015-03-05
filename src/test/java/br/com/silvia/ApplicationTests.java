package br.com.silvia;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BatePonto.class)
public class ApplicationTests {

	@Test
	public void contextLoads() throws InterruptedException {
//		 Application app = new Application();
//		 app.execute();
//		 
//		 
			Robot robot;
			try {
				System.setProperty("java.awt.headless", "false");
				robot = new Robot();
				BufferedImage a = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
				Date date = new Date();
				DateFormat dateFormat = new SimpleDateFormat("dd_MM-HH_mm_ss");
				File ss = new File("./src/main/resources/"+dateFormat.format(date)+".jpg");
				ImageIO.write(a, "jpg", ss);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

}
