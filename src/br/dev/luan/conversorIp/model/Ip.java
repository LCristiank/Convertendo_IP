package br.dev.luan.conversorIp.model;

public class Ip {
	private String ip;
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getIp() {
		return ip;
	}
	
	public String classificarIp() {
		String[] octeto = ip.split("\\.");
		int primeiroOcteto = Integer.parseInt(octeto[0]);
		
		if(primeiroOcteto >= 0 && primeiroOcteto <= 127) {
			return "Classe A";
		} else if(primeiroOcteto >= 128 && primeiroOcteto <= 191) {
			return "Classe B";
		} else if(primeiroOcteto >= 192 && primeiroOcteto <= 223) {
			return "Classe C";
		} else {
			return "Sem Classe";
		}
	}
	
}
