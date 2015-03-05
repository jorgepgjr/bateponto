package br.com.silvia;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class BatePonto {

	public static List<Ponto> execute() {
		return BatePonto.execute(null);
	}
	public static List<Ponto> execute(String firefoxPath) {		
		System.setProperty("java.awt.headless", "false");
		WebDriver driver = new FirefoxDriver();
		if (firefoxPath != null) {
			FirefoxBinary binary = new FirefoxBinary(new File(firefoxPath));
			FirefoxProfile profile = new FirefoxProfile();			
			driver = new FirefoxDriver(binary, profile);
		}
		
		// System.setProperty("webdriver.chrome.driver",
		// "src/main/resources/chromedriver.exe");
		// ChromeDriver(DesiredCapabilities.chrome());

		final List<Ponto> pontos = new ArrayList<Ponto>();
		try {
			driver.get("http://ppm.rsinet.com.br/channel/apontamento.do");
			driver.findElement(By.name("username")).sendKeys("silvia.correa");
			driver.findElement(By.name("password")).sendKeys("Mellany1802");
			driver.findElement(By.id("entrar")).click();

			// Apenas para esperar a página carregar
			driver.findElement(By.linkText("Ferramentas"));

			// Executa JS para mostrar o Modal de registar ponto
			((JavascriptExecutor) driver)
					.executeScript("showModalRegistroPonto();");
			Thread.sleep(1000L);

			if (App.isBatePontoSelected()) {
				// Registra o ponto				
				((JavascriptExecutor) driver)
				.executeScript("baterPonto(ID_USUARIO);");
			}

			// driver.findElement(By.linkText("Ferramentas")).click();
			// driver.findElement(By.linkText("registrar ponto")).click();
			// Registra o ponto
			// driver.findElement(By.id("botaoRegistrarPonto")).click();

		} catch (Exception e) {
			System.out.println(e);
			driver.close();
		}
		driver.close();
		return pontos;
	}

	public static void takeSS() {
		Robot robot;
		try {
			robot = new Robot();
			BufferedImage a = robot.createScreenCapture(new Rectangle(Toolkit
					.getDefaultToolkit().getScreenSize()));
			Date date = new Date();
			DateFormat dateFormat = new SimpleDateFormat("dd_MM-HH_mm_ss");
			File ss = new File("./src/main/resources/"
					+ dateFormat.format(date) + ".jpg");
			ImageIO.write(a, "jpg", ss);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
