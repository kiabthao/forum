package fr.afpa.formation.service.exception;

public class CommentaireAllreadyExistsException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public CommentaireAllreadyExistsException() {
		super("Commentaire Allready Exist");
		
	}

}
