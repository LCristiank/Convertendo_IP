package br.dev.luan.conversorIp.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.dev.luan.conversorIp.model.Ip;
import br.dev.luan.conversorIp.model.Mascara;

public class TelaConversor {
	private JLabel labelIp;
	private JLabel labelMask;

	private JTextField textFirstOctect;
	private JTextField textSecondOctect;
	private JTextField textThirdOctect;
	private JTextField textFourthOctect;
	
	private JTextField textCidr;
	
	private JButton buttonConverter;
	private JButton buttonLimpar;
	
	private JLabel labelClassIp;
	private JLabel labelReturnCIDR;
	private JLabel labelResultMask;
	private JLabel labelResultBinary;
	

	public void criarTela(){
		Mascara mask = new Mascara();
		Ip ip = new Ip();
		Font fonte = new Font("Arial", Font.BOLD, 30);
		
		JFrame tela = new JFrame();
		tela.setSize(400, 300);
		tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tela.setTitle("Conversor de IPV4 e Máscara");
		tela.setLocationRelativeTo(null);
		tela.setLayout(null);
		tela.setResizable(false);
		
		//
		labelIp = new JLabel();
		labelIp.setText("Informe seu IP: ");
		labelIp.setBounds(50, 30, 100, 30);
		
		labelMask = new JLabel();
		labelMask.setText("/");
		labelMask.setBounds(270
				, 60, 40, 30);
		labelMask.setFont(fonte);
		
	    //Entrada de Dados
		textFirstOctect = new JTextField();
		textSecondOctect = new JTextField();
		textThirdOctect = new JTextField();
		textFourthOctect = new JTextField();
		
		textFirstOctect.setBounds(60, 60, 40, 30);
		textFirstOctect.setHorizontalAlignment(textFirstOctect.CENTER);

		textSecondOctect.setBounds(110, 60, 40, 30);
		textSecondOctect.setHorizontalAlignment(textFirstOctect.CENTER);
		
		textThirdOctect.setBounds(160, 60, 40, 30);
		textThirdOctect.setHorizontalAlignment(textFirstOctect.CENTER);

		textFourthOctect.setBounds(210, 60, 40, 30);
		textFourthOctect.setHorizontalAlignment(textFirstOctect.CENTER);

		textCidr = new JTextField();
		textCidr.setBounds(300, 60, 40, 30);
		textCidr.setHorizontalAlignment(textFirstOctect.CENTER);
		
		labelClassIp = new JLabel();
		labelClassIp.setBounds(350, 60, 40, 30);
		
		buttonConverter = new JButton();
		buttonConverter.setText("Verificar");
		buttonConverter.setBounds(50, 100, 145, 30);
		buttonConverter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Ip ip = new Ip();
				
				String txtIp = textFirstOctect.getText();
				String classificarIp = ip.classificarIp();
				
				ip.setIp(txtIp);	
				labelClassIp.setText(classificarIp);
				
			}
		});
		
		buttonLimpar = new JButton();
		buttonLimpar.setText("Limpar Dados");
		buttonLimpar.setBounds(200, 100, 145, 30);
		
		tela.getContentPane().add(labelIp);
		tela.getContentPane().add(labelMask);
		tela.getContentPane().add(textFirstOctect);
		tela.getContentPane().add(textSecondOctect);
		tela.getContentPane().add(textThirdOctect);
		tela.getContentPane().add(textFourthOctect);
		tela.getContentPane().add(textCidr);
		tela.getContentPane().add(labelClassIp);
		tela.getContentPane().add(buttonConverter);
		tela.getContentPane().add(buttonLimpar);
		
		tela.setVisible(true);
	}
}
