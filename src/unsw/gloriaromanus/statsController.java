package unsw.gloriaromanus;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class statsController extends MenuController{
    @FXML
    private Label P1gold;
    @FXML
    private Label P2gold;
    @FXML
    private Label WEALTHBOX1;
    @FXML
    private Label WEALTHBOX2;
    @FXML
    private Label OWNEDPROVS1;
    @FXML
    private Label OWNEDPROVS2;
    @FXML 
    private Label goalsP1;
    @FXML 
    private Label goalsP2;
    @FXML
    private TextField bankBorrowAmount;
    @FXML
    private TextField bankreturnAmount1;
    @FXML 
    private Label p1amtdue;
    @FXML 
    private Label p2amtdue;

    public void updateGoldAmt(ActionEvent e) throws Exception {
        int goldA = getParent().getGoldAmount(e, 1);
        int goldB = getParent().getGoldAmount(e, 2);
        P1gold.setText(Integer.toString(goldA));
        P2gold.setText(Integer.toString(goldB));
        int wealthA = getParent().gettotalwealth(e, 1);
        int wealthB = getParent().gettotalwealth(e, 2);
        WEALTHBOX1.setText(Integer.toString(wealthA));
        WEALTHBOX2.setText(Integer.toString(wealthB));
        int provsA = getParent().getNumberProvinces(e, 1);
        int provsB = getParent().getNumberProvinces(e, 2);
        OWNEDPROVS1.setText(Integer.toString(provsA));
        OWNEDPROVS2.setText(Integer.toString(provsB));
        //goalsP1
        String goalsA = getParent().getGoal(1);
        String goalsB = getParent().getGoal(2);
        goalsP1.setText(goalsA);
        goalsP2.setText(goalsB);
        int loangoldA = getParent().getLoan(1);
        int loangoldB = getParent().getLoan(2);
        p1amtdue.setText(Integer.toString(loangoldA));
        p2amtdue.setText(Integer.toString(loangoldB));
    }

    @FXML
    public void bankBorrowp1(ActionEvent e) throws Exception {
        int qty = Integer.parseInt(bankBorrowAmount.getText());
        getParent().borrowMoney(e, qty, 1);
        updateGoldAmt(e);
    }

    @FXML
    public void bankBorrowp2(ActionEvent e) throws Exception {
        int qty = Integer.parseInt(bankBorrowAmount.getText());
        getParent().borrowMoney(e, qty, 2);
        updateGoldAmt(e);
    }

    @FXML
    public void bankretp1(ActionEvent e) throws Exception {
        int qty = Integer.parseInt(bankreturnAmount1.getText());
        getParent().paybackmoney(e, qty, 1);
        updateGoldAmt(e);
    }

    @FXML
    public void bankretp2(ActionEvent e) throws Exception {
        int qty = Integer.parseInt(bankreturnAmount1.getText());
        getParent().paybackmoney(e, qty, 2);
        updateGoldAmt(e);
    }
}
