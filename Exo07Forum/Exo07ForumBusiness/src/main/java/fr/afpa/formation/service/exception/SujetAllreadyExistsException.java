package fr.afpa.formation.service.exception;

public class SujetAllreadyExistsException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public SujetAllreadyExistsException() {
		super("Sujet Allready Exist");
		
	}

}
