package gruner.huger.grunerhugel.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Role implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    public Role() {
    }
    
	public Role(String role) {
        if(role.equals("USER")) {
            this.id = 1;
        } else if(role.equals("ADMIN")) {
            this.id = 2;
        }
        this.name = role;
    }

    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
		return name;
	}
	public void setRole(String role) {
		this.name = role;
	}
	
}
