package br.com.silvia;

import java.util.Random;

/**
 * @author br3jpg
 *
 */
public class GeraMinutos {
	
	public static int geraMinutosAleatoriosAlmoco(){
		Random random = new Random();
		int num = random.nextInt(59);		
		return num;		
	}
	/**
	 * Gera um numero aleatório entre 15 e 40
	 * @return
	 */
	public static int geraMinutosAleatoriosEntrada(){
		Random random = new Random();
		int num = random.nextInt(26);
		return num+15;		
	}
}
