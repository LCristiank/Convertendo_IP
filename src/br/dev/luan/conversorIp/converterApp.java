package br.dev.luan.conversorIp;

import br.dev.luan.conversorIp.gui.TelaConversor;
import br.dev.luan.conversorIp.model.Ip;
import br.dev.luan.conversorIp.model.Mascara;

public class converterApp {
	public static void main(String[] args) {
		TelaConversor tela = new TelaConversor();
		Ip ip = new Ip();
		
		
//		tela.criarTela();
		
		ip.setIp("192.168.0.0");
		ip.setMask(24);
		System.out.println(ip.cidrToMask());
		System.out.println(ip.maskToBinary());
		System.out.println(ip.calcularIPsDisponiveis());
		System.out.println(ip.calcularSubRedes());
		System.out.println(ip.calcularQuantidadesSaltos());
	}
}
