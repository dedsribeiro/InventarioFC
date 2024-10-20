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

import Controller.DAOCaixa;
import Model.Produto;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;
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
public class Caixa extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static DefaultTableModel modelo = new DefaultTableModel(); 
	static JComboBox<String> comboBox = new JComboBox<String>();
	private JTextField txtQuantidade;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Caixa frame = new Caixa();
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
	public Caixa() {
		setTitle("Caixa Coletiva");
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
		
		comboBox.setBounds(367, 124, 162, 23);
		contentPane.add(comboBox);
		
		txtQuantidade = new JTextField();
		txtQuantidade.setBounds(367, 158, 162, 20);
		contentPane.add(txtQuantidade);
		txtQuantidade.setColumns(10);
		
		JButton btnAdiciona = new JButton("+");
		btnAdiciona.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAdiciona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int resultado = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja adicionar "+txtQuantidade.getText()+
					" caixas no estoque de "+comboBox.getSelectedItem().toString()+" ?");
					if(resultado == JOptionPane.YES_OPTION){
						DAOCaixa dpt = new DAOCaixa();
						int temp = dpt.busca_quantidade(comboBox.getSelectedItem().toString());
						int att = Integer.parseInt(txtQuantidade.getText()) + temp;
						dpt.att_quantidade(comboBox.getSelectedItem().toString(), att);
						carregarTabela();
					}
					txtQuantidade.setText("");
					txtQuantidade.requestFocus();
				}catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(null, "Entre com um número válido!" ,"ERRO",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAdiciona.setBounds(549, 137, 42, 42);
		contentPane.add(btnAdiciona);
		
		JButton btnSubtrai = new JButton("-");
		btnSubtrai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int resultado = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja subtrair "+txtQuantidade.getText()+
					" caixas no estoque de "+comboBox.getSelectedItem().toString()+" ?");
					if(resultado == JOptionPane.YES_OPTION){
						DAOCaixa dpt = new DAOCaixa();
						int temp = dpt.busca_quantidade(comboBox.getSelectedItem().toString());
						int att = temp - Integer.parseInt(txtQuantidade.getText());
						dpt.att_quantidade(comboBox.getSelectedItem().toString(), att);
						carregarTabela();
					}
					txtQuantidade.setText("");
					txtQuantidade.requestFocus();
				}catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(null, "Entre com um número válido!" ,"ERRO",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSubtrai.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSubtrai.setBounds(601, 137, 42, 42);
		contentPane.add(btnSubtrai);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 684, 21);
		contentPane.add(menuBar);
		
		JMenu mnAdministrarProdutos = new JMenu("Administrar Caixas");
		menuBar.add(mnAdministrarProdutos);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Pesquisar");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ComboxBoxShowInputDialogPesquisa();
			}
		});
		mnAdministrarProdutos.add(mntmNewMenuItem);
		
		JMenuItem mntmInserir = new JMenuItem("Inserir");
		mntmInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				InserirCaixa ins = new InserirCaixa();
				ins.setVisible(true);
				ins.setLocationRelativeTo(null);
			}
		});
		mnAdministrarProdutos.add(mntmInserir);
		
		JMenuItem mntmAtualizar = new JMenuItem("Atualizar");
		mntmAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AtualizarCaixa att = new AtualizarCaixa();
				att.setVisible(true);
				att.setLocationRelativeTo(null);
			}
		});
		mnAdministrarProdutos.add(mntmAtualizar);
		
		JMenuItem mntmDeletar = new JMenuItem("Deletar");
		mntmDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ComboxBoxShowInputDialogDelete();
			}
		});
		mnAdministrarProdutos.add(mntmDeletar);
		
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
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblQuantidade.setBounds(287, 160, 70, 14);
		contentPane.add(lblQuantidade);
		
		JButton btnNewButton = new JButton("Tela de \u00EDtens");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelo.setColumnCount(0);
				TelaAdmin tl = new TelaAdmin();
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
				DAOCaixa dpt = new DAOCaixa();
				try {
					label.setText(dpt.busca_descricao(comboBox.getSelectedItem().toString()));
					lblNewLabel.setText("Quantidade em estoque: "+Integer.toString(dpt.busca_quantidade(comboBox.getSelectedItem().toString()))+" caixas");
					
				}
				catch(NullPointerException nexc) {
					label.setText("Nenhuma Caixa selecionada");
				}
			}
		});
		
		att_combobox();	
	}
	
	public static void carregarTabela() {
		
		modelo.setNumRows(0);
		DAOCaixa dpt = new DAOCaixa();
		List<Produto> resultados = dpt.buscarTodos();
		
		for (Produto pt : resultados) {
			modelo.addRow(new Object[]{pt.codigo,pt.quantidade,pt.descricao});	
		}
	}
	
	public static void att_combobox() {
		DAOCaixa dpt = new DAOCaixa();
		comboBox.removeAllItems();
		List<Produto> resultados = dpt.buscarTodos();
		
		for (Produto pt : resultados) {
			comboBox.addItem(pt.codigo);
		}
	}
	
	public void ComboxBoxShowInputDialogDelete()
	{			
		DAOCaixa dptpa = new DAOCaixa();
		List<String> resultado = dptpa.buscarTodosCodigos();
		Object[] resultados = resultado.toArray();
		
	    JFrame frame = new JFrame("Deleção de caixa");
	    String delItem = (String) JOptionPane.showInputDialog(frame, 
	        "Qual caixa deseja deletar?",
	        "Deletar Caixa",
	        JOptionPane.QUESTION_MESSAGE, 
	        null, 
	        resultados, 
	        resultados[0]);
	    	if(delItem != null) {
	    		int resp = JOptionPane.showConfirmDialog(null, "A Caixa será deletada!\nConfirma a deleção do item selecionado?");	
	    		if(resp == JOptionPane.YES_OPTION){
					dptpa.apagar(delItem);
					att_combobox();
					carregarTabela();	
				}
	    	}
	  }
	public void ComboxBoxShowInputDialogPesquisa()
	{		
		DAOCaixa dpt = new DAOCaixa();
		List<String> resultado = dpt.buscarTodosCodigos();
		Object[] resultados = resultado.toArray();
		
	    JFrame frame = new JFrame("Pesquisa de caixa");
	    String delItem = (String) JOptionPane.showInputDialog(frame, 
	        "Qual caixa deseja pesquisar?",
	        "Pesquisar Caixa",
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
