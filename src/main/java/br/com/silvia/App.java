package br.com.silvia;

import java.awt.AWTException;
import java.awt.GridLayout;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

public class App {

	private static JFrame frame;
	private static JButton button;
	private static JCheckBox checkBox = new JCheckBox("Bate ponto ativado");
	private static JSpinner timeSpinner = new JSpinner( new SpinnerDateModel() );
	private static StartJobs jobs;
	private static boolean jobLigado;

	public static void main(String[] args) throws AWTException,
			UnsupportedFlavorException, IOException {
		App app = new App();
		jobs = new StartJobs();
		app.createAndShowGUI();
	}

	public void createAndShowGUI() {
		button = new JButton("Ligar o EasyDot =D");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (jobLigado) {
						jobs.stopJobs();
						jobLigado = false;
						button.setText("Ligar o EasyDot =D");
					} else {
						Date a = (Date) timeSpinner.getValue();
						SimpleDateFormat sdf = new SimpleDateFormat("mm HH");
						jobs.createAndStartJobs(sdf.format(a));
						jobLigado = true;
						button.setText("Desligar o EasyDot =/");
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			};
		});

		JButton buttonBater = new JButton("Bater Ponto Teste!");
		buttonBater.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BatePonto.execute();
			}
		});
		
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
		timeSpinner.setEditor(timeEditor);		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY,18);
		cal.set(Calendar.MINUTE,00);
		timeSpinner.setValue(cal.getTime()); // will only show the current time
		
		checkBox.setEnabled(true);
		checkBox.setSelected(true);
		
		frame = new JFrame();
		frame.getContentPane().setLayout(new GridLayout(2, 2));
		frame.setLocation(150, 150);
		frame.add(button);
		frame.add(buttonBater);
		frame.add(checkBox);
		frame.add(timeSpinner);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (jobLigado) {
					jobs.stopJobs();
				}
				super.windowClosing(e);

			}
		});
		
	}

	public static boolean isBatePontoSelected(){
		return checkBox.isSelected();
	}
}
