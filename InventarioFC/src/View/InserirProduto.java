/**
 * 
 */
package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.DAOProduto;
import Controller.DAOProdutoAcabado;
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
public class InserirProduto extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtQuantidade;
	private JTextField txtDescricao;
	private JTextField txtQuantidadePA;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InserirProduto frame = new InserirProduto();
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
	public InserirProduto() {
		setLocationRelativeTo(null);
		setTitle("Inserir Produto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 288, 220);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnInserirProdutoMp = new JButton("Inserir Produto");
		btnInserirProdutoMp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DAOProduto daoprod = new DAOProduto();
				DAOProdutoAcabado daoprodpa = new DAOProdutoAcabado();
				Produto prod = new Produto();
				Produto prodpa = new Produto();
				prod.codigo = txtCodigo.getText();
				prodpa.codigo = txtCodigo.getText();
				prod.descricao = txtDescricao.getText();
				prodpa.descricao = txtDescricao.getText();
				if(prod.codigo.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Entre com um código válido.", "Código inválido", JOptionPane.ERROR_MESSAGE);
				}
				else {
					try {
						prod.quantidade = Integer.parseInt(txtQuantidade.getText());
						prodpa.quantidade = Integer.parseInt(txtQuantidadePA.getText());
						daoprod.insere(prod);
						daoprodpa.insere(prodpa);
						JOptionPane.showMessageDialog(null, "O Produto "+prod.codigo+" foi inserido com sucesso!");
						dispose();
						ProdutoMP.carregarTabela();
						ProdutoMP.att_combobox();
						
						
					}catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(null, "Entre com uma Quantidade válida!" ,"ERRO",JOptionPane.ERROR_MESSAGE);
					}
				}
		
			}
		});
		btnInserirProdutoMp.setBounds(54, 142, 157, 23);
		contentPane.add(btnInserirProdutoMp);
		
		JLabel lblNewLabel = new JLabel("C\u00F3digo:");
		lblNewLabel.setBounds(70, 23, 42, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblQuantidade = new JLabel(" Quantidade em MP:");
		lblQuantidade.setBounds(0, 53, 120, 14);
		contentPane.add(lblQuantidade);
		
		JLabel lblDescrio = new JLabel("    Descri\u00E7\u00E3o:");
		lblDescrio.setBounds(39, 114, 73, 14);
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
		txtDescricao.setBounds(115, 111, 140, 20);
		contentPane.add(txtDescricao);
		txtDescricao.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel(" Quantidade em PA:");
		lblNewLabel_1.setBounds(0, 83, 120, 14);
		contentPane.add(lblNewLabel_1);
		
		txtQuantidadePA = new JTextField();
		txtQuantidadePA.setBounds(115, 80, 140, 20);
		contentPane.add(txtQuantidadePA);
		txtQuantidadePA.setColumns(10);
	}
}
