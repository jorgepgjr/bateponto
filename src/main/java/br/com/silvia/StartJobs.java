package br.com.silvia;

import java.util.Date;

import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

public class StartJobs implements Job {

	Scheduler scheduler;
	public void createAndStartJobs(String pontoFinal) {
		try {
			//Numero gerado aleatóriamente entre 0 e 60
			int numAlmoco = GeraMinutos.geraMinutosAleatoriosAlmoco();
			
			int numEntrada = GeraMinutos.geraMinutosAleatoriosEntrada();

			JobDetail job = new JobDetail();
			job.setJobClass(StartJobs.class);
			job.setDurability(true);
			job.setName("dummyJobName");
			job.setGroup("tests");
			
			CronTrigger triggerEntrada = new CronTrigger();
			triggerEntrada.setName("triggerEntrada");
			triggerEntrada.setCronExpression("0 "+String.format("%02d", numEntrada)+" 8 ? * MON-FRI *");
			triggerEntrada.setJobName("dummyJobName");
			triggerEntrada.setJobGroup("tests");
			
			CronTrigger triggerAlmoco = new CronTrigger();
			triggerAlmoco.setName("triggerAlmoco");
			triggerAlmoco.setCronExpression("0 "+String.format("%02d", numAlmoco)+" 11 ? * MON-FRI *");
			triggerAlmoco.setJobName("dummyJobName");
			triggerAlmoco.setJobGroup("tests");
			
			CronTrigger triggerAlmocoVolta = new CronTrigger();
			triggerAlmocoVolta.setName("triggerAlmocoVolta");
			triggerAlmocoVolta.setCronExpression("0 "+String.format("%02d", numAlmoco+1)+" 12 ? * MON-FRI *");
			triggerAlmocoVolta.setJobName("dummyJobName");
			triggerAlmocoVolta.setJobGroup("tests");
			
			CronTrigger triggerSaida = new CronTrigger();
			triggerSaida.setName("triggerSaida");
			triggerSaida.setCronExpression("0 "+ pontoFinal +" ? * MON-FRI *");
			triggerSaida.setJobName("dummyJobName");
			triggerSaida.setJobGroup("tests");
			
			// schedule it
			scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			scheduler.addJob(job, true);
			scheduler.scheduleJob(triggerEntrada);
			scheduler.scheduleJob(triggerAlmoco);
			scheduler.scheduleJob(triggerAlmocoVolta);
			scheduler.scheduleJob(triggerSaida);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void createAndStartJobs() {
		this.createAndStartJobs("00 18");
	}

	public void stopJobs() {
		try {
			scheduler.shutdown();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("Disparado o methodo execute em:  " + new Date());
		BatePonto.execute();
		// try {
		//
		// File file = new File("src/main/resources/ponto.txt");
		//
		// // if file doesnt exists, then create it
		// if (!file.exists()) {
		// file.createNewFile();
		// }
		// FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		// BufferedWriter bw = new BufferedWriter(fw);
		// PrintWriter out = new PrintWriter(bw);
		// if(pontos != null){
		// for (Ponto ponto : pontos) {
		// out.println(ponto.getData() + "   " + ponto.getHoraIni() + "   " +
		// ponto.getHoraFim());
		// }
		// }
		// out.close();
		//
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}
}
