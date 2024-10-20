/**
 * 
 */
package View;

import java.awt.EventQueue;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Controller.DAOProdutoAcabado;
import Model.Produto;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Color;

/**
 * @author André Ribeiro
 *
 */
public class ProdutoPAVendedor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static DefaultTableModel modelo = new DefaultTableModel(); 
	static JComboBox<String> comboBox = new JComboBox<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProdutoPAVendedor frame = new ProdutoPAVendedor();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					carregarTabela();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ProdutoPAVendedor() {
		setTitle("Produto - Produto Acabado");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 257, 684, 304);
		contentPane.add(scrollPane);
		
		JTable table = new JTable(modelo);
		modelo.addColumn("Código");
		modelo.addColumn("Quantidade");
		modelo.addColumn("Descrição");
		table.getTableHeader().setReorderingAllowed(false); 
		RowSorter<TableModel> sorter =
	             new TableRowSorter<TableModel>(modelo);
	           table.setRowSorter(sorter);
		table.getColumnModel().getColumn(2).setPreferredWidth(420);
		scrollPane.setViewportView(table);
		
		JButton btnCarregar = new JButton("Atualizar Tabela");
		btnCarregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				att_combobox();
				carregarTabela();	
			}
		});
		btnCarregar.setBounds(10, 223, 148, 23);
		contentPane.add(btnCarregar);
		
		comboBox.setBounds(367, 124, 183, 23);
		contentPane.add(comboBox);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 684, 21);
		contentPane.add(menuBar);
		
		JMenu mnAdministrarProdutos = new JMenu("Administrar Produtos");
		menuBar.add(mnAdministrarProdutos);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Pesquisar");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ComboxBoxShowInputDialogPesquisa();
			}
		});
		mnAdministrarProdutos.add(mntmNewMenuItem);
		
		JMenu mnConfiguraes = new JMenu("Configura\u00E7\u00F5es");
		menuBar.add(mnConfiguraes);
		
		JMenuItem mntmSobre = new JMenuItem("Sobre");
		mntmSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String about = "Desenvolvedor: André Ribeiro\n";
				about += "Empresa: Fonte CFTV\n";
				about += "Versão do software: v1.0\n";
				about += "Software para gerenciamento de inventário";
				JOptionPane.showMessageDialog(null, about, "Sobre", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnConfiguraes.add(mntmSobre);
		
		JLabel labelLogo = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/newlogo-FC.png")).getImage();
		labelLogo.setIcon(new ImageIcon(img));
		labelLogo.setBounds(0, 21, 230, 197);
		contentPane.add(labelLogo);
		
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblModelo.setBounds(311, 120, 46, 29);
		contentPane.add(lblModelo);
		
		JButton btnNewButton = new JButton("Tela de \u00EDtens");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelo.setColumnCount(0);
				TelaUsuario tl = new TelaUsuario();
				tl.setLocationRelativeTo(null);
				tl.setVisible(true);
				dispose();
				
			}
		});
		btnNewButton.setBounds(168, 223, 148, 23);
		contentPane.add(btnNewButton);
		
		JLabel label = new JLabel("");
		label.setForeground(new Color(255, 69, 0));
		label.setFont(new Font("Tahoma", Font.BOLD, 20));
		label.setBounds(240, 32, 444, 45);
		contentPane.add(label);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(new Color(255, 69, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(240, 61, 445, 45);
		contentPane.add(lblNewLabel);
		
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				DAOProdutoAcabado dpt = new DAOProdutoAcabado();
				try {
					label.setText(dpt.busca_descricao(comboBox.getSelectedItem().toString()));
					lblNewLabel.setText("Quantidade em estoque: "+Integer.toString(dpt.busca_quantidade(comboBox.getSelectedItem().toString()))+" peças");
					
				}
				catch(NullPointerException nexc) {
					label.setText("Nenhum produto selecionado");
				}
			}
		});
		
		JButton btnNewButton_1 = new JButton("Pesquisar Produto P.A.");
		btnNewButton_1.setBounds(367, 158, 183, 29);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAOProdutoAcabado dpt = new DAOProdutoAcabado();
				dpt.buscar(comboBox.getSelectedItem().toString());
			}
		});
		
		att_combobox();	
	}
	
	public static void carregarTabela() {
		
		modelo.setNumRows(0);
		DAOProdutoAcabado dpt = new DAOProdutoAcabado();
		List<Produto> resultados = dpt.buscarTodos();
		
		for (Produto pt : resultados) {
			modelo.addRow(new Object[]{pt.codigo,pt.quantidade,pt.descricao});	
		}
	}
	
	public static void att_combobox() {
		DAOProdutoAcabado dpt = new DAOProdutoAcabado();
		comboBox.removeAllItems();
		List<Produto> resultados = dpt.buscarTodos();
		
		for (Produto pt : resultados) {
			comboBox.addItem(pt.codigo);
		}
	}
	public void ComboxBoxShowInputDialogPesquisa()
	{		
		DAOProdutoAcabado dpt = new DAOProdutoAcabado();
		List<String> resultado = dpt.buscarTodosCodigos();
		Object[] resultados = resultado.toArray();
		
	    JFrame frame = new JFrame("Pesquisa de produto");
	    String delItem = (String) JOptionPane.showInputDialog(frame, 
	        "Qual produto deseja pesquisar?",
	        "Pesquisar Produto",
	        JOptionPane.QUESTION_MESSAGE, 
	        null, 
	        resultados, 
	        resultados[0]);
	    	if(delItem != null) {
	    		dpt.buscar(delItem);
				att_combobox();
				carregarTabela();	
			}
	  }
}
