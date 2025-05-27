	package br.dev.luan.conversorIp.model;

public class Ip {
	private String endereco;
	private int cidr;
	private int[] octetos = new int [4];
	String[] octetoBinario = new String[4];

	
	public void setIp(String ip) {
		this.endereco = ip;
	}
	public String getIp() {
		return endereco;
	}
	public void setMask(int cidr) {
		this.cidr = cidr;
	}
	public int getMask() {
		return cidr;
	}
	
	
	public String classificarIp() {
		String[] octetosIp = endereco.split("\\.");
		int primeiroOcteto = Integer.parseInt(octetosIp[0]);
		if (octetosIp[0] == null || octetosIp[1] == null || octetosIp[2] == null || octetosIp[3] == null) {
			return "Faltando número no IP.";
		} else {
			if(primeiroOcteto >= 0 && primeiroOcteto <= 127) {
				return "Classe A";
			} else if(primeiroOcteto >= 128 && primeiroOcteto <= 191) {
				return "Classe B";
			} else if(primeiroOcteto >= 192 && primeiroOcteto <= 223) {
				return "Classe C";
			} else if (primeiroOcteto >= 224 && primeiroOcteto <= 239){
				return "Classe D";
			} else if (primeiroOcteto >= 240 && primeiroOcteto <= 255){
				return "Classe E";
			} else {
				return "Sem Classe";
			}
		}
		
	}
	
	public String cidrToMask() {
		int tempCidr = cidr;
		for (int i = 0; i  < 4; i++) {
			if(tempCidr >=8) {
				octetos[i] = 255;
				tempCidr -= 8;
			} else if(tempCidr > 0) {
				octetos[i] = 256 - (1 << (8 - tempCidr));
				tempCidr = 0;
			} else {
				octetos[i] = 0;
			}
			
		}
		return String.format("%d.%d.%d.%d" , octetos[0], octetos[1], octetos[2], octetos[3]);
	}
	
	public String maskToBinary() {
		int tempCidr = cidr;
		for (int i = 0; i  < 4; i++) {
			if(tempCidr >=8) {
				octetos[i] = 255;
				tempCidr -= 8;
			} else if(tempCidr > 0) {
				octetos[i] = 256 - (1 << (8 - tempCidr));
				tempCidr = 0;
			} else {
				octetos[i] = 0;
			}
		}
		
		for (int i = 0; i < 4; i++) {
			String binary = Integer.toBinaryString(octetos[i]);
			octetoBinario[i] = String.format("%8s", binary).replace(' ', '0');
			
		}
		
		return String.join(".", octetoBinario);
	}
	
	//calculo de ips disponíveis funcionando
	public int calcularIPsDisponiveis() {
		if (cidr < 0 || cidr > 32) {
			return 0;
		} else if (cidr == 31 || cidr == 32) {
			return 0;
		} else {
			return (int) Math.pow(2, 32 - cidr) - 2;
		}
	}
	
	public int calcularSubRedes() {
		String binaryOctectFourth = octetoBinario[3];
		int contador = 0;
		for (char c : binaryOctectFourth.toCharArray()) {
            if (c == '1') {
                contador++;
            }
        }
		return (int) Math.pow(2, contador);

	}
	
	public int calcularQuantidadesSaltos() {
		int mascara = octetos[3];
		int calculoSalto = 256 - mascara;
		return calculoSalto;
	}
	
	public String primeiroIpValido() {
		String[] octetosIp = endereco.split("\\.");
		String ultimoOcteto = octetosIp[3];
		int primeiroIpValido = Integer.parseInt(ultimoOcteto) + 1;
		
		return octetosIp[0] + "." + octetosIp[1] + "." + octetosIp[2] + "." + primeiroIpValido;
	}
	
	public String ultimoIpValido() {
		String[] octetosIp = endereco.split("\\.");
		String[] mascara = cidrToMask().split("\\.");
		String ultimoOcteto = mascara[0];
		int ultimoIpValido = Integer.parseInt(ultimoOcteto) - 1;

		return octetosIp[0] + "." + octetosIp[1] + "." + octetosIp[2] + "." + ultimoIpValido;
	}
	public String ipBroadcast() {
		String[] octetosIp = endereco.split("\\.");
		String[] mascara = cidrToMask().split("\\.");
		String ultimoOcteto = mascara[0];
		int broadcastIp = Integer.parseInt(ultimoOcteto);
		return octetosIp[0] + "." + octetosIp[1] + "." + octetosIp[2] + "." + broadcastIp;
	}
}
