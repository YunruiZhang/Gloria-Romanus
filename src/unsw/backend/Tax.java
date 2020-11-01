package unsw.backend;

public class Tax {
    private int taxRate;
    private Province province;
    
    public Tax(Province province) {
        this.province = province;
        this.taxRate = 15;
    }

    /**
     * changes the wealth every turn according to the tax rate.
     */
    public void CalculateWealth() {
        if (taxRate == 10) {
            province.provinceWealthAdder(10);
        } else if (taxRate == 15) {
            province.provinceWealthAdder(0);
        } else if (taxRate == 20) {
            if (province.getProvinceWealth()-10 < 0) {
                province.setWealthprov(0);
            } else {
                province.provinceWealthAdder(-10);
            }
        } else if (taxRate == 25) {
            if (province.getProvinceWealth()-30 < 0) {
                province.setWealthprov(0);
            } else {
                province.provinceWealthAdder(-30);
            }
            province.decreaseAllMorale();
        }
    }

    /**
     * @return double return the taxRate
     */
    public double GetTaxRate() {
        return taxRate;
    }

    /**
     * the taxRate to set
     */
    public void IncreaseTaxRate() {
        if (taxRate < 25) {
            taxRate += 5;
        } else {
            //System.out.println("tax rate has reached its limit");
        }
    }

    /**
     * the taxRate to set
     */
    public void DecreaseTaxRate() {
        if (taxRate > 10) {
            taxRate -= 5;
        } else {
            //System.out.println("tax rate is already minimum");
        }
    }

    /**
     * @param level sets a particular tax rate given by the user.
     */
    public void SetTax(int level){
        switch(level){
            case 1:
                taxRate = 10;
                break;
            case 2:
                taxRate = 15;
                break;
            case 3: 
                taxRate = 20;
                break;
            case 4:
                taxRate = 25;
                break;
            default:
                //System.out.println("WrongNumber"); //JAVAFX ~~~~~~~~~~~
                break;
        }
    }
    
}
