package fr.splExceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SplException extends Exception{
	/**
	 *
	 */
	private static final Logger LOG = LogManager.getLogger();
	private static final long serialVersionUID = -5601149107058379833L;
	public SplException(){
		super();
		SplException.LOG.debug("pas de message Passé");
	}
	public SplException(String message){
		super(message);
		SplException.LOG.debug(message);
	}
}
