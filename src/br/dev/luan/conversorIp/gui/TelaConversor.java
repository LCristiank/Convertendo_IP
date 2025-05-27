	package br.dev.luan.conversorIp.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.dev.luan.conversorIp.model.Ip;

public class TelaConversor {
	private JLabel labelIp;
	private JLabel labelMask;

	private JTextField textIp;
	private JTextField textCidr;
	
	private JButton buttonConverter;
	private JButton buttonLimpar;
	
	private JLabel labelClassIp;
	private JLabel labelResultMask;
	private JLabel labelResultBinary;
	private JLabel labelIps;
	
	private JLabel labelError;
	

	public void criarTela(){
		Font fonte = new Font("Arial", Font.BOLD, 30);
		
		JFrame tela = new JFrame();
		tela.setSize(400, 500);
		tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tela.setTitle("Conversor de IPV4 e Máscara");
		tela.setLocationRelativeTo(null);
		tela.setLayout(null);
		tela.setResizable(false);
		
		//
		labelIp = new JLabel();
		labelIp.setText("Informe seu IP: (EX.: 192.168.0.0)");
		labelIp.setBounds(50, 30, 250, 30);
		
		labelMask = new JLabel();
		labelMask.setText("/");
		labelMask.setBounds(270, 60, 40, 30);
		labelMask.setFont(fonte);
		
	    //Entrada de Dados
		textCidr = new JTextField();
		textCidr.setBounds(300, 60, 40, 30);
		textCidr.setHorizontalAlignment(textIp.CENTER);
		
		textIp = new JTextField();
		textIp.setBounds(60, 60, 180, 30);
		textIp.setHorizontalAlignment(textIp.CENTER);

		labelClassIp = new JLabel();
		labelClassIp.setBounds(50, 160, 270, 30);
		
		labelResultMask = new JLabel();
		labelResultMask.setBounds(50, 260, 150, 30);
		
		labelResultBinary = new JLabel();
		labelResultBinary.setBounds(50, 240, 300, 30);
		
		labelIps = new JLabel();
		labelIps.setBounds(50, 190, 210, 30);

		labelError = new JLabel();
		labelError.setBounds(50, 200, 300, 30);
		
		buttonConverter = new JButton();
		buttonConverter.setText("Verificar");
		buttonConverter.setBounds(50, 100, 145, 30);
		buttonConverter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Ip ip = new Ip();
		
				String txtCidr = textCidr.getText();
				String txtIp = textIp.getText();
				String[] ipOctetos = new String[4];
				ipOctetos =  txtIp.split("\\.");
				boolean ipNumero = false;
				
				try {
					if (ipOctetos.length >= 5) {
						JOptionPane.showMessageDialog(
								null,
								"IP inválido! O IP está no formato incorreto", 
								txtIp, 
								JOptionPane.ERROR_MESSAGE
								);
					} else {
						ipNumero = true; 
					}
				} catch (Exception e2) {
					ipNumero = false;
				}
				if (ipNumero) { 
					ip.setIp(txtIp);
					String classificarIp = ip.classificarIp();
					labelClassIp.setText("Classe IP: " + classificarIp);
				} else {
					JOptionPane.showMessageDialog(null, "IP inválido! Não aceitamos letras ou está incompleto", txtIp, JOptionPane.ERROR_MESSAGE);
				}
				
				try {

					int cidr = Integer.parseInt(txtCidr);
					if (cidr > 32) {
						JOptionPane.showMessageDialog(null, "CIDR Não pode ser maior que 32!!", txtCidr, JOptionPane.ERROR_MESSAGE);
					} else {
						ip.setMask(cidr);
						String mascara = ip.cidrToMask();
						String mascaraBinario = ip.maskToBinary();
						int ipsDisponiveis = ip.calcularIPsDisponiveis();
						
						labelResultMask.setText("Máscara: " + mascara);
						labelResultBinary.setText("Binário: " + mascaraBinario);
						labelIps.setText("IPs Disponíveis: " + ipsDisponiveis); 
						
					}
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "CIDR inválido!!!", txtCidr, JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		buttonLimpar = new JButton();
		buttonLimpar.setText("Limpar Dados");
		buttonLimpar.setBounds(200, 100, 145, 30);
		buttonLimpar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				labelError.setText(null);
				
				labelClassIp.setText(null);
				labelResultMask.setText(null);
				labelResultBinary.setText(null);
				labelIps.setText(null);
			}
		});
		
		tela.getContentPane().add(labelIp);
		tela.getContentPane().add(labelMask);
		tela.getContentPane().add(textIp);
		tela.getContentPane().add(textCidr);
		tela.getContentPane().add(labelClassIp);
		tela.getContentPane().add(labelMask);
		tela.getContentPane().add(labelResultMask);
		tela.getContentPane().add(labelResultBinary);
		tela.getContentPane().add(labelIps);
		tela.getContentPane().add(buttonConverter);
		tela.getContentPane().add(buttonLimpar);
		
		tela.setVisible(true);
	}

}
