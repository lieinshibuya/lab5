package com.company;
import reflection.SomeInterface;
import reflection.SomeOtherInterface;

/**
 * A class containing two annotations
 */
public class SomeBean {

    @AutoInjectable
    private SomeInterface someField;
    @AutoInjectable
    private SomeOtherInterface otherField;

    /**
     * Default Class constructor
     */
    public SomeBean() {}

    /**
     * A method that calls interface methods from fields with annotations.
     */
    public void go(){
        someField.doSome();
        otherField.doSome();
    }
}