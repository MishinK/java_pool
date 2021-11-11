package edu.school21.app;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringJoiner;
import java.lang.reflect.*;


public class Program {
	private static Object getInputArg(String typeName, String message) {
        System.out.println(message);
        System.out.print("-> ");
        try {
            if (typeName.equals("String"))
                return new Scanner(System.in).nextLine();
			if (typeName.equals("Integer") || typeName.equals("int"))
                return new Scanner(System.in).nextInt();
            if (typeName.equals("Double") || typeName.equals("double"))
                return new Scanner(System.in).nextDouble();
            if (typeName.equals("Boolean") || typeName.equals("boolean"))
                return new Scanner(System.in).nextBoolean();
            if (typeName.equals("Long") || typeName.equals("long"))
                return new Scanner(System.in).nextLong();
        } catch (InputMismatchException e) {
             return null;
        }
        return null;
    }

    public static void printSummary(Class ref_class) {
        System.out.println("fields:");
        {
            for (Field field : ref_class.getDeclaredFields()) {
                System.out.println("\t" + field.getType().getSimpleName() + " " + field.getName());
            }
        }
        System.out.println("methods:");
        {
            for (Method method : ref_class.getDeclaredMethods()) {
                if (method.getName().equals("toString"))
                    continue ;
                System.out.print("\t" + method.getReturnType().getSimpleName() + " " + method.getName() + "(");
                StringJoiner sj = new StringJoiner(", ");
                for (Parameter parameter : method.getParameters())
                    sj.add(parameter.getType().getSimpleName());
                System.out.print(sj);
                System.out.println(")");
            }
        }
    }

    public static Constructor getConstructor(Class ref_class) {
        for (Constructor constructor : ref_class.getDeclaredConstructors()) {
            if (constructor.getParameters().length > 0)
                return constructor;
        }
        return ref_class.getDeclaredConstructors()[0];
    }

    public static Method getMethod(Method [] methods, String methodName) {
        for (Method method : methods) {
            if (method.getName().equals(methodName))
                return method;
        }
        return null;
    }
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

		File mainPath = new File("./src/main/java/edu/school21/classes");
		if(!mainPath.exists() || !mainPath.isDirectory()) {
            System.err.println("error: bad path");
            System.exit(-1);
        }
		System.out.println("---------------------");
		System.out.println("Classes:");
		File[] children = mainPath.listFiles();
        for (File file : children) {
                String f = file.getName();
				String name = f.substring(0, f.lastIndexOf("."));
				System.out.println(name);
        }
		System.out.println("---------------------");
		String className = (String) getInputArg("String", "Enter class name:");
		System.out.println("---------------------");

        Class ref_class = Class.forName("edu.school21.classes.".concat(className));
		printSummary(ref_class);
		System.out.println("---------------------");
		System.out.println("Let's create an object.");
        
		Constructor constr = getConstructor(ref_class);
        Parameter constrParams[] = constr.getParameters();
        Object constrArgs[] = new Object[constrParams.length];
		Field [] fields = ref_class.getDeclaredFields();

        for (int i = 0; i < constrParams.length; i++) {
            Class<?> t = constrParams[i].getType();
            while (constrArgs[i] == null)
                constrArgs[i] = getInputArg(t.getSimpleName(), fields[i].getName() + "(" + t.getSimpleName() + "):");
		}
        Object obj = constr.newInstance(constrArgs);
        System.out.println("Object created: " + obj);
        System.out.println("---------------------");
		
       
        Field field = null;
         while (field == null) {
            String filedName = (String) getInputArg("String", "Enter name of the field for changing:");
            try {
                field = ref_class.getDeclaredField(filedName);
            } catch (NoSuchFieldException e) {
            }
        }
        Object value = null;
        while (value == null)
                value = getInputArg(field.getType().getSimpleName(), "Enter " + field.getType().getSimpleName() + " value: ");
        field.setAccessible(true);
        field.set(obj, value);
        field.setAccessible(false);
        System.out.println("Object updated:" + obj);
        System.out.println("---------------------");
       
        Method [] methods = ref_class.getDeclaredMethods();
        Method method = null;
        while (method == null) {
            String methodName = (String)getInputArg("String", "Enter name of the method for call:");
            method = getMethod(methods, methodName);
        }
       
        Parameter methodParams[] = method.getParameters();
        Object methodArgs [] = new Object[methodParams.length];
        for (int i = 0; i < methodParams.length; i++) {
            while (methodArgs[i] == null) {
                String type = methodParams[i].getType().getSimpleName();
                methodArgs[i] = getInputArg(type, "Enter " + type + " value:");
            }
        }
        Object ret = method.invoke(obj, methodArgs);
        if (ret != null)
		{
            System.out.println("Method returned:");
			System.out.println(ret);
		}
        System.out.println("Object final: " + obj);
        System.out.println("---------------------");
    }

}