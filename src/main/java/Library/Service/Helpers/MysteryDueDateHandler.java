package Library.Service.Helpers;

public class MysteryDueDateHandler extends AbstractDueDateHandler {
    public int getNumberOfDaysAllowed(String genre) {
        if (genre.toLowerCase().equals("mystery")) {
            return 12;
        } else {
            return this.nextHandler.getNumberOfDaysAllowed(genre);
        }
    }
}
