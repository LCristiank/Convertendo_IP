package br.dev.luan.conversorIp.model;

public class Ip {
    private String endereco;
    private int cidr;
    private int[] octetos = new int[4];
    private String[] octetoBinario = new String[4];

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

    public String cidrToMask() {
        int tempCidr = cidr;
        for (int i = 0; i < 4; i++) {
            if (tempCidr >= 8) {
                octetos[i] = 255;
                tempCidr -= 8;
            } else if (tempCidr > 0) {
                octetos[i] = 256 - (1 << (8 - tempCidr));
                tempCidr = 0;
            } else {
                octetos[i] = 0;
            }
        }
        return String.format("%d.%d.%d.%d", octetos[0], octetos[1], octetos[2], octetos[3]);
    }

    public String maskToBinary() {
        int tempCidr = cidr;
        for (int i = 0; i < 4; i++) {
            if (tempCidr >= 8) {
                octetos[i] = 255;
                tempCidr -= 8;
            } else if (tempCidr > 0) {
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
        if (cidr < 24 || cidr > 30) {
            return 0; // Assuming we are subnetting a /24 network
        }
        int borrowedBits = cidr - 24; // Number of bits borrowed from host portion
        return (int) Math.pow(2, borrowedBits);
    }

    public int calcularSalto() {
        if (cidr < 24 || cidr > 30) {
            return 0;
        }
        int hostBits = 32 - cidr;
        return (int) Math.pow(2, hostBits); // Total IPs per subnet, including network and broadcast
    }

    public String primeiroIpValido() {
        String[] octetosIp = endereco.split("\\.");
        int ultimoOcteto = Integer.parseInt(octetosIp[3]);
        int networkOctet = ultimoOcteto & octetos[3]; // Apply mask to get network address
        int primeiroIpValido = networkOctet + 1;
        return String.format("%s.%s.%s.%d", octetosIp[0], octetosIp[1], octetosIp[2], primeiroIpValido);
    }

    public String ultimoIpValido() {
        String[] octetosIp = endereco.split("\\.");
        int ultimoOcteto = Integer.parseInt(octetosIp[3]);
        int networkOctet = ultimoOcteto & octetos[3]; // Apply mask to get network address
        int salto = calcularSalto();
        int ultimoIpValido = networkOctet + salto - 2;
        return String.format("%s.%s.%s.%d", octetosIp[0], octetosIp[1], octetosIp[2], ultimoIpValido);
    }

    public String ipBroadcast() {
        String[] octetosIp = endereco.split("\\.");
        int ultimoOcteto = Integer.parseInt(octetosIp[3]);
        int networkOctet = ultimoOcteto & octetos[3]; // Apply mask to get network address
        int salto = calcularSalto();
        int broadcastIp = networkOctet + salto - 1;
        return String.format("%s.%s.%s.%d", octetosIp[0], octetosIp[1], octetosIp[2], broadcastIp);
    }

    public String showNetwork() {
        // Validate IP address
        String[] octetosIp = endereco.split("\\.");
        if (octetosIp.length != 4) {
            return "Error: Invalid IP address!";
        }

        try {
            // Calculate subnet details
            cidrToMask(); // Ensure mask is calculated
            int ultimoOcteto = Integer.parseInt(octetosIp[3]);
            int networkOctet = ultimoOcteto & octetos[3]; // Apply mask to get network address
            int salto = calcularSalto();
            int numSubRedes = calcularSubRedes();

            StringBuilder resultado = new StringBuilder();

            // Iterate through subnets
            for (int i = 0; i < numSubRedes && (networkOctet + (i * salto)) <= 255; i++) {
                int currentOctet = networkOctet + (i * salto);
                resultado.append(String.format("\n SubRede: %d: %s.%s.%s.%d \n", 
                    i + 1, octetosIp[0], octetosIp[1], octetosIp[2], currentOctet));

                // List all usable IPs
                int primeiroIp = currentOctet + 1;
                int ultimoIp = currentOctet + salto - 2;
                resultado.append(" IPs Disponiveis: \n");
                for (int ip = primeiroIp; ip <= ultimoIp; ip++) {
                    resultado.append(String.format(" - %s.%s.%s.%d \n", 
                        octetosIp[0], octetosIp[1], octetosIp[2], ip));
             
                }

                // Broadcast address
                resultado.append(String.format("  Broadcast: %s.%s.%s.%d\n", 
                    octetosIp[0], octetosIp[1], octetosIp[2], currentOctet + salto - 1));
            }

            return resultado.toString();
        } catch (NumberFormatException e) {
            return "Error: Invalid last octet!";
        }
    }
}