package com.malay.emr.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

@Service
public class BlockchainService {


	public static final Logger LOGGER = Logger.getLogger(BlockchainService.class.getName());

	public BlockchainService() throws Exception {
	}

	public BigInteger checksum(Object obj) throws IOException, NoSuchAlgorithmException {

	    if (obj == null) {
	      return BigInteger.ZERO;   
	    }

	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    ObjectOutputStream oos = new ObjectOutputStream(baos);
	    oos.writeObject(obj);
	    oos.close();

	    MessageDigest m = MessageDigest.getInstance("SHA1");
	    m.update(baos.toByteArray());

	    return new BigInteger(1, m.digest());
	}

}
