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
import unsw.backend.Unit;
import unsw.gloriaromanus.*;

public class TestMine {
    @Test
    public void testMineBonus() {
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
        for (Infrastructure i : v.getBuildinCons()) {
            assertEquals(i.getBuildTime(), 2);
        }
        newGame.nextTurn();
        for (Infrastructure i : v.getBuildinCons()) {
            assertEquals(i.getBuildTime(), 1);
        }
        constuct = v.checkIfBuildingExistsB("Mine");
        assertFalse(constuct);
        newGame.nextTurn();
        constuct = v.checkIfBuildingExistsB("Mine");
        assertTrue(constuct);
        for (Infrastructure i : v.getBuildings()) {
            assertEquals(i.getLevel(), 1);
            assertEquals(i.getProvince(), v);
            assertFalse(i.checkifmax());
            assertEquals(i.getType(), "Mine");
        }
        constuct = newGame.bulid(temp, "Markettt", "V");
        assertFalse(constuct);
        constuct = newGame.bulid(temp, "Market", "V");
        assertTrue(constuct);
        constuct = newGame.bulid(temp, "Road", "V");
        assertTrue(constuct);
        constuct = v.checkIfBuildingExistsA("Road");
        assertTrue(constuct);
        constuct = newGame.bulid(temp, "TroopProduction", "V");
        assertTrue(constuct);
        int counter = 0;
        for (Infrastructure i : v.getBuildinCons()) {
            counter++;
            if (i.getType().equals("Market")) {
                assertEquals(i.getBuildTime(), 2);
            } else if (i.getType().equals("Road")) {
                assertEquals(i.getBuildTime(), 1);
            } else {
                assertEquals(i.getBuildTime(), 2);
            }
        }
        assertEquals(counter, 3);
        newGame.nextTurn();
        constuct = v.checkIfBuildingExistsA("Road");
        assertFalse(constuct);
        constuct = v.checkIfBuildingExistsB("Road");
        assertTrue(constuct);
        counter = 0;
        for (Infrastructure i : v.getBuildinCons()) {
            counter++;
            if (i.getType().equals("Market")) {
                assertEquals(i.getBuildTime(), 1);
            } else {
                assertEquals(i.getBuildTime(), 1);
            }
        } 
        assertEquals(counter, 2);
        newGame.nextTurn();
        constuct = v.checkIfBuildingExistsB("Market");
        assertTrue(constuct);
        constuct = v.checkIfBuildingExistsB("TroopProduction");
        assertTrue(constuct);
        constuct = v.checkIfBuildingExistsA("Market");
        assertFalse(constuct);
        constuct = v.checkIfBuildingExistsA("TroopProduction");
        assertFalse(constuct);
        constuct = newGame.bulid(temp, "Market", "V");
        assertFalse(constuct); //cant build duplicate buildings.
    }

    @Test
    public void testMineUpgrade() {
        GameController newGame = new GameController();
        Player temp = newGame.setPlayer("Rome");
        newGame.bulid(temp, "Mine", "V");
        newGame.bulid(temp, "Mine", "VI");
        Province v = newGame.getProvinceFromString("V");
        Province vi = newGame.getProvinceFromString("VI");
        newGame.nextTurn();
        newGame.nextTurn();
        newGame.upgrade(temp, "Mine", "V");
        newGame.upgrade(temp, "Mine", "V");
        for (Infrastructure i : v.getBuildings()) {
            assertEquals(i.getLevel(), 3);
        }
        for (Infrastructure i : vi.getBuildings()) {
            assertEquals(i.getLevel(), 1);
        }
        boolean constuct = newGame.bulid(temp, "TroopProduction", "V");
        assertTrue(constuct);
        constuct = newGame.bulid(temp, "TroopProduction", "VI");
        assertTrue(constuct);
        for (Infrastructure i : v.getBuildinCons()) {
            assertEquals(i.getBuildTime(), 1);
        }
        newGame.nextTurn();
        for (Infrastructure i : vi.getBuildings()) {
            if(i.getType().equals("TroopProduction")) {
                assertEquals(i.getBuildTime(), 2);
            }
        }
    }

    @Test
    public void testSoldierCreation() {
        GameController newGame = new GameController();
        Player temp = newGame.setPlayer("Rome");
        Province v = newGame.getProvinceFromString("V");
        Province vi = newGame.getProvinceFromString("VI");
        Unit x = newGame.createUnit("V", "Spearman", "sps");
        newGame.addsolider(temp, "V", x, num)
    }
}
