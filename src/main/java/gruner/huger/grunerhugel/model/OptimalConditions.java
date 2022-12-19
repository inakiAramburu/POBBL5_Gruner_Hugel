package gruner.huger.grunerhugel.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "optimal_conditions")
@Getter
@Setter
public class OptimalConditions implements Serializable{
    
    @Id
    @Column(name = "type_name")
    String name;
    @Column(name = "Germinacion min (ºC)")
    int minTemp;
    @Column(name = "Germinacion optima min (ºC)")
    int minOptimalTemp;
    @Column(name = "Germinacion optima max (ºC)")
    int maxOptimalTemp;
    @Column(name = "crecimiento vegetativo  t<(ºC)")
    int growthTemp;
    @Column(name = "macollamiento min(ºC)")
    int minMaturityTemp;
    @Column(name = "macollamiento max(ºC)")
    int maxMaturityTemp;
    @Column(name = "antesis min min (ºC)")
    int minMinAntesisTemp;
    @Column(name = "antesis min max (ºC)")
    int minMaxAntesisTemp;
    @Column(name = "antesis max min (ºC)")
    int maxMinAntesisTemp;
    @Column(name = "antesis max max (ºC)")
    int maxMaxAntesisTemp;
    @Column(name = "lechoso min min (ºC)")
    int minMinLechosoTemp;
    @Column(name = "lechoso min max (ºC)")
    int minMaxLechosoTemp;
    @Column(name = "lechoso max min (ºC)")
    int maxMinLechosoTemp;
    @Column(name = "lechoso max max (ºC)")
    int maxMaxLechosoTemp;
    @Column(name = "pastoso min min (ºC)")
    int minMinPastosoTemp;
    @Column(name = "pastoso min max (ºC)")
    int minMaxPastosoTemp;
    @Column(name = "pastoso max min (ºC)")
    int maxMinPastosoTemp;
    @Column(name = "pastoso max max (ºC)")
    int maxMaxPastosoTemp;
    @Column(name = "maduracion min min (ºC)")
    int minMinMaturityTemp;
    @Column(name = "maduracion min max (ºC)")
    int minMaxMaturityTemp;
    @Column(name = "maduracion max min (ºC)")
    int maxMinMaturityTemp;
    @Column(name = "maduracion max max (ºC)")
    int maxMaxMaturityTemp;

    public OptimalConditions() {
      //no need
    }

}
