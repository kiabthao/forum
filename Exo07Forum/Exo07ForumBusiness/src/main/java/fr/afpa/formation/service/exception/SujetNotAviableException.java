package fr.afpa.formation.service.exception;

public class SujetNotAviableException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SujetNotAviableException() {
		super("Sujet not aviable");
	}
	
	
	public SujetNotAviableException(String msg) {
		super(msg);
	}

}
