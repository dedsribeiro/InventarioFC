/**
 * 
 */
package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.DAOProduto;
import Model.Produto;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

/**
 * @author Andr� Ribeiro
 *
 */
public class AtualizarMP extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtQuantidade;
	private JTextField txtDescricao;
	static JComboBox<String> comboBoxAtt = new JComboBox<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AtualizarMP frame = new AtualizarMP();
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
	public AtualizarMP() {
		setLocationRelativeTo(null);
		setTitle("Atualizar M.P");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 257, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnInserirProdutoMp = new JButton("Atualizar Produto MP");
		btnInserirProdutoMp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DAOProduto daoprod = new DAOProduto();
				Produto prod = new Produto();
				prod.codigo = comboBoxAtt.getSelectedItem().toString();
				prod.descricao = txtDescricao.getText();
				if(prod.codigo.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Entre com um c�digo v�lido.", "C�digo inv�lido", JOptionPane.ERROR_MESSAGE);
				}
				else {
					try {
						prod.quantidade = Integer.parseInt(txtQuantidade.getText());
						daoprod.atualizar(prod);
						JOptionPane.showMessageDialog(null, "O Produto "+prod.codigo+" foi alterado com sucesso!");
						dispose();
						ProdutoMP.carregarTabela();
						ProdutoMP.att_combobox();
						
						
					}catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(null, "Entre com uma Quantidade v�lida!" ,"ERRO",JOptionPane.ERROR_MESSAGE);
					}
				}
		
			}
		});
		btnInserirProdutoMp.setBounds(43, 127, 157, 23);
		contentPane.add(btnInserirProdutoMp);
		
		JLabel lblNewLabel = new JLabel("C\u00F3digo:");
		lblNewLabel.setBounds(33, 23, 50, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setBounds(10, 53, 73, 14);
		contentPane.add(lblQuantidade);
		
		JLabel lblDescrio = new JLabel("  Descri\u00E7\u00E3o:");
		lblDescrio.setBounds(10, 83, 73, 14);
		contentPane.add(lblDescrio);
		
		txtQuantidade = new JTextField();
		txtQuantidade.setBounds(80, 50, 120, 20);
		contentPane.add(txtQuantidade);
		txtQuantidade.setColumns(10);
		
		txtDescricao = new JTextField();
		txtDescricao.setBounds(80, 80, 120, 20);
		contentPane.add(txtDescricao);
		txtDescricao.setColumns(10);
		

		comboBoxAtt.setBounds(80, 20, 120, 20);
		contentPane.add(comboBoxAtt);
		
		comboBoxAtt.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				DAOProduto dpt = new DAOProduto();
				try {
					txtDescricao.setText(dpt.busca_descricao(comboBoxAtt.getSelectedItem().toString()));
					txtQuantidade.setText(Integer.toString(dpt.busca_quantidade(comboBoxAtt.getSelectedItem().toString())));
					
				}
				catch(NullPointerException nexc) {
					txtDescricao.setText("Nenhum produto selecionado");
				}
			}
		});
		
		att_combobox();
	}
	public static void att_combobox() {
		DAOProduto dpt = new DAOProduto();
		comboBoxAtt.removeAllItems();
		List<Produto> resultados = dpt.buscarTodos();
		
		for (Produto pt : resultados) {
			comboBoxAtt.addItem(pt.codigo);
		}
	}
}
