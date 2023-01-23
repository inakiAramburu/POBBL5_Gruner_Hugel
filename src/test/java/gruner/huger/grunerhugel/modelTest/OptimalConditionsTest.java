package gruner.huger.grunerhugel.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import gruner.huger.grunerhugel.model.OptimalConditions;

@SpringBootTest
public class OptimalConditionsTest {

    @Test
    public void testGetSet() {
        OptimalConditions optimalConditions = new OptimalConditions();
        optimalConditions.setName("name");
        optimalConditions.setMinGerminationTemp(0);
        optimalConditions.setMinOpGerminationTemp(0);
        optimalConditions.setMaxOpGerminationTemp(0);
        optimalConditions.setVegetativeTemp(0);
        optimalConditions.setMinTilleringTemp(0);
        optimalConditions.setMaxTilleringTemp(0);
        optimalConditions.setMinMinAnthesisTemp(0);
        optimalConditions.setMinMaxAnthesisTemp(0);
        optimalConditions.setMaxMinAnthesisTemp(0);
        optimalConditions.setMaxMaxAnthesisTemp(0);
        optimalConditions.setMinMinMaturityTemp(5);
        optimalConditions.setMinMaxMaturityTemp(5);
        optimalConditions.setMaxMinMaturityTemp(5);
        optimalConditions.setMaxMaxMaturityTemp(5);
        optimalConditions.setMaxMaxMilkyTemp(0);
        optimalConditions.setMinMinMilkyTemp(0);
        optimalConditions.setMinMaxMilkyTemp(0);
        optimalConditions.setMaxMinMilkyTemp(0);
        optimalConditions.setMaxMaxPastyTemp(0);
        optimalConditions.setMinMinPastyTemp(0);
        optimalConditions.setMinMaxPastyTemp(0);
        optimalConditions.setMaxMinPastyTemp(0);

        assertEquals("name", optimalConditions.getName());
        assertEquals(0, optimalConditions.getMinGerminationTemp());
        assertEquals(0, optimalConditions.getMinOpGerminationTemp());
        assertEquals(0, optimalConditions.getMaxOpGerminationTemp());
        assertEquals(0, optimalConditions.getVegetativeTemp());
        assertEquals(0, optimalConditions.getMinTilleringTemp());
        assertEquals(0, optimalConditions.getMaxTilleringTemp());
        assertEquals(0, optimalConditions.getMinMinAnthesisTemp());
        assertEquals(0, optimalConditions.getMinMaxAnthesisTemp());
        assertEquals(0, optimalConditions.getMaxMinAnthesisTemp());
        assertEquals(0, optimalConditions.getMaxMaxAnthesisTemp());
        assertEquals(5, optimalConditions.getMinMinMaturityTemp());
        assertEquals(5, optimalConditions.getMinMaxMaturityTemp());
        assertEquals(5, optimalConditions.getMaxMinMaturityTemp());
        assertEquals(5, optimalConditions.getMaxMaxMaturityTemp());
        assertEquals(0, optimalConditions.getMaxMaxMilkyTemp());
        assertEquals(0, optimalConditions.getMinMinMilkyTemp());
        assertEquals(0, optimalConditions.getMinMaxMilkyTemp());
        assertEquals(0, optimalConditions.getMaxMinMilkyTemp());
        assertEquals(0, optimalConditions.getMaxMaxPastyTemp());
        assertEquals(0, optimalConditions.getMinMinPastyTemp());
        assertEquals(0, optimalConditions.getMinMaxPastyTemp());
        assertEquals(0, optimalConditions.getMaxMinPastyTemp());
    }

    @Test
    public void testToString() {
        OptimalConditions optimalConditions = new OptimalConditions();
        optimalConditions.setName("name");
        optimalConditions.setMinGerminationTemp(0);
        optimalConditions.setMinOpGerminationTemp(0);
        optimalConditions.setMaxOpGerminationTemp(0);
        optimalConditions.setVegetativeTemp(0);
        optimalConditions.setMinTilleringTemp(0);
        optimalConditions.setMaxTilleringTemp(0);
        optimalConditions.setMinMinAnthesisTemp(0);
        optimalConditions.setMinMaxAnthesisTemp(0);
        optimalConditions.setMaxMinAnthesisTemp(0);
        optimalConditions.setMaxMaxAnthesisTemp(0);
        optimalConditions.setMinMinMaturityTemp(5);
        optimalConditions.setMinMaxMaturityTemp(5);
        optimalConditions.setMaxMinMaturityTemp(5);
        optimalConditions.setMaxMaxMaturityTemp(5);
        optimalConditions.setMaxMaxMilkyTemp(0);
        optimalConditions.setMinMinMilkyTemp(0);
        optimalConditions.setMinMaxMilkyTemp(0);
        optimalConditions.setMaxMinMilkyTemp(0);
        optimalConditions.setMaxMaxPastyTemp(0);
        optimalConditions.setMinMinPastyTemp(0);
        optimalConditions.setMinMaxPastyTemp(0);
        optimalConditions.setMaxMinPastyTemp(0);

        assertEquals(
                "OptimalConditions [name=name, minGerminationTemp=0, minOpGerminationTemp=0, maxOpGerminationTemp=0, vegetativeTemp=0, minTilleringTemp=0, maxTilleringTemp=0, minMinAnthesisTemp=0, minMaxAnthesisTemp=0, maxMinAnthesisTemp=0, maxMaxAnthesisTemp=0, minMinMilkyTemp=0, minMaxMilkyTemp=0, maxMinMilkyTemp=0, maxMaxMilkyTemp=0, minMinPastyTemp=0, minMaxPastyTemp=0, maxMinPastyTemp=0, maxMaxPastyTemp=0, minMinMaturityTemp=5, minMaxMaturityTemp=5, maxMinMaturityTemp=5, maxMaxMaturityTemp=5]",
                optimalConditions.toString());
    }

}
