package gruner.huger.grunerhugel.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "role")
@Getter
@Setter
public class Role implements Serializable{
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "name")
    private String name;

    public Role() {
    
    }

    public Role(int id) {
        this.id = id;
    }
    
	public Role(String role) {
        if(role.equals("ADMIN")){
            this.id = 2;
        } else if(role.equals("USER")){
            this.id = 1;
        }
        this.name = role;
    }

    @Override
    public String toString() {
        return "Role [id ="+id+" name=" + name + "]";
    }
}
