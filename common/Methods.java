package common;

import java.lang.reflect.Field;
import java.lang.reflect.Constructor;

public class Methods {

  /**
   * Checks whether the object represented by this component satisfies the
   * properties of the class specified by the argument requiredClass. First
   * checks all the super classes of the object then checks all the interfaces
   * implemented by all the super classes of the object.
   * @param requiredClass Class
   * @return boolean
   */
  public static boolean satisfy(Object object, Class requiredClass) {
    Class c = object.getClass();
    if (requiredClass.isAssignableFrom(c)) return true;
    while(c.getName() != "java.lang.Object") {
      Class[] interfaces = c.getInterfaces();
      for (int i=0; i < interfaces.length; i++)
        if (interfaces[i] == requiredClass)return true;
      c = c.getSuperclass();
    }
    return false;
  }

  /*
   * Clones a Java object regardless of its type
   */
  public static Object clone(Object object) {
    Object clone = null;
    Class c = null;
    try {
       c = object.getClass();
    } catch (java.lang.NullPointerException e) {
      return null;
    }
    clone = getInstance(c);
    if (clone == null) return null;
    while(c.getName() != "java.lang.Object") {
      Field[] fields = c.getDeclaredFields();
      Field.setAccessible(fields, true);
      for (int i=0; i < fields.length; i++) {
        if (fields[i].getType().isPrimitive()) {
          try {
            fields[i].set(clone, fields[i].get(object));
          }
          catch (IllegalAccessException e) {
            System.out.println("Clone Error: 1");
          }
        }
        else {
          try {
            fields[i].set(clone, clone(fields[i].get(object)));
          }
          catch (IllegalAccessException e) {
            System.out.println("Clone Error: 2");
          }
        }
      }
      Field.setAccessible(fields, false);
      c = c.getSuperclass();
    }
    return clone;
  }

  /*
   * Gets an instance of Java object regardless of its type
   */
  public static Object getInstance(Class c) {
    Constructor[] cons = c.getDeclaredConstructors();
    System.out.println(cons.length);
    try {
      Constructor.setAccessible(cons, true);
    } catch (java.lang.SecurityException e) {
      System.out.println("getInstance Error: 1");
    }
    for (int j = 0; j < cons.length; j++) {
      Class[] argClasses = cons[0].getParameterTypes();
      Object[] args = new Object[argClasses.length];
      for (int i = 0; i < argClasses.length; i++) {
        if (argClasses[i].isPrimitive()) {
          if (argClasses[i].getName() == "int")
            args[i] = new Integer(0);
          else if (argClasses[i].getName() == "byte")
            args[i] = new Byte("0");
          else if (argClasses[i].getName() == "short")
            args[i] = new Short("0");
          else if (argClasses[i].getName() == "long")
            args[i] = new Long("0");
          else if (argClasses[i].getName() == "float")
            args[i] = new Float("0");
          else if (argClasses[i].getName() == "double")
            args[i] = new Double("0");
          else if (argClasses[i].getName() == "boolean")
            args[i] = new Boolean(true);
          else if (argClasses[i].getName() == "char") {
            char ch = 0;
            args[i] = new java.lang.Character(ch);
          }
        }
        else {
          args[i] = getInstance(argClasses[i]);
        }
      }
      Object object = null;
      try {
        object = cons[0].newInstance(args);
        Constructor.setAccessible(cons, false);
        return object;
      }
      catch (InstantiationException e) {System.out.println("getInstance Error: 2");}
      catch (IllegalAccessException e) {System.out.println("getInstance Error: 3");}
      catch (java.lang.reflect.InvocationTargetException e) {System.out.println("getInstance Error: 4");}
    }
    Constructor.setAccessible(cons, false);
    return null;
  }
}