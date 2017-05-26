package wang.relish.whateat.seller.bean;

import wang.relish.ugo.db.DataSupportCompat;

/**
 * <pre>
 *     author : 王鑫
 *     e-mail : wangxin@souche.com
 *     time   : 2017/05/18
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class Good extends DataSupportCompat<Good> {

    private String pic;
    private String name;
    private String kind;
    private double price;
    private String pUnit;
    private double inventory;
    private String iUnit;

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getpUnit() {
        return pUnit;
    }

    public void setpUnit(String pUnit) {
        this.pUnit = pUnit;
    }

    public double getInventory() {
        return inventory;
    }

    public void setInventory(double inventory) {
        this.inventory = inventory;
    }

    public String getiUnit() {
        return iUnit;
    }

    public void setiUnit(String iUnit) {
        this.iUnit = iUnit;
    }
}
