package View;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.DAOLogin;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtLogin;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					frame.setTitle("Login - Fonte CFTV");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(10, 258, 46, 14);
		contentPane.add(lblLogin);
		
		JLabel lblSenha = new JLabel("Senha:   ");
		lblSenha.setBounds(10, 283, 54, 14);
		contentPane.add(lblSenha);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(54, 255, 115, 20);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(54, 280, 115, 20);
		contentPane.add(txtSenha);
		
		JLabel lblNewLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/newlogo-FC.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		
		lblNewLabel.setBounds(10, 11, 230, 207);
		contentPane.add(lblNewLabel);
		
		JButton btnAcessar = new JButton("Acessar");
		btnAcessar.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				
				DAOLogin cl = new DAOLogin();
				cl.AcessoLogin(txtLogin.getText(), txtSenha.getText());
				if(cl.acesso == 1) {
					TelaUsuario tl = new TelaUsuario();
					tl.setLocationRelativeTo(null);
					tl.setVisible(true);;
					dispose();
				}
				if(cl.acesso == 2) {
					TelaAdmin tl = new TelaAdmin();
					tl.setLocationRelativeTo(null);
					tl.setVisible(true);;
					dispose();
				}
				if(cl.acesso == 0) {
					txtLogin.setText("");
					txtSenha.setText("");
					txtLogin.requestFocus();
				}
				
				cl.acesso = 0;
				
			}
		});
		Image img1 = new ImageIcon(this.getClass().getResource("/ok.png")).getImage();
		btnAcessar.setIcon(new ImageIcon(img1));
		btnAcessar.setBounds(215, 279, 107, 23);
		contentPane.add(btnAcessar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
				
			}
		});
		Image img2 = new ImageIcon(this.getClass().getResource("/error.png")).getImage();
		btnCancelar.setIcon(new ImageIcon(img2));
		btnCancelar.setBounds(332, 279, 107, 23);
		contentPane.add(btnCancelar);
	}
}
