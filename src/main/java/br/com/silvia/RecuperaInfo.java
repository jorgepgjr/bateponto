package br.com.silvia;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class RecuperaInfo {

	public static List<Ponto> execute() {
		System.setProperty("java.awt.headless", "false");
		WebDriver driver = new FirefoxDriver();

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
			BatePonto.takeSS();
			RecuperaInfo.mapeaPontos(driver);

		} catch (Exception e) {
			System.out.println(e);
			driver.close();
		}
		driver.close();
		return pontos;
	}

	private static List<Ponto> mapeaPontos(WebDriver driver) {
		final List<Ponto> pontos = new ArrayList<Ponto>();
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
		return pontos;

	}
}
