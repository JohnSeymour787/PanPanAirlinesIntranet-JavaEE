package entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-28T10:54:03")
@StaticMetamodel(Employee.class)
public class Employee_ { 

    public static volatile SingularAttribute<Employee, String> firstname;
    public static volatile SingularAttribute<Employee, String> address;
    public static volatile SingularAttribute<Employee, String> phone;
    public static volatile SingularAttribute<Employee, String> passwordencrypted;
    public static volatile SingularAttribute<Employee, String> passwordplain;
    public static volatile SingularAttribute<Employee, Boolean> active;
    public static volatile SingularAttribute<Employee, Integer> employeeid;
    public static volatile SingularAttribute<Employee, String> rolegroup;
    public static volatile SingularAttribute<Employee, String> email;
    public static volatile SingularAttribute<Employee, String> lastname;
    public static volatile SingularAttribute<Employee, String> username;

}