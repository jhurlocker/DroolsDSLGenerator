package com.sample.dsl.reflect;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class CreateDSL {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ArrayList listofClasses = new ArrayList();
			JarInputStream theJarFile = new JarInputStream(new FileInputStream("./src/com/sample/dsl/reflect/reflectDSLModel.jar"));
			
			JarEntry theJar;
			 
			while (true) {
				theJar = theJarFile.getNextJarEntry();
				if (theJar == null) {
					break;
				}
				if ((theJar.getName().endsWith(".class"))) {
					String className = theJar.getName().replaceAll("/", "\\.");
					String myClass = className.substring(0, className.lastIndexOf('.'));
					listofClasses.add(myClass);
				}
			}
			for (int x = 0; x < listofClasses.size(); x++){
				
				String aClass = listofClasses.get(x).toString();

				Class<?> theClass;
				try {
					theClass = Class.forName(listofClasses.get(x).toString());
					Method[] methods = theClass.getDeclaredMethods();
					Field[] fields = theClass.getDeclaredFields();

					for (int i =0; i < fields.length; i++){

						String[] fieldArray = fields[i].getType().toString().split(" ");
						String field = fieldArray[1];
						String fieldName = split(fields[i].toString());

						String className = split(listofClasses.get(x).toString());

						if (field.trim().equalsIgnoreCase("java.util.List")){
							createDSLforList(className, fieldName);									
						}
						if (field.equalsIgnoreCase("java.lang.String") ||
								field.equalsIgnoreCase("java.lang.Character") ||
								field.equalsIgnoreCase("char")){
							createDSLforStrings(className, fieldName);									
						}
						if (field.equalsIgnoreCase("java.lang.Boolean") ||
								field.equalsIgnoreCase("boolean")){
							createDSLforBoolean(className, fieldName);									
						}
						
						if (field.equalsIgnoreCase("java.lang.Integer") ||
								field.equalsIgnoreCase("java.lang.Byte") ||
								field.equalsIgnoreCase("java.lang.Short") ||
								field.equalsIgnoreCase("java.lang.Long") ||
								field.equalsIgnoreCase("java.lang.Float") ||
								field.equalsIgnoreCase("java.lang.Number") ||
								field.equalsIgnoreCase("java.lang.Double") ||
								field.equalsIgnoreCase("java.math.BigDecimal") ||
								field.equalsIgnoreCase("java.math.BigInteger") ||
								field.equalsIgnoreCase("int") ||
								field.equalsIgnoreCase("byte") ||
								field.equalsIgnoreCase("short") ||
								field.equalsIgnoreCase("long") ||
								field.equalsIgnoreCase("float") ||
								field.equalsIgnoreCase("double")){
							createDSLforNumeric(className, fieldName);									
						}
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String split(String classOrField){
		String[] classOrFieldArray = classOrField.split("\\.");
		int len = classOrFieldArray.length;		
		String classOrFieldNameString = classOrFieldArray[len - 1];
		return classOrFieldNameString;
	}
	
	public static void createDSLforStrings(String className, String fieldName){
		System.out.println("[conditon]  A " + className + " has a " + fieldName + " that is not empty=" + className.toLowerCase() + " : " + className + "(" + fieldName.toString() + "!= null || != '')");
		System.out.println("[conditon]  A " + className + " has a " + fieldName + " that is empty=" + className.toLowerCase() + " : " + className + "(" + fieldName.toString() + "== null || == '')");
		System.out.println("[conditon]  A " + className + " has a " + fieldName + " that is equal to {var1}=" + className.toLowerCase() + " : " + className + "(" + fieldName.toString() + "== {var1})");
		System.out.println("[conditon]  A " + className + " has a " + fieldName + " that is not equal to {var1}=" + className.toLowerCase() + " : " + className + "(" + fieldName.toString() + "!= {var1})");
	}
	
	public static void createDSLforBoolean(String className, String fieldName){
		System.out.println("[conditon]  A " + className + " has a " + fieldName + " that is not empty=" + className.toLowerCase() + " : " + className + "(" + fieldName.toString() + "!= null || != '')");
		System.out.println("[conditon]  A " + className + " has a " + fieldName + " that is empty=" + className.toLowerCase() + " : " + className + "(" + fieldName.toString() + "== null || == '')");
		System.out.println("[conditon]  A " + className + " has a " + fieldName + " that is equal to {var1}=" + className.toLowerCase() + " : " + className + "(" + fieldName.toString() + "== {var1})");
		System.out.println("[conditon]  A " + className + " has a " + fieldName + " that is not equal to {var1}=" + className.toLowerCase() + " : " + className + "(" + fieldName.toString() + "!= {var1})");
	}
	
	public static void createDSLforNumeric(String className, String fieldName){
		System.out.println("[conditon]  A " + className + " has a " + fieldName + " that is not empty=" + className.toLowerCase() + " : " + className + "(" + fieldName.toString() + "!= null || != '')");
		System.out.println("[conditon]  A " + className + " has a " + fieldName + " that is empty=" + className.toLowerCase() + " : " + className + "(" + fieldName.toString() + "== null || == '')");
		System.out.println("[conditon]  A " + className + " has a " + fieldName + " that is equal to {var1}=" + className.toLowerCase() + " : " + className + "(" + fieldName.toString() + "== {var1})");
		System.out.println("[conditon]  A " + className + " has a " + fieldName + " that is not equal to {var1}=" + className.toLowerCase() + " : " + className + "(" + fieldName.toString() + "!= {var1})");
		System.out.println("[conditon]  A " + className + " has a " + fieldName + " that is greater than {var1}=" + className.toLowerCase() + " : " + className + "(" + fieldName.toString() + "== {var1})");
		System.out.println("[conditon]  A " + className + " has a " + fieldName + " that is less than {var1}=" + className.toLowerCase() + " : " + className + "(" + fieldName.toString() + "!= {var1})");
	}
	
	public static void createDSLforList(String className, String fieldName){
		System.out.println("[conditon]  A " + className + " has a list of " + fieldName + " that are not empty=" + className.toLowerCase() + " : " + className + "(" + fieldName.toString() + "!= null || != '')");
		System.out.println("[conditon]  A " + className + " has a list of " + fieldName + " that are empty=" + className.toLowerCase() + " : " + className + "(" + fieldName.toString() + "== null || == '')");
	}
}
