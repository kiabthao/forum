package fr.afpa.formation.service.exception;

public class CommentaireNotAviableException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CommentaireNotAviableException() {
		super("Commentaire not aviable");
	}
	
	
	public CommentaireNotAviableException(String msg) {
		super(msg);
	}

}
