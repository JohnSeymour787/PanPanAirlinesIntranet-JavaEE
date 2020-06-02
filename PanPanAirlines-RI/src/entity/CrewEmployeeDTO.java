/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author John
 */
public class CrewEmployeeDTO
{
    private final String firstName;
    private final String lastName;
    private final String rolegroup;
    

    public CrewEmployeeDTO(String firstName, String lastName, String rolegroup)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.rolegroup = rolegroup;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getRolegroup()
    {
        return rolegroup;
    }
}
