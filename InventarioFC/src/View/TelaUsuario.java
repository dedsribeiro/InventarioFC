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
public class TelaUsuario extends JFrame {

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
					TelaUsuario frame = new TelaUsuario();
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
	public TelaUsuario() {
		setResizable(false);
		setTitle("Vendedor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 247, 173);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton(" Produtos - M.P.");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProdutoMPVendedor tl = new ProdutoMPVendedor();
				tl.setLocationRelativeTo(null);
				tl.setVisible(true);
				ProdutoMPVendedor.carregarTabela();
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
				ProdutoPAVendedor tl = new ProdutoPAVendedor();
				tl.setLocationRelativeTo(null);
				tl.setVisible(true);
				ProdutoPAVendedor.carregarTabela();
				dispose();
			}
		});
		btnNewButton_1.setBounds(20, 78, 200, 56);
		contentPane.add(btnNewButton_1);
		
		Image img = new ImageIcon(this.getClass().getResource("/FE1201A.png")).getImage();
		btnNewButton.setIcon(new ImageIcon(img));
		contentPane.add(btnNewButton);
		
		Image img1 = new ImageIcon(this.getClass().getResource("/produtoacabado.png")).getImage();
		btnNewButton_1.setIcon(new ImageIcon(img1));
		contentPane.add(btnNewButton_1);
		
	}
}
