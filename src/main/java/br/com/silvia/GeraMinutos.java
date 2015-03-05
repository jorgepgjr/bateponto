package br.com.silvia;

import java.util.Random;

public class GeraMinutos {
	
	public static int geraMinutosAleatoriosAlmoco(){
		Random random = new Random();
		int num = random.nextInt(59);		
		return num;		
	}
	
	public static int geraMinutosAleatoriosEntrada(){
		Random random = new Random();
		int num = random.nextInt(11);
		return num+20;		
	}
}
