package Library.Service.Helpers;

public class BiographyDueDateHandler extends AbstractDueDateHandler {
    public int getNumberOfDaysAllowed(String genre) {
        if (genre.toLowerCase().equals("biography")) {
            return 14;
        } else {
            return this.nextHandler.getNumberOfDaysAllowed(genre);
        }
    }
}
