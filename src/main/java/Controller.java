import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 */
public class Controller implements Initializable {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日");
    @FXML
    public TextField text_year;
    @FXML
    public ChoiceBox<Integer> box_month;
    @FXML
    public TableView table;
    @FXML
    public RadioButton radio_sun;
    @FXML
    public RadioButton radio_mon;
    @FXML
    public RadioButton radio_tues;
    @FXML
    public RadioButton radio_wed;
    @FXML
    public RadioButton radio_thur;
    @FXML
    public RadioButton radio_fri;
    @FXML
    public RadioButton radio_sat;
    @FXML
    public TextArea text_weeks;
    @FXML
    public ChoiceBox<String> label_begin;
    @FXML
    public ChoiceBox<String> label_end;
    @FXML
    public TextField text_student;
    @FXML
    public ChoiceBox<String> box_subject;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // 表格字符工厂
        ObservableList<TableColumn> columns = table.getColumns();
        columns.get(0).setCellValueFactory(new PropertyValueFactory<>("SUNDAY"));
        columns.get(1).setCellValueFactory(new PropertyValueFactory<>("MONDAY"));
        columns.get(2).setCellValueFactory(new PropertyValueFactory<>("TUESDAY"));
        columns.get(3).setCellValueFactory(new PropertyValueFactory<>("WEDNESDAY"));
        columns.get(4).setCellValueFactory(new PropertyValueFactory<>("THURSDAY"));
        columns.get(5).setCellValueFactory(new PropertyValueFactory<>("FRIDAY"));
        columns.get(6).setCellValueFactory(new PropertyValueFactory<>("SATURDAY"));

        // 初始化年
        Calendar now = CalendarHelper.get();
        text_year.setText(String.valueOf(now.get(Calendar.YEAR)));

        // 初始化月
        box_month.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
        box_month.setValue(now.get(Calendar.MONTH) + 1);

        ObservableList<String> times = getTimeItems();
        // 初始化开始时间
        label_begin.setItems(times);
        // 初始化结束时间
        label_end.setItems(times);

        // 学科初始化
        box_subject.setItems(FXCollections.observableArrayList("语文", "数学", "英语", "科学", "社会", "历史", "地理", "政治", "物理", "化学", "生物", "技术"));
    }

    private ObservableList<String> getTimeItems() {
        ObservableList<String> times = FXCollections.observableArrayList("");
        for (int i = 6; i < 24; i++) {
            times.add(i + ":00");
            times.add(i + ":30");
        }
        return times;
    }

    /**
     * 日期变化
     *
     * @param actionEvent
     */
    public void map_show(ActionEvent actionEvent) {
        Integer[][] map = CalendarHelper.map(Integer.parseInt(text_year.getText()), box_month.getValue());
        ObservableList<WeekLine> weekLines = FXCollections.observableArrayList(//
                new WeekLine(map[0]),//
                new WeekLine(map[1]),//
                new WeekLine(map[2]),//
                new WeekLine(map[3]),//
                new WeekLine(map[4]),//
                new WeekLine(map[5]));
        table.setItems(weekLines);

        text_show(actionEvent);
    }

    /**
     * 星期选择
     *
     * @param actionEvent
     */
    public void text_show(ActionEvent actionEvent) {
        HashSet<Integer> chooces = new HashSet<>();
        if (radio_sun.isSelected()) {
            chooces.add(Calendar.SUNDAY);
        }
        if (radio_mon.isSelected()) {
            chooces.add(Calendar.MONDAY);
        }
        if (radio_tues.isSelected()) {
            chooces.add(Calendar.TUESDAY);
        }
        if (radio_wed.isSelected()) {
            chooces.add(Calendar.WEDNESDAY);
        }
        if (radio_thur.isSelected()) {
            chooces.add(Calendar.THURSDAY);
        }
        if (radio_fri.isSelected()) {
            chooces.add(Calendar.FRIDAY);
        }
        if (radio_sat.isSelected()) {
            chooces.add(Calendar.SATURDAY);
        }

        ArrayList<Calendar> weeks = CalendarHelper.choose(Integer.parseInt(text_year.getText()), box_month.getValue(), chooces);
        String text = weeks.stream().map(this::format).collect(Collectors.joining(" \n"));
        text_weeks.setText(text);
    }

    private String format(Calendar calendar) {
        StringJoiner joiner = new StringJoiner(" ");
        joiner.add(simpleDateFormat.format(calendar.getTime()));

        Optional.ofNullable(label_begin.getValue()).filter(bv -> !"null".equals(bv) && !"".equals(bv))
                .ifPresent(bv -> Optional.ofNullable(label_end.getValue()).filter(ev -> !"null".equals(ev) && !"".equals(ev)).ifPresent(ev -> joiner.add(bv + "-" + ev)));

        Optional.ofNullable(box_subject.getValue()).ifPresent(joiner::add);
        Optional.ofNullable(text_student.getText()).filter(s -> !s.isEmpty()).ifPresent(joiner::add);
        return joiner.toString();
    }

    public void begin_time_clicked() {
        ObservableList<String> newList = FXCollections.observableArrayList();
        String begin_v = label_begin.getValue();
        if (null == begin_v) {
            return;
        }
        getTimeItems().forEach(next -> {
            if (null != next && next.compareTo(begin_v) > 0) {
                newList.add(next);
            }
        });

        label_end.setItems(newList);
    }

    public void end_time_clicked() {
        ObservableList<String> newList = FXCollections.observableArrayList();
        String end_v = label_begin.getValue();
        if (null == end_v) {
            return;
        }
        getTimeItems().forEach(next -> {
            if (null != next && next.compareTo(end_v) < 0) {
                newList.add(next);
            }
        });

        label_begin.setItems(newList);
    }
}
