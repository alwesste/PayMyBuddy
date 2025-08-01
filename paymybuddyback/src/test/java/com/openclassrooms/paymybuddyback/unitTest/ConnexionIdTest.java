package com.openclassrooms.paymybuddyback.unitTest;


import com.openclassrooms.paymybuddyback.models.ConnexionId;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ConnexionIdTest {

    @Test
    void shouldReturnTrueForSameValue() {
        ConnexionId connexionId1 = new ConnexionId(1,2);
        ConnexionId connexionId2 = new ConnexionId(1,2);

        assertEquals(connexionId1, connexionId2);
        assertEquals(connexionId1.hashCode(), connexionId2.hashCode());
    }

    @Test
    void shouldReturnFalseForWrongValue() {
        ConnexionId connexionId1 = new ConnexionId(1,2);
        ConnexionId connexionId2 = new ConnexionId(2,1);

        assertNotEquals(connexionId1, connexionId2);
        assertNotEquals(connexionId1.hashCode(), connexionId2.hashCode());
    }
}
