package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import unsw.backend.ConstructionFactory;
import unsw.backend.Player;
import unsw.backend.Province;
import unsw.gloriaromanus.*;

public class TestMine {
    @Test
    public void test1() {
        Player play = new Player("Roman");
        Province p = new Province("Rome", "Roman");
        ConstructionFactory factory = new ConstructionFactory();
        factory.constructNewBuilding("Mine", p, play);
        boolean works = p.checkIfBuildingExistsA("Mine");
        assertTrue(works);
    }

    @Test
    public void test2() {
        Player play = new Player("Roman");
        String faction = play.getFaction();
        assertEquals(faction, "Roman");
    }
}
