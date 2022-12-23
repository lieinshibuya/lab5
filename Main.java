package com.company;
import java.io.IOException;

/**
 * The Main class
 */
public class Main {
    /**
     * The Main method
     * @param args The Main arguments
     * @throws IOException An exception that is thrown when an I/O error occurs.
     * @throws InstantiationException Instance creation exception
     * @throws IllegalAccessException Exception to instantiation without access to the definition of the specified class, field, method, or constructor.
     */
    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException {

            SomeBean sb = (new Injector<SomeBean>("src/config/inj.properties").inject(new SomeBean()));
            sb.go();
    }
}