package gruner.huger.grunerhugel.model.formObjects;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class EditSimulation {

    String tractorName;
    int numTractor;
    int numWorkers;
    String plowName;
    int numPlow;
    String seederName;
    int numSeeder;
    String harvesterName;
    int numHarvester;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date endDate;

    public EditSimulation() {
        // no need
    }

    public String getTractorName() {
        return tractorName;
    }

    public void setTractorName(String tractorName) {
        this.tractorName = tractorName;
    }

    public int getNumTractor() {
        return numTractor;
    }

    public void setNumTractor(int numTractor) {
        this.numTractor = numTractor;
    }

    public int getNumWorkers() {
        return numWorkers;
    }

    public void setNumWorkers(int numWorkers) {
        this.numWorkers = numWorkers;
    }

    public String getPlowName() {
        return plowName;
    }

    public void setPlowName(String plowName) {
        this.plowName = plowName;
    }

    public int getNumPlow() {
        return numPlow;
    }

    public void setNumPlow(int numPlow) {
        this.numPlow = numPlow;
    }

    public String getSeederName() {
        return seederName;
    }

    public void setSeederName(String seederName) {
        this.seederName = seederName;
    }

    public int getNumSeeder() {
        return numSeeder;
    }

    public void setNumSeeder(int numSeeder) {
        this.numSeeder = numSeeder;
    }

    public String getHarvesterName() {
        return harvesterName;
    }

    public void setHarvesterName(String harvesterName) {
        this.harvesterName = harvesterName;
    }

    public int getNumHarvester() {
        return numHarvester;
    }

    public void setNumHarvester(int numHarvester) {
        this.numHarvester = numHarvester;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}
