# Final Project

This is the final project for my CIS-181 Java III course at Kirkwood Community College. Below is a journal of all the things I learned throughout the class.

## Chapters 1 and 2

in chapters 1 and 2 in Java 3, we primarily learned about the Java Enterprise Edition (Java EE) and multi-tiered applications, and web containers. 
The Java Enterprise Edition is a specification that describes how web servers interact with Java web technologies like Servlets and Java Server Pages (JSP).
A web application, which on the surface is quite self-explanator, is a type of application where a user can interact with an application on a server from their
own computer. We employed these concepts through a Java Servlet Container called Apache Tomcat.

## Chapters 3 and 4

in chapters 3 and 4 in Java 3, we primarily learned about Servlets,  Java Server Pages (JSPs), and "Plain Old Java Objects," or POJOs. A servlet is a class that implements the Java Servlet interface, it does this by extending the HttpServlet class which inherits from the GenericServlet class. a Servlet allows a webcontainer, in our case Tomcat, to take and reply to HTTP requests.

Plain Old Java Objects are, to put it simply, just any plain old java object. For instance, 
~~~java
// POJO class Exmaple https://www.javatpoint.com/pojo-in-java#:~:text=POJO%20in%20Java%20stands%20for,usability%20of%20a%20Java%20program.
package Jtp.PojoDemo;  
public class Employee {  
private String name;  
private String id;  
private double sal;  
public String getName() {  
    return name;  
}  
public void setName(String name) {  
    this.name = name;  
}  
public String getId() {  
    return id;  
}  
public void setId(String id) {  
    this.id = id;  
}  
public double getSal() {  
    return sal;  
}  
public void setSal(double sal) {  
    this.sal = sal;  
}  
}  
~~~
the former is an example of a POJO, a POJO represents any basic object.

Java Server Pages allow us to combine Java code with HTML.
the following can be used in a JSP file
~~~
<%@ this is a directive %> - Directives are elements that relay messages to the JSP container and affect how it compiles the JSP page.
<%! this is a declaration %> -  Declarations can be used to declare variables and methods.
<% this is a scriplet %> - Scriplets can be used to embed code into a JSP 
<%= this is an expression %> - Expressions are used to output variables to the user, the concept is similar to putting a variable in a System.out.println() statement
~~~
