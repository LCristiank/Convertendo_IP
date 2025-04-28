package br.dev.luan.conversorIp;

import br.dev.luan.conversorIp.gui.TelaConversor;
import br.dev.luan.conversorIp.model.Ip;
import br.dev.luan.conversorIp.model.Mascara;

public class converterApp {
	public static void main(String[] args) {
		Ip ip = new Ip();
		Mascara mask = new Mascara();
		TelaConversor tela = new TelaConversor();
		
		tela.criarTela();
		ip.setIp("192.168.12.10");
		mask.setMascara(33);;
		System.out.println(mask.cidrToMask());
		System.out.println(mask.maskToBinary());
		System.out.println(ip.classificarIp());
		
	}
}
