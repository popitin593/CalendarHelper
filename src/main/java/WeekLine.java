import java.util.Optional;

import javafx.beans.property.SimpleStringProperty;

/**
 * @author JiangYangfan
 * @version $Id$
 * @since 2020/12/31 5:17 下午
 */
public class WeekLine {

    private final SimpleStringProperty SUNDAY;
    private final SimpleStringProperty MONDAY;
    private final SimpleStringProperty TUESDAY;
    private final SimpleStringProperty WEDNESDAY;
    private final SimpleStringProperty THURSDAY;
    private final SimpleStringProperty FRIDAY;
    private final SimpleStringProperty SATURDAY;

    public WeekLine(Integer[] args) {
        this.SUNDAY = new SimpleStringProperty(str(args[0]));
        this.MONDAY = new SimpleStringProperty(str(args[1]));
        this.TUESDAY = new SimpleStringProperty(str(args[2]));
        this.WEDNESDAY = new SimpleStringProperty(str(args[3]));
        this.THURSDAY = new SimpleStringProperty(str(args[4]));
        this.FRIDAY = new SimpleStringProperty(str(args[5]));
        this.SATURDAY = new SimpleStringProperty(str(args[6]));
    }

    private String str(Object o) {
        return Optional.ofNullable(o).orElse("").toString();
    }

    public String getSUNDAY() {
        return SUNDAY.get();
    }

    public void setSUNDAY(String SUNDAY) {
        this.SUNDAY.set(SUNDAY);
    }

    public SimpleStringProperty SUNDAYProperty() {
        return SUNDAY;
    }

    public String getMONDAY() {
        return MONDAY.get();
    }

    public void setMONDAY(String MONDAY) {
        this.MONDAY.set(MONDAY);
    }

    public SimpleStringProperty MONDAYProperty() {
        return MONDAY;
    }

    public String getTUESDAY() {
        return TUESDAY.get();
    }

    public void setTUESDAY(String TUESDAY) {
        this.TUESDAY.set(TUESDAY);
    }

    public SimpleStringProperty TUESDAYProperty() {
        return TUESDAY;
    }

    public String getWEDNESDAY() {
        return WEDNESDAY.get();
    }

    public void setWEDNESDAY(String WEDNESDAY) {
        this.WEDNESDAY.set(WEDNESDAY);
    }

    public SimpleStringProperty WEDNESDAYProperty() {
        return WEDNESDAY;
    }

    public String getTHURSDAY() {
        return THURSDAY.get();
    }

    public void setTHURSDAY(String THURSDAY) {
        this.THURSDAY.set(THURSDAY);
    }

    public SimpleStringProperty THURSDAYProperty() {
        return THURSDAY;
    }

    public String getFRIDAY() {
        return FRIDAY.get();
    }

    public void setFRIDAY(String FRIDAY) {
        this.FRIDAY.set(FRIDAY);
    }

    public SimpleStringProperty FRIDAYProperty() {
        return FRIDAY;
    }

    public String getSATURDAY() {
        return SATURDAY.get();
    }

    public void setSATURDAY(String SATURDAY) {
        this.SATURDAY.set(SATURDAY);
    }

    public SimpleStringProperty SATURDAYProperty() {
        return SATURDAY;
    }
}
