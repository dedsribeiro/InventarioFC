/**
 * 
 */
package Controller;

/**
 * @author André Ribeiro
 *
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;  
  
import javax.swing.JOptionPane;

import Model.Produto; 
    
  
public class DAOEtiqueta {  
   // Configura essas variáveis de acordo com o seu banco  
   private final String URL = "jdbc:mysql://186.202.152.152/inventariofc?useSSL=false",  
         NOME = "inventariofc", SENHA = "wm792567";  
  
   private Connection con;  
   private Statement comando;  
  
   public void apagar(String codigo) {  
      conectar();  
      try {  
         comando  
               .executeUpdate("DELETE FROM etiqueta WHERE codigo = '" + codigo  
                     + "';");  
      } catch (SQLException e) {  
         imprimeErro("Erro ao apagar!", e.getMessage());  
      } finally {  
         fechar();  
      }  
   }  
  
   public List<Produto> buscarTodos() {  
      conectar();  
      List<Produto> resultados = new ArrayList<Produto>();  
      ResultSet rs;  
      try {  
         rs = comando.executeQuery("SELECT * FROM etiqueta order by codigo");  
         while (rs.next()) {  
            Produto temp = new Produto();  
            // pega todos os atributos  
            temp.setCodigo(rs.getString("codigo"));  
            temp.setQuantidade(rs.getInt("quantidade"));  
            temp.setDescricao(rs.getString("descricao"));    
            resultados.add(temp);  
         }  
         return resultados;  
      } catch (SQLException e) {  
         imprimeErro("Não encontrado!", e.getMessage());  
         return null;  
      }  
   }
   
   public List<String> buscarTodosCodigos() {  
	      conectar();  
	      List<String> resultados = new ArrayList<String>();  
	      ResultSet rs;  
	      try {  
	         rs = comando.executeQuery("SELECT * FROM etiqueta order by codigo");  
	         while (rs.next()) {  
	            String temp;  
	            // pega todos os atributos  
	            temp = (rs.getString("codigo"));     
	            resultados.add(temp);  
	         }  
	         return resultados;  
	      } catch (SQLException e) {  
	         imprimeErro("Não encontrado!", e.getMessage());  
	         return null;  
	      }  
	   } 
  
   public void atualizar(Produto etiqueta) {  
      conectar();  
      String com = "UPDATE etiqueta SET codigo = '" + etiqueta.getCodigo()  
            + "', quantidade =" + etiqueta.getQuantidade() + ", descricao = '"  
            + etiqueta.getDescricao() 
            + "' WHERE  codigo = '" + etiqueta.getCodigo() + "';";  
      //System.out.println("Atualizado!");  
      try {  
         comando.executeUpdate(com);  
      } catch (SQLException e) {  
         e.printStackTrace();  
      } finally {  
         fechar();  
      }  
   }  
  
   public Vector<Produto> buscar(String codigo) {  
      conectar();  
      Vector<Produto> resultados = new Vector<Produto>();  
      ResultSet rs;  
      try {  
         rs = comando.executeQuery("SELECT * FROM etiqueta WHERE codigo LIKE '"  
               + codigo + "%';");  
         while (rs.next()) {  
            Produto temp = new Produto();  
            // pega todos os atributos da pessoa  
            temp.setCodigo(rs.getString("codigo"));  
            temp.setDescricao(rs.getString("descricao"));  
            temp.setQuantidade(rs.getInt("quantidade"));  
  
            JOptionPane.showMessageDialog(null, "Código: "+rs.getString("codigo")+"\nQuantidade: "+rs.getInt("quantidade")+"\nDescrição: "+rs.getString("descricao"),"Informações sobre "+rs.getString("codigo"), JOptionPane.INFORMATION_MESSAGE);
            resultados.add(temp);  
         }  
         return resultados;  
      } catch (SQLException e) {  
         imprimeErro("Erro ao buscar!", e.getMessage());  
         return null;  
      }  
  
   }  
  
   public void insere(Produto etiqueta){  
	      conectar();  
	      try {  
	         comando.executeUpdate("INSERT INTO etiqueta VALUES('"  
	               + etiqueta.getCodigo() + "','" 
	               + etiqueta.getQuantidade() + "','"  
	               + etiqueta.getDescricao() + "')"); 
	         //System.out.println("Inserido!");  
	      } catch (SQLException e) {  
	         imprimeErro("Erro ao inserir!", e.getMessage());  
	      } finally {  
	         fechar();  
	      }  
	   } 
  
   private void conectar() {  
      try {  
    	 Class.forName("com.mysql.jdbc.Driver");
         con = DriverManager.getConnection(URL, NOME, SENHA);
         comando = con.createStatement();  
         //System.out.println("Conectado!");  
      }catch(ClassNotFoundException | SQLException e){ 
         imprimeErro("Erro ao carregar o driver", e.getMessage());  
      }  
   }  
  
   private void fechar() {  
      try {  
         comando.close();  
         con.close();  
         //System.out.println("Conexão Fechada");  
      } catch (SQLException e) {  
         imprimeErro("Erro ao fechar conexão", e.getMessage());  
      }  
   }  
  
   private void imprimeErro(String msg, String msgErro) {  
      JOptionPane.showMessageDialog(null, msg, "Erro crítico", 0);  
      System.err.println(msg);  
      System.out.println(msgErro);  
      System.exit(0);  
   }  

	public void att_quantidade(String codigo, int quantidade) {  
	    conectar();  
	    String com = "UPDATE etiqueta SET quantidade =" + quantidade + " WHERE  codigo = '"+codigo+"';";  
	    //System.out.println("Atualizado!");  
	    try {  
	       comando.executeUpdate(com);  
	    } catch (SQLException e) {  
	       e.printStackTrace();  
	    } finally {  
	       fechar();  
	    }  
	 }

	public int busca_quantidade(String codigo) {
		int quantidade = 0;
		ResultSet rs;
		conectar();
		String com = "SELECT quantidade FROM etiqueta WHERE  codigo = '" + codigo + "';";    
		//System.out.println("Selecionado!");
		try {  
		       rs = comando.executeQuery(com);
		       while (rs.next()) {  
		            quantidade = rs.getInt("quantidade");  
		         }  
		          
		    } catch (SQLException e) {  
		       e.printStackTrace();  
		    } finally {  
		       fechar();  
		    }
		return quantidade;
	}
	public String busca_descricao(String codigo) {
		String descricao = null;
		ResultSet rs;
		conectar();
		String com = "SELECT descricao FROM etiqueta WHERE  codigo = '" + codigo + "';";    
		//System.out.println("Selecionado!");
		try {  
		       rs = comando.executeQuery(com);
		       while (rs.next()) {  
		            descricao = rs.getString("descricao");  
		         }  
		          
		    } catch (SQLException e) {  
		       e.printStackTrace();  
		    } finally {  
		       fechar();  
		    }
		return descricao;
	}
}

