package fr.afpa.formation.service.exception;

public class SujetContentAllreadyExistsException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public SujetContentAllreadyExistsException() {
		super("SujetContent Allready Exist");
		
	}

}
