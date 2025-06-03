package br.dev.luan.conversorIp;

import br.dev.luan.conversorIp.gui.TelaConversor;
import br.dev.luan.conversorIp.model.Ip;

public class converterApp {
	public static void main(String[] args) {
		TelaConversor tela = new TelaConversor();
		Ip ip = new Ip();
		
		
		tela.criarTela();
		
		ip.setIp("192.168.0.0");
		ip.setMask(30);
		System.out.println("Máscara: " + ip.cidrToMask());
		System.out.println("Binário: " + ip.maskToBinary());
		System.out.println("IPs Disponíveis: " + ip.calcularIPsDisponiveis());
		System.out.println("Subredes: " + ip.calcularSubRedes());
		System.out.println("Salto de Rede: " + ip.calcularSalto());
		System.out.println("IP da Rede: " + ip.getIp());
		System.out.println("Primeiro IP válido: " + ip.primeiroIpValido());
		System.out.println("Ultimo IP válido: " + ip.ultimoIpValido());
		System.out.println("IP do Broadcast: " + ip.ipBroadcast());
		System.out.println("Redes: \n" + ip.showNetwork());
	}
}
