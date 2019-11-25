package com.qlive.api.util;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class KeyGenerator implements IdentifierGenerator {

    private final String ALPHA_NUMERIC_SET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /**
     * Generates a random String of length count
     *
     * @param count     Length of random string to be generated
     * @return          Random alphonumeric string from the set
     */
    private String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();

        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_SET.length());
            builder.append(ALPHA_NUMERIC_SET.charAt(character));
        }

        return builder.toString();
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o)
            throws HibernateException {
        return randomAlphaNumeric(6);
    }
}
