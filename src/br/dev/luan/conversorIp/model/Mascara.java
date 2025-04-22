package br.dev.luan.conversorIp.model;

public class Mascara {
	private int cidr;
	
	public void setMascara( int cidr) {
		this.cidr = cidr;
	}
	public int getMascara() {
		return cidr;
	}
	
	// Converte CIDR em número inteiro (máscara);
	public String cidrToMask() {
		int[] octetos = new int[4];
		int tempCidr = cidr;
		for( int i = 0; i < 4; i++ ) {
			if(tempCidr >= 8) {
				octetos[i] = 255;
				tempCidr -= 8;
			} else if( tempCidr > 0 ) {
				 octetos[i] = 256 - (1 << (8 - tempCidr));
				 tempCidr = 0;
			} else {
				octetos[i] = 0;
			}
		}
		return String.format("%d.%d.%d.%d", octetos[0], octetos[1], octetos[2], octetos[3]);
	}
	
	//Converte a mascará em Binário
	public String maskToBinary() {
		int[] octetos = new int[4];
		int tempCidr = cidr;
		for( int i = 0; i < 4; i++ ) {
			if(cidr >= 8) {
				octetos[i] = 255;
				cidr -= 8;
			} else if( cidr > 0 ) {
				 octetos[i] = 256 - (1 << (8 - tempCidr));
				 cidr = 0;
			} else {
				octetos[i] = 0;
			}
		}
		
		String[] octetoBinary = new String[4];
		for (int i = 0 ; i < 4 ; i++) {
			String binary = Integer.toBinaryString(octetos[i]);
			 octetoBinary[i] = String.format("%8s", binary).replace(' ', '0');
		}

        return String.join(".", octetoBinary);
	}
	

}
