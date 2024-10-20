/**
 * 
 */
package View;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;

/**
 * @author André Ribeiro
 *
 */
public class TelaAdmin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAdmin frame = new TelaAdmin();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaAdmin() {
		setResizable(false);
		setTitle("Administrador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 247, 307);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton(" Produtos - M.P.");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProdutoMP tl = new ProdutoMP();
				tl.setLocationRelativeTo(null);
				tl.setVisible(true);
				ProdutoMP.carregarTabela();
				dispose();
			}
		});
		btnNewButton.setBounds(20, 11, 200, 56);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("  Produtos - P.A.");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProdutoPA tl = new ProdutoPA();
				tl.setLocationRelativeTo(null);
				tl.setVisible(true);
				ProdutoPA.carregarTabela();
				dispose();
			}
		});
		btnNewButton_1.setBounds(20, 78, 200, 56);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton(" Etiquetas");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_2.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Etiqueta tl = new Etiqueta();
				tl.setLocationRelativeTo(null);
				tl.setVisible(true);
				Etiqueta.carregarTabela();
				dispose();
			}
		});
		btnNewButton_2.setBounds(20, 145, 200, 56);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton(" Caixas Coletivas");
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_3.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Caixa tl = new Caixa();
				tl.setLocationRelativeTo(null);
				tl.setVisible(true);
				Caixa.carregarTabela();
				dispose();
			}
		});
		btnNewButton_3.setBounds(20, 212, 200, 56);
		contentPane.add(btnNewButton_3);
		
		Image img = new ImageIcon(this.getClass().getResource("/FE1201A.png")).getImage();
		btnNewButton.setIcon(new ImageIcon(img));
		contentPane.add(btnNewButton);
		
		Image img1 = new ImageIcon(this.getClass().getResource("/produtoacabado.png")).getImage();
		btnNewButton_1.setIcon(new ImageIcon(img1));
		contentPane.add(btnNewButton_1);
		
		Image img2 = new ImageIcon(this.getClass().getResource("/etiqueta.jpeg")).getImage();
		btnNewButton_2.setIcon(new ImageIcon(img2));
		contentPane.add(btnNewButton_2);
		

		Image img3 = new ImageIcon(this.getClass().getResource("/caixa.png")).getImage();
		btnNewButton_3.setIcon(new ImageIcon(img3));
		contentPane.add(btnNewButton_3);
	}
}
