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
		int max = 0;
		int min = 60;
		for (int i = 0; i < 1000; i++) {
			int f = GeraMinutos.geraMinutosAleatoriosEntrada();
			System.out.println(String.format("%02d", f));
			Assert.assertTrue(f <= 40 && f >= 15);
			max = f > max ? f : max;
			min = f < min ? f : min;
		}
		System.out.println("Max = " + max + "  Min = "+ min);
	}
	
}
