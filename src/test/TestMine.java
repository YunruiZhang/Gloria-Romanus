package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import unsw.backend.ConstructionFactory;
import unsw.backend.GameController;
import unsw.backend.Infrastructure;
import unsw.backend.Market;
import unsw.backend.Mine;
import unsw.backend.Player;
import unsw.backend.Province;
import unsw.backend.TroopProduction;
import unsw.backend.Unit;
//import unsw.gloriaromanus.*;

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
        Unit x = newGame.createUnit("V", "Crossbowman", "sps");
        Unit y = newGame.createUnit("V", "Druid", "meep");
        newGame.bulid(temp, "TroopProduction", "V");
        newGame.nextTurn();
        newGame.nextTurn();
        newGame.nextTurn();
        newGame.addsolider(temp, "V", x, 7);
        newGame.addsolider(temp, "V", x, 2);
        newGame.addsolider(temp, "V", x, 4);
        ArrayList<Object[]> trains = v.getSoldierTraining();
        assertEquals(trains.size(), 3);
        assertEquals(x.getSoldiers(), 0);
        newGame.nextTurn();
        assertEquals(trains.size(), 1);
        assertEquals(x.getSoldiers(), 9);
        newGame.nextTurn();
        assertEquals(trains.size(), 0);
        assertEquals(x.getSoldiers(), 13);
        newGame.addsolider(temp, "V", y, 6);
        assertEquals(trains.size(), 0);
        assertEquals(y.getSoldiers(), 0);
        newGame.upgrade(temp, "TroopProduction", "V");
        newGame.addsolider(temp, "V", y, 6);
        assertEquals(trains.size(), 1);
        assertEquals(y.getSoldiers(), 0);
        newGame.nextTurn();
        newGame.nextTurn();
        newGame.nextTurn();
        newGame.nextTurn();
        assertEquals(trains.size(), 0);
        assertEquals(y.getSoldiers(), 6);
    }

    @Test
    public void taxTest() {
        GameController newGame = new GameController();
        Player temp = newGame.setPlayer("Rome");
        assertEquals(temp.getGold(), 10000);
        Province v = newGame.getProvinceFromString("V");
        Unit x = newGame.createUnit("V", "Crossbowman", "sps");
        newGame.bulid(temp, "TroopProduction", "V");
        assertEquals(temp.getGold(), 8000); 
        assertEquals(v.getProvinceWealth(), 0);
        assertEquals(v.getTaxRate(), 15);
        newGame.nextTurn();
        assertEquals(temp.getGold(), 8000);
        assertEquals(v.getProvinceWealth(), 0);
        v.decreaseTaxRate();
        assertEquals(v.getTaxRate(), 10);
        newGame.nextTurn();
        assertEquals(v.getProvinceWealth(), 10);
        assertEquals(temp.getGold(), 8001); 
        newGame.nextTurn();
        assertEquals(v.getProvinceWealth(), 720);
        assertEquals(temp.getGold(), 8073); 
        v.increaseTaxRate();
        assertEquals(v.getTaxRate(), 15); 
        newGame.addsolider(temp, "V", x, 7);
        newGame.addsolider(temp, "V", x, 2);
        newGame.addsolider(temp, "V", x, 4);
        assertEquals(temp.getGold(), 7423);
        assertEquals(v.getProvinceWealth(), 720);
        newGame.nextTurn();
        assertEquals(v.getProvinceWealth(), 765);
        assertEquals(v.getTaxRate(), 15); 
        //assertEquals(temp.getGold(), 7537.75);
        assertEquals(temp.getGold(), 7531);
        v.increaseTaxRate();
        assertEquals(v.getTaxRate(), 20);
        newGame.nextTurn();
        assertEquals(v.getProvinceWealth(), 775);
        newGame.upgrade(temp, "TroopProduction", "V");
        assertEquals(v.getProvinceWealth(), 1075);
    }

    @Test
    public void testMarketUpgrade() {
        GameController newGame = new GameController();
        Player temp = newGame.setPlayer("Rome");
        assertEquals(temp.getGold(), 10000);
        Province v = newGame.getProvinceFromString("V");
        newGame.bulid(temp, "Market", "V");
        assertEquals(temp.getGold(), 8000); 
        assertEquals(v.getProvinceWealth(), 0);
        newGame.nextTurn();
        newGame.nextTurn();
        newGame.nextTurn();
        assertEquals(v.getProvinceWealth(), 700);
        assertEquals(temp.getGold(), 8105);
        assertFalse(newGame.bulid(temp, "Market", "V"));
        newGame.bulid(temp, "Mine", "V");
        assertEquals(temp.getGold(), 6305); 
        newGame.nextTurn();
        assertEquals(temp.getGold(), 6410); 
        newGame.nextTurn();
        assertEquals(temp.getGold(), 6620); 
        assertEquals(v.getProvinceWealth(), 1400);
        newGame.upgrade(temp, "Market", "V");
        assertEquals(v.getProvinceWealth(), 1700);
        assertEquals(temp.getGold(), 5720); 
        newGame.nextTurn();
        assertEquals(temp.getGold(), 5975); 
        newGame.upgrade(temp, "Mine", "V");
        assertEquals(temp.getGold(), 5175);
        newGame.upgrade(temp, "Market", "V");
        assertEquals(temp.getGold(), 4375);
        for (Infrastructure i : v.getBuildings()) {
            if (i instanceof Market) {
                assertEquals(i.getLevel(), 3);
            }
        }
    }

    @Test
    public void testShieldsNstuff() {
        GameController newGame = new GameController();
        Player temp = newGame.setPlayer("Rome");
        assertEquals(temp.getGold(), 10000);
        Province v = newGame.getProvinceFromString("V");
        Unit x = newGame.createUnit("V", "Crossbowman", "sps");
        Unit xi = newGame.createUnit("V", "NetFighter", "sppp");
        Unit xii = newGame.createUnit("V", "Chariot", "sas");
        Unit xiii = newGame.createUnit("V", "Elephant", "aa");
        Unit xiv = newGame.createUnit("V", "MissileMan", "ss");
        newGame.bulid(temp, "TroopProduction", "V");
        newGame.nextTurn();
        newGame.nextTurn();
        newGame.nextTurn();
        assertEquals(v.getBuildings().size(), 1);
        newGame.upgrade(temp, "TroopProduction", "V");
        newGame.upgrade(temp, "TroopProduction", "V");
        newGame.upgrade(temp, "TroopProduction", "V");
        newGame.upgrade(temp, "TroopProduction", "V");
        newGame.upgrade(temp, "TroopProduction", "V");
        for (Infrastructure i : v.getBuildings()) {
            if (i.getType().equals("Mine")) assertEquals(i.getLevel(), 99);
        }
        System.out.println(temp.getGold() + " t1");
        assertTrue(newGame.addsolider(temp, "V", x, 7));
        System.out.println(temp.getGold() + " t2");
        assertTrue(newGame.addsolider(temp, "V", xi, 2));
        System.out.println(temp.getGold() + " t3");
        assertTrue(newGame.addsolider(temp, "V", xii, 4));
        System.out.println(temp.getGold() + " t4");
        assertTrue(newGame.addsolider(temp, "V", xiii, 5));
        System.out.println(temp.getGold() + " t5");
        assertTrue(newGame.addsolider(temp, "V", xiv, 1));
        System.out.println(temp.getGold() + " t6");
        ArrayList<Object[]> trains = v.getSoldierTraining();
        assertEquals(trains.size(), 5);
        assertEquals(x.getSoldiers(), 0);
        newGame.nextTurn();
        assertEquals(trains.size(), 4);
        assertEquals(x.getSoldiers(), 7);
        newGame.nextTurn();
        assertEquals(trains.size(), 2);
        newGame.nextTurn();
        newGame.nextTurn();
        assertEquals(trains.size(), 0);
        assertEquals(x.getSoldiers(), 7);
        assertEquals(xi.getSoldiers(), 2);
        assertEquals(xii.getSoldiers(), 4);
        assertEquals(xiii.getSoldiers(), 5);
        assertEquals(xiv.getSoldiers(), 1);
    }
}
