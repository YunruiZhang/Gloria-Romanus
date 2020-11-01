package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import unsw.backend.*;


public class TestMove{
    @Test
    public void testRoad(){
        GameController newGame = new GameController();
        Player temp1 = newGame.setPlayer("Gaul");
        Province BEP = newGame.getProvinceFromString("Bithynia et Pontus");
        Unit x1 = newGame.createUnit("Bithynia et Pontus", "Crossbowman", "sps");
        Unit y1 = newGame.createUnit("Bithynia et Pontus", "Druid", "meep");
        newGame.bulid(temp1, "TroopProduction", "Bithynia et Pontus");
        newGame.nextTurn();
        newGame.nextTurn();
        newGame.nextTurn();
        newGame.addsolider(temp1, "Bithynia et Pontus", x1, 7);
        newGame.addsolider(temp1, "Bithynia et Pontus", x1, 2);
        newGame.addsolider(temp1, "Bithynia et Pontus", x1, 4);
        ArrayList<Object[]> trains1 = BEP.getSoldierTraining();
        assertEquals(trains1.size(), 3);
        assertEquals(x1.getSoldiers(), 0);
        newGame.nextTurn();
        assertEquals(trains1.size(), 1);
        assertEquals(x1.getSoldiers(), 9);
        newGame.nextTurn();
        assertEquals(trains1.size(), 0);
        assertEquals(x1.getSoldiers(), 13);
        newGame.addsolider(temp1, "Bithynia et Pontus", y1, 6);
        assertEquals(trains1.size(), 0);
        assertEquals(y1.getSoldiers(), 0);
        newGame.upgrade(temp1, "TroopProduction", "Bithynia et Pontus");
        newGame.addsolider(temp1, "Bithynia et Pontus", y1, 6);
        assertEquals(trains1.size(), 1);
        assertEquals(y1.getSoldiers(), 0);
        newGame.nextTurn();
        newGame.nextTurn();
        newGame.nextTurn();
        newGame.nextTurn();
        assertEquals(trains1.size(), 0);
        assertEquals(y1.getSoldiers(), 6);
        newGame.bulid(temp1, "Road", "Asia");
        newGame.nextTurn();
        
        ArrayList<Unit> Army = new ArrayList<Unit>();
        Army.add(x1);
        Army.add(y1);
        newGame.MoveUnit(temp1, Army, "Bithynia et Pontus", "Africa Proconsularis");
        newGame.MoveUnit(temp1, Army, "Bithynia et Pontus", "Asia");
        newGame.upgrade(temp1, "Road", "Asia");
        newGame.nextTurn();
        newGame.MoveUnit(temp1, Army, "Asia", "Bithynia et Pontus");
        newGame.nextTurn();
        newGame.MoveUnit(temp1, Army, "Bithynia et Pontus", "Asia");
    }
}
