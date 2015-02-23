package br.com.silvia;


public class Ponto {
	
	private String data;
	private String horaIni;
	private String horaFim;
	
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getHoraIni() {
		return horaIni;
	}
	public void setHoraIni(String horaIni) {		
		this.horaIni = setHora(horaIni);
	}
	public String getHoraFim() {
		return horaFim;
	}
	public void setHoraFim(String horaFim) {
		this.horaFim = setHora(horaFim);
	}
	private String setHora(String hora){
		if (hora.equals("--:--")) {
			return "";
		}
		return hora;
	}
	
	
}
