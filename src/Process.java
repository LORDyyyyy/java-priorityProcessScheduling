import javafx.scene.control.TextField;

public class Process {
    private int proNo;
    private TextField priority;
    private TextField arrivalTime;
    private TextField burstTime;

    public Process(String priority, String arrivalTime, String burstTime, int proNo) {

        this.proNo += 1;

        this.priority = new TextField(priority);
        this.arrivalTime = new TextField(arrivalTime);
        this.burstTime = new TextField(burstTime);
        this.proNo = proNo;

        this.priority.setMaxWidth(50);
        this.arrivalTime.setMaxWidth(50);
        this.burstTime.setMaxWidth(50);
    }

    public boolean validate() {
        if (priority.getText().matches("^(?!0)\\d+$")
                && arrivalTime.getText().matches("^(?!0)\\d+$")
                && burstTime.getText().matches("^(?!0)\\d+$"))
            return true;
        return false;
    }

    public int getProNo() {
        return proNo;
    }

    public void setProNo(int proNo) {
        this.proNo = proNo;
    }

    public int getProName() {
        return proNo;
    }

    public TextField getPriority() {
        return priority;
    }

    public void setPriority(TextField priority) {
        this.priority = priority;
    }

    public TextField getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(TextField arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public TextField getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(TextField burstTime) {
        this.burstTime = burstTime;
    }
}