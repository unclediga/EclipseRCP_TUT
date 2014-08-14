package de.techjava.rcp.cnf.subcontent.data;

import de.techjava.rcp.cnf.data.Child;

/**
 * Represents a pet
 * @author Simon Zambrovski
 */
public class Pet
{
    private String name;
    private Child owner;
    
    public Pet(String name, Child owner)
    {
        super();
        this.name = name;
        this.owner = owner;
    }
    
    
    public final String getName()
    {
        return name;
    }
    public final void setName(String name)
    {
        this.name = name;
    }
    public final Child getOwner()
    {
        return owner;
    }
    public final void setOwner(Child owner)
    {
        this.owner = owner;
    }
}
