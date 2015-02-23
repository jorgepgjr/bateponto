package br.com.silvia;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Application {

	private static ChromeDriverService service;

	public static List<Ponto> execute() throws InterruptedException,
			IOException {
		createAndStartService();
		WebDriver driver;
		driver = new RemoteWebDriver(service.getUrl(),
				DesiredCapabilities.chrome());
		final List<Ponto> pontos = new ArrayList<Ponto>();
		try {
			driver.get("http://ppm.rsinet.com.br/channel/apontamento.do");
			driver.findElement(By.name("username")).sendKeys("silvia.correa");
			driver.findElement(By.name("password")).sendKeys("Mellany1802");
			driver.findElement(By.id("entrar")).click();
			driver.findElement(By.linkText("Ferramentas")).click();
			Thread.sleep(100L);
			driver.findElement(By.linkText("registrar ponto")).click();
			Thread.sleep(200L);
			// Registra o ponto
			driver.findElement(By.id("botaoRegistrarPonto")).click();
			
			final WebElement tabela = driver.findElement(By
					.id("PONTO_tblListagemRegistrarPonto"));
			if (tabela == null) {
				return null;
			}

			final List<WebElement> rows = tabela.findElements(By.tagName("tr"));

			if (rows == null || rows.isEmpty()) {
				return null;
			}
			
			for (WebElement row : rows) {
				Ponto ponto = new Ponto();
				List<WebElement> tds = row.findElements(By.tagName("td"));
				for (WebElement value : tds) {
					if (tds.indexOf(value) == 0) {
						ponto.setData(value.getText());
					} else if (tds.indexOf(value) == 1) {
						ponto.setHoraIni(value.getText());
					} else if (tds.indexOf(value) == 2) {
						ponto.setHoraFim(value.getText());
					}
				}
				pontos.add(ponto);
			}

			for (Ponto ponto1 : pontos) {
				System.out.print(ponto1.getData());
				System.out.print("       " + ponto1.getHoraIni());
				System.out.println("       " + ponto1.getHoraFim());
			}

			driver.close();
		} catch (Exception e) {
			driver.close();
		}
		return pontos;
	}

	public static void createAndStartService() throws IOException {
		service = new ChromeDriverService.Builder()
				.usingDriverExecutable(
						new File("src/main/resources/chromedriver"))
				.usingAnyFreePort().build();
		service.start();
	}

}
