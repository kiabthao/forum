package fr.afpa.formation.service.exception;

public class SujetContentNotAviableException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SujetContentNotAviableException() {
		super("SujetContent not aviable");
	}
	
	
	public SujetContentNotAviableException(String msg) {
		super(msg);
	}

}
