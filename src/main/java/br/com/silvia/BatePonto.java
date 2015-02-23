package br.com.silvia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

public class BatePonto implements Job {

	public static void main(String[] args) throws IOException,
			InterruptedException, ParseException, SchedulerException {

		JobDetail job = new JobDetail();
		job.setJobClass(BatePonto.class);
		job.setDurability(true);
		job.setName("dummyJobName");
		job.setGroup("tests");

		CronTrigger triggerEntrada = new CronTrigger();
		triggerEntrada.setName("triggerEntrada");
		triggerEntrada.setCronExpression("0 20 8 ? * MON-FRI *");
		triggerEntrada.setJobName("dummyJobName");
		triggerEntrada.setJobGroup("tests");

		CronTrigger triggerAlmoco = new CronTrigger();
		triggerAlmoco.setName("triggerAlmoco");
		triggerAlmoco.setCronExpression("0 0 12 ? * MON-FRI *");
		triggerAlmoco.setJobName("dummyJobName");
		triggerAlmoco.setJobGroup("tests");

		CronTrigger triggerAlmocoVolta = new CronTrigger();
		triggerAlmocoVolta.setName("triggerAlmocoVolta");
		triggerAlmocoVolta.setCronExpression("0 5 13 ? * MON-FRI *");
		triggerAlmocoVolta.setJobName("dummyJobName");
		triggerAlmocoVolta.setJobGroup("tests");

		CronTrigger triggerSaida = new CronTrigger();
		triggerSaida.setName("triggerSaida");
		triggerSaida.setCronExpression("0 30 17 ? * MON-FRI *");
		triggerSaida.setJobName("dummyJobName");
		triggerSaida.setJobGroup("tests");

		// schedule it
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.start();
		scheduler.addJob(job, true);	
		scheduler.scheduleJob(triggerEntrada);
		scheduler.scheduleJob(triggerAlmoco);
		scheduler.scheduleJob(triggerAlmocoVolta);
		scheduler.scheduleJob(triggerSaida);
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("Disparado o methodo execute em:  " + new Date());
		try {
			List<Ponto> pontos = Application.execute();
			File file = new File("/home/jorge/Documentos/ponto.txt");
			
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw);
			if(pontos != null){
				for (Ponto ponto : pontos) {
					out.println(ponto.getData() + "   " + ponto.getHoraIni() + "   " + ponto.getHoraFim());					
				}
			}
			out.close();
			
				
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
