package gruner.huger.grunerhugel.modelTest;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import gruner.huger.grunerhugel.model.WheatPrice;
import gruner.huger.grunerhugel.model.compositekey.WheatPriceId;
import jakarta.persistence.EmbeddedId;

@SpringBootTest
class WheatPriceTest {
    @EmbeddedId
    private WheatPriceId wheatPriceId;

    @Test
    void testGetSet() {
        WheatPrice wheatPrice = new WheatPrice();
        wheatPrice.setWheatPriceId(wheatPriceId);
        wheatPrice.setPrice(100);
        assertEquals(wheatPrice.getWheatPriceId(), wheatPriceId);
        // assertEquals(wheatPrice.getPrice(), 100);
    }

    @Test
    void testHashCode() {
        WheatPrice wheatPrice = new WheatPrice();
        assertEquals(961, wheatPrice.hashCode());
    }

}
