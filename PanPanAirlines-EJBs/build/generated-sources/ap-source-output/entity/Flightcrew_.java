package entity;

import entity.Employee;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-23T18:52:24")
@StaticMetamodel(Flightcrew.class)
public class Flightcrew_ { 

    public static volatile SingularAttribute<Flightcrew, Integer> crewid;
    public static volatile SingularAttribute<Flightcrew, Integer> id;
    public static volatile SingularAttribute<Flightcrew, Employee> employeeid;

}