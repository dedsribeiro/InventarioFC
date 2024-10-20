/**
 * 
 */
package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

/**
 * @author André Ribeiro
 *
 */
public class DAOLogin {

		public int acesso;
		
		public void AcessoLogin(String login, String senha){
			Connection con  = null;
			Statement consulta = null;
			ResultSet tabela  = null;
			
			try{
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://186.202.152.152/inventariofc?useSSL=false", "inventariofc", "wm792567");
				consulta = (Statement) con.createStatement();
				tabela = consulta.executeQuery("select loginUsuario, senhaUsuario, tipoUsuario from usuario where loginUsuario='"+login+"'and senhaUsuario='"+senha+"'");
				
				if(tabela.next()){
					if(tabela.getInt(3)==1) {
						JOptionPane.showMessageDialog(null, "Usuário conectado\nPermissão: Vendedor");
						acesso = 1;
					}
					else if(tabela.getInt(3)==2) {
						JOptionPane.showMessageDialog(null, "Usuário conectado\nPermissão: Administrador");
						acesso = 2;
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Usuário ou Senha INCORRETOS!");
					acesso = 0;;
				}
			}catch(ClassNotFoundException | SQLException e){
			}
		}
}
