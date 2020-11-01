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
    public void calculateWealth() {
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
    
}
