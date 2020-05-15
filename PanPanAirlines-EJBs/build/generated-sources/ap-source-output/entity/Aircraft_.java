package entity;

import entity.Scheduledflight;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-16T01:24:45")
@StaticMetamodel(Aircraft.class)
public class Aircraft_ { 

    public static volatile CollectionAttribute<Aircraft, Scheduledflight> scheduledflightCollection;
    public static volatile SingularAttribute<Aircraft, Integer> aircraftid;
    public static volatile SingularAttribute<Aircraft, String> aircrafttype;
    public static volatile SingularAttribute<Aircraft, Integer> seats;
    public static volatile SingularAttribute<Aircraft, String> manufacturer;

}