package com.daniloaraujosilva.file_parser.model.helper;


/**
 * 
 */
public class Cloner {
	
	/**
	 * 
	 */
	private static final com.rits.cloning.Cloner INSTANCE = new com.rits.cloning.Cloner();
	
	/**
	 * 
	 * @return
	 */
	public static com.rits.cloning.Cloner getInstance() {
		return INSTANCE;
	}
}