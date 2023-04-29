package Library.Service.Helpers;

public class DefaultDueDateHandler extends AbstractDueDateHandler {
    public int getNumberOfDaysAllowed(String genre) {
        return 7;
    }
}