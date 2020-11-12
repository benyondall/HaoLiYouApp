package e.commerce.shopping.guide.main.ui.user.model;

public class BeanRespRecentlyViewed {


    public BeanRespRecentlyViewed(String _name){
        setName(_name);
    }
    private String name;
    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
