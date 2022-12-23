package com.company;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 *
 * Class Injector implements dependency injection into any object that contains fields
 * marked with an annotation @AutoInjectable
 */
public class Injector<T>{
    /**
     * Reference to the configuration object
     */
    private final Properties properties;

    /**
     * Class constructor
     * @param pathToPropertiesFile The path to the file.
     * @throws IOException An exception that is thrown when an I/O error occurs.
     */
    Injector(String pathToPropertiesFile) throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream(new File(pathToPropertiesFile)));
    }

    /**
     * Inject accepts an arbitrary object, examines it for the presence of fields with an AutoInjectable annotation.
     * If there is such a field, look at its type and look for an implementation in the ini.properties file.
     * @param obj An object of any class
     * @return An object with initialized fields with an Auto Injectable annotation
     */
    T inject(T obj) throws IOException, IllegalAccessException, InstantiationException {

        Class dependency;
        Class cl = obj.getClass();

        Field[] fields = cl.getDeclaredFields();
        for (Field field: fields){

            Annotation a = field.getAnnotation(AutoInjectable.class);
            if (a != null){

                String[] fieldType = field.getType().toString().split(" ");
                String equalsClassName = properties.getProperty(fieldType[1], null);

                if (equalsClassName != null){

                    try {
                        dependency = Class.forName(equalsClassName);

                    } catch (ClassNotFoundException e){
                        System.out.println("Not found class for " + equalsClassName);
                        continue;
                    }

                    field.setAccessible(true);
                    field.set(obj, dependency.newInstance());
                }
                else
                    System.out.println("Not found properties for field type " + fieldType[1]);
            }
        }
        return obj;
    }
}
