/**
 * 
 */
package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.DAOEtiqueta;
import Model.Produto;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author André Ribeiro
 *
 */
public class InserirEtiqueta extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtQuantidade;
	private JTextField txtDescricao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InserirEtiqueta frame = new InserirEtiqueta();
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
	public InserirEtiqueta() {
		setLocationRelativeTo(null);
		setTitle("Inserir Etiqueta");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 288, 185);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnInserirProdutoMp = new JButton("Inserir Etiqueta");
		btnInserirProdutoMp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DAOEtiqueta daoprod = new DAOEtiqueta();
				Produto prod = new Produto();
				prod.codigo = txtCodigo.getText();;
				prod.descricao = txtDescricao.getText();
				if(prod.codigo.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Entre com um código válido.", "Código inválido", JOptionPane.ERROR_MESSAGE);
				}
				else {
					try {
						prod.quantidade = Integer.parseInt(txtQuantidade.getText());
						daoprod.insere(prod);
						JOptionPane.showMessageDialog(null, "A Etiqueta "+prod.codigo+" foi inserida com sucesso!");
						dispose();
						Etiqueta.carregarTabela();
						Etiqueta.att_combobox();
						
						
					}catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(null, "Entre com uma Quantidade válida!" ,"ERRO",JOptionPane.ERROR_MESSAGE);
					}
				}
		
			}
		});
		btnInserirProdutoMp.setBounds(49, 109, 157, 23);
		contentPane.add(btnInserirProdutoMp);
		
		JLabel lblNewLabel = new JLabel("C\u00F3digo:");
		lblNewLabel.setBounds(70, 23, 42, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblQuantidade = new JLabel("  Quantidade:");
		lblQuantidade.setBounds(39, 53, 85, 14);
		contentPane.add(lblQuantidade);
		
		JLabel lblDescrio = new JLabel("    Descri\u00E7\u00E3o:");
		lblDescrio.setBounds(39, 81, 73, 14);
		contentPane.add(lblDescrio);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(115, 20, 140, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtQuantidade = new JTextField();
		txtQuantidade.setBounds(115, 50, 140, 20);
		contentPane.add(txtQuantidade);
		txtQuantidade.setColumns(10);
		
		txtDescricao = new JTextField();
		txtDescricao.setBounds(115, 78, 140, 20);
		contentPane.add(txtDescricao);
		txtDescricao.setColumns(10);
	}
}
