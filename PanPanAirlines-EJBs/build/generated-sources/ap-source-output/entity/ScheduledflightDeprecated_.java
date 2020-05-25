package entity;

import entity.Aircraft;
import entity.Flightcrew;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-23T18:52:24")
@StaticMetamodel(ScheduledflightDeprecated.class)
public class ScheduledflightDeprecated_ { 

    public static volatile SingularAttribute<ScheduledflightDeprecated, String> destinationairport;
    public static volatile SingularAttribute<ScheduledflightDeprecated, Aircraft> aircraft;
    public static volatile SingularAttribute<ScheduledflightDeprecated, Integer> flightnumber;
    public static volatile SingularAttribute<ScheduledflightDeprecated, String> originatingairport;
    public static volatile SingularAttribute<ScheduledflightDeprecated, Flightcrew> crew;

}