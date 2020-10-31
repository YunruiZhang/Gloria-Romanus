package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import unsw.backend.ConstructionFactory;
import unsw.backend.GameController;
import unsw.backend.Infrastructure;
import unsw.backend.Player;
import unsw.backend.Province;
import unsw.gloriaromanus.*;

public class TestMine {
    @Test
    public void test1() {
        GameController newGame = new GameController();
        Player temp = newGame.setPlayer("Rome");
        boolean constuct = newGame.bulid(temp, "Mine", "V");
        assertTrue(constuct);
        constuct = newGame.bulid(temp, "Mine", "V");
        assertFalse(constuct); //cant build duplicate buildings.
        Province v = newGame.getProvinceFromString("V");
        constuct = v.checkIfBuildingExistsA("Mine");
        assertTrue(constuct);
        constuct = newGame.upgrade(temp, "Mine", "V");
        assertFalse(constuct); //can't upgrade building before it is done being constructed.
        for (Infrastructure i : v.getBBBB()) {
            System.out.println(i.getBuildTime());
        }
        newGame.nextTurn();
        //constuct = v.checkIfBuildingExistsB("Mine");
        //assertTrue(constuct);

    }

    @Test
    public void test2() {
        Player play = new Player("Roman");
        String faction = play.getFaction();
        assertEquals(faction, "Roman");
    }
}
