package Library.Service.Helpers;

public class FantasyDueDateHandler extends AbstractDueDateHandler {
    public int getNumberOfDaysAllowed(String genre) {
        if (genre.toLowerCase().equals("fantasy")) {
            return 10;
        } else {
            return this.nextHandler.getNumberOfDaysAllowed(genre);
        }
    }
}