package org.cyberpro.atm.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lmichelson
 *
 */
@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		log.info("+---------------------------------------------+");
		log.info("+ Starting up ATM server");
		log.info("+---------------------------------------------+");

		SpringApplication.run(Application.class, args);
	}

}