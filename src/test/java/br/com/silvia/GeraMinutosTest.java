package br.com.silvia;

import org.junit.Assert;
import org.junit.Test;

public class GeraMinutosTest {

	
	@Test
	public void geraMinutosAleatoriosAlmocoTest(){
		for (int i = 0; i < 60; i++) {
			int f = GeraMinutos.geraMinutosAleatoriosAlmoco();
			System.out.println(String.format("%02d", f));
			Assert.assertTrue(f <= 59 && f >= 0);
		}
	}
	
	@Test
	public void geraMinutosAleatoriosEntradaTest(){
		for (int i = 0; i < 60; i++) {
			int f = GeraMinutos.geraMinutosAleatoriosEntrada();
			System.out.println(String.format("%02d", f));
			Assert.assertTrue(f <= 30 && f >= 20);
		}
	}
	
}
