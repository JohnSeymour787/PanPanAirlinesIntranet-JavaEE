package session;

import entity.EmployeeDTO;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entity.Flightcrew;
import entity.FlightCrewDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.persistence.Query;
/**
 *
 * @author John
 */
@Stateful
public class FlightCrewFacade implements FlightCrewFacadeLocal
{
    @PersistenceContext(unitName = "PanPanAirlines-EJBsPU")
    private EntityManager em;
    @EJB
    private EmployeeFacadeLocal employeeFacade;
    
    private FlightCrewDTO crew;
    private List<Integer> IDList;
    private Integer initialEmployeesCount;
    
    private void createCrew(Flightcrew flight) throws Exception
    {
        em.persist(flight);
    }
    
    @Override
    public Flightcrew findCrewDAO(Integer id)
    {
        return em.find(Flightcrew.class, id);
    }
    
    private void updateCrew(Flightcrew flightCrew) throws Exception
    {
        if (em.merge(flightCrew) == null)
            throw new Exception("Nothing updated.");
    }
    
    private void deleteCrew(int id) throws Exception
    {
        Flightcrew toRemove = findCrewDAO(id);
        if (toRemove == null)
            throw new Exception("Cannot find record to remove.");
        else
            em.remove(toRemove);
    }
    
    
    //Converts the externally-available DTO to a DAO for use with the EntityManager
    private Flightcrew dtoToDAO(FlightCrewDTO flightCrew)
    {
        /*
        Flightcrew result;
        
        result = new Flightcrew
        (
            flightCrew.getCrewid(), 
            flightCrew.getId(),
            flightCrew.getEmployees()
        );
        */
        return null;
        //return result;
    }
    
    @Override
    public boolean createFlightCrew(int id, int crewID, int employeeID)
    {
        //Getting the employee from the Employee table and making sure it exists
        EmployeeDTO employee = employeeFacade.getEmployeeDetails(employeeID);
        if (employee == null)
            return false;

        //Unique DB record already exists
        if (crewExists(id))
            return false;
        
        ArrayList<Flightcrew> crewIDArray = getAllForCrewID(crewID);
        
        //Shared crewID already exists
        if (!crewIDArray.isEmpty())
            return false;
        
        Flightcrew flightCrew = new Flightcrew(id, crewID, employeeID);

        try
        {
            createCrew(flightCrew);
        }
        catch (Exception e)
        {
            return false;
        }
        
        return true;
    }
    
    private ArrayList<Flightcrew> getAllForCrewID(Integer crewID)
    {
        //Finding all Flightcrew DAOs with the same, shared CrewID field
        Query query = em.createNamedQuery("Flightcrew.findByCrewid").setParameter("crewid", crewID);
        
        ArrayList<Flightcrew> result;
        
        try
        {
            result = new ArrayList<>(query.getResultList());
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        
        return result;
    }

    @Override
    public FlightCrewDTO findFlightCrew(int crewID)
    {
        try
        {
            //Getting all DB records with the crew 
            ArrayList<Flightcrew> daoArray = getAllForCrewID(crewID);
            
            if (daoArray.isEmpty())
                return null;

            //Extracting all employeeIDs from the Flightcrew DAOs.
            int[] employeeIDs = new int[daoArray.size()];
            
            for (int i = 0; i < employeeIDs.length; i++)
                employeeIDs[i] = daoArray.get(i).getEmployeeid();

            //EmployeeFacade will convert an array of empIDs to an ArrayList of DTOs.
            ArrayList<EmployeeDTO> employeesDTOList = employeeFacade.employeeIDsToDTOs(employeeIDs);

            //Can now create the FlightCrewDTO with the list of employeeDTOs
            FlightCrewDTO result = new FlightCrewDTO
            (
                crewID, 
                daoArray.get(0).getId(),    //Know that the list is not empty, so just get the unique DB record ID of the first element
                employeesDTOList
            );

            //Saving to the stateful bean's field in case it will be updated
            crew = result;
            
            return result;
        }
        catch (Exception e)
        {
            return null;
        }

    }

    @Override
    public boolean updateFlightCrew(FlightCrewDTO flightCrew)
    {
        if (flightCrew == null)
            return false;
        
        //Record must exist
        if (!crewExists(flightCrew.getCrewid()))
            return false;
        
        try
        {
            updateCrew(dtoToDAO(flightCrew));
        }
        catch (Exception e)
        {
            return false;
        }
        
        return true;
    }

    @Override
    public boolean deleteFlightCrew(int id)
    {
        try
        {
            deleteCrew(id);
        }
        catch (Exception e)
        {
            return false;
        }
        
        return true;
    }

    @Override
    public boolean crewExists(int id)
    {
        return findCrewDAO(id) != null;
    }

    @Override
    public boolean isAdmin(Integer employeeID)
    {
        return employeeFacade.isAdmin(employeeID);
    }

    @Override
    public List<EmployeeDTO> addEmployeeToCrew(Integer employeeID, Integer id)
    {
        //Crew DTO with the employees and crewID should exist before adding to the employees list
        if (crew == null)
            return null;
        
        //The current unique DB ID should not exist, either for this crewID or another
        if (crewExists(id))
            return null;
        
        //Get the EmployeeDTO from the ID
        EmployeeDTO toAdd = employeeFacade.getEmployeeDetails(employeeID);
        
        //If employee doesn't exist in the employees DB, then should not continue
        if (toAdd == null)
            return null;
        
        //Checking that this employee is not already in the crew list
        for (EmployeeDTO emp : crew.getEmployees())
            if (Objects.equals(emp.getEmployeeid(), toAdd.getEmployeeid()))
                return null;
        
        //If this is the first new employee to add to the list
        if (IDList == null)
        {
            //Create a new list to record the unique IDs to add
            IDList = new ArrayList<>();
            //Record the number of employees that were already in this crew
            initialEmployeesCount = crew.getEmployees().size();
        }
        
        //Add the employeeDTO to the crew list
        crew.getEmployees().add(toAdd);
        //Add the unique ID to the ID list
        IDList.add(id);
        
        //Return the list for the client to update their page of the temporaray changes to this crew list.
        return crew.getEmployees();
    }

    //Saves the employees in the 'crew' field and the unique DB IDs to the FlightCrew DB table
    @Override
    public boolean saveListToDB()
    {
        //Cannot save list if doesn't exist
        if (crew == null)
            return false;
        
        //Need to start the index at 1 element after the last employee that was already in the list from the DB, originally.
        int unaddedEmployeesIndex = initialEmployeesCount;
        int IDListIndex = 0;
        
        //New FlightCrew DAO to add to the DB table
        Flightcrew newTableRecord;
               
        //For each new employee in the temp crew list, get the corresponding unique ID for it, create a DAO, and attempt to add
        //it to the DB
        while (unaddedEmployeesIndex < crew.getEmployees().size())
        {
            newTableRecord = new Flightcrew(IDList.get(IDListIndex), crew.getCrewid(), crew.getEmployees().get(unaddedEmployeesIndex).getEmployeeid());
        
            try
            {
                createCrew(newTableRecord);
            }
            catch (Exception e)
            {
                return false;
            }
            
            unaddedEmployeesIndex++;
            IDListIndex++;
        }
        
        return true;
    }
    
    
}
