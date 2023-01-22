package gruner.huger.grunerhugel.modelTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import gruner.huger.grunerhugel.model.OptimalConditions;
public class OptimalConditionsTest {
    
    @Test
    public void testGetSet(){
        OptimalConditions optimalConditions = new OptimalConditions();
        optimalConditions.setName("name");
        optimalConditions.setMinTemp(5);
        optimalConditions.setMinOptimalTemp(5);
        optimalConditions.setMaxOptimalTemp(5);
        optimalConditions.setGrowthTemp(5);
        optimalConditions.setMinMaturityTemp(5);
        optimalConditions.setMaxMaturityTemp(5);
        optimalConditions.setMinMinAntesisTemp(5);
        optimalConditions.setMinMaxAntesisTemp(5);
        optimalConditions.setMaxMinAntesisTemp(5);
        optimalConditions.setMaxMaxAntesisTemp(5);
        optimalConditions.setMinMinLechosoTemp(5);
        optimalConditions.setMinMaxLechosoTemp(5);
        optimalConditions.setMaxMinLechosoTemp(5);
        optimalConditions.setMaxMaxLechosoTemp(5);
        optimalConditions.setMinMinPastosoTemp(5);
        optimalConditions.setMinMaxPastosoTemp(5);
        optimalConditions.setMaxMinPastosoTemp(5);
        optimalConditions.setMaxMaxPastosoTemp(5);
        optimalConditions.setMinMinMaturityTemp(5);
        optimalConditions.setMinMaxMaturityTemp(5);
        optimalConditions.setMaxMinMaturityTemp(5);
        optimalConditions.setMaxMaxMaturityTemp(5);
        
        assertEquals("name", optimalConditions.getName());
        assertEquals(5, optimalConditions.getMinTemp());
        assertEquals(5, optimalConditions.getMinOptimalTemp());
        assertEquals(5, optimalConditions.getMaxOptimalTemp());
        assertEquals(5, optimalConditions.getGrowthTemp());
        assertEquals(5, optimalConditions.getMinMaturityTemp());
        assertEquals(5, optimalConditions.getMaxMaturityTemp());
        assertEquals(5, optimalConditions.getMinMinAntesisTemp());
        assertEquals(5, optimalConditions.getMinMaxAntesisTemp());
        assertEquals(5, optimalConditions.getMaxMinAntesisTemp());
        assertEquals(5, optimalConditions.getMaxMaxAntesisTemp());
        assertEquals(5, optimalConditions.getMinMinLechosoTemp());
        assertEquals(5, optimalConditions.getMinMaxLechosoTemp());
        assertEquals(5, optimalConditions.getMaxMinLechosoTemp());
        assertEquals(5, optimalConditions.getMaxMaxLechosoTemp());
        assertEquals(5, optimalConditions.getMinMinPastosoTemp());
        assertEquals(5, optimalConditions.getMinMaxPastosoTemp());
        assertEquals(5, optimalConditions.getMaxMinPastosoTemp());
        assertEquals(5, optimalConditions.getMaxMaxPastosoTemp());
        assertEquals(5, optimalConditions.getMinMinMaturityTemp());
        assertEquals(5, optimalConditions.getMinMaxMaturityTemp());
        assertEquals(5, optimalConditions.getMaxMinMaturityTemp());
        assertEquals(5, optimalConditions.getMaxMaxMaturityTemp());
    }




        
}
