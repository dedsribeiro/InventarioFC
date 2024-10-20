/**
 * 
 */
package Model;

/**
 * @author André Ribeiro
 *
 */
public class Imagem {

	private int idImagem;
	private byte[] imagem;
	
	/**
	 * @return the idImagem
	 */
	public int getIdImagem() {
		return idImagem;
	}
	/**
	 * @param idImagem the idImagem to set
	 */
	public void setIdImagem(int idImagem) {
		this.idImagem = idImagem;
	}
	/**
	 * @return the imagem
	 */
	public byte[] getImagem() {
		return imagem;
	}
	/**
	 * @param imagem the imagem to set
	 */
	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}
}
