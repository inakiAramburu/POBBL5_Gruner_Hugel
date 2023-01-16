package gruner.huger.grunerhugel.model.formObjects;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class CreateSimulation {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date endDate;
    int budget;
    String tractorName;
    int numTractor;
    int numWorkers;
    String plowName;
    int numPlow;
    String seederName;
    int numSeeder;
    String harvesterName;
    int numHarvester;

    public CreateSimulation() {
        // no need
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
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

}
