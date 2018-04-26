package com.teamtreehouse.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@Entity
public class Direction
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String directionName;

    public Direction(String directionName)
    {
        this.directionName = directionName;
    }

    public Direction()
    {
        this(null);
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getDirectionName()
    {
        return directionName;
    }

    public void setDirectionName(String directionName)
    {
        this.directionName = directionName;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Direction direction = (Direction)o;

        if (id != null ? !id.equals(direction.id) : direction.id != null)
            return false;
        return directionName != null ? directionName.equals(direction.directionName) : direction.directionName == null;

    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (directionName != null ? directionName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "Direction{" +
                "id=" + id +
                ", directionName='" + directionName + '\'' +
                //", recipe=" + recipe +
                '}';
    }
}
