package com.notebook.annotations_test;


import com.notebook.annotations_test.annotations.Animal;
import org.springframework.core.env.Environment;

import java.io.File;

/**
 * 注解
 * @author wudi
 */
public class Zoo {
    private Environment environment;

    public static void main(String[] args) {

        File file = new File("/Users/wudi/Documents/code/notebook/src/main/java/com/notebook/annotations_test/animal");
        File[] files = file.listFiles();
        assert files != null;
        for (File tempFile : files) {
            String name = tempFile.getName();
            System.out.println(name);
            Class<?> aClass = null;
            try {
                aClass = Class.forName("com.notebook.annotations_test.animal." + name.replace(".java", ""));
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
            assert aClass != null;
            boolean present = aClass.isAnnotationPresent(Animal.class);
            if (present) {
                Animal animal = aClass.getAnnotation(Animal.class);
                System.out.println(animal.name());
            }
        }

    }

}
