package gruner.huger.grunerhugel.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Role implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    private String name;

    public Role() {
    }
    
	public Role(String role) {
        if(role.equals("ADMIN")){
            this.id = 2;
        } else if(role.equals("USER")){
            this.id = 1;
        }
        this.name = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role [id ="+id+" name=" + name + "]";
    }
}
