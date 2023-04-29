package Library.Service.Helpers;

import Library.Service.Helpers.Interface.IDueDateHandler;

public class DueDateHandler {
    public static IDueDateHandler getDueDateHandler() {
        IDueDateHandler fantasyDueDateHandler = new FantasyDueDateHandler();
        IDueDateHandler biographyDueDateHandler = new BiographyDueDateHandler();
        IDueDateHandler mysteryDueDateHandler = new MysteryDueDateHandler();
        IDueDateHandler defaultDueDateHandler = new DefaultDueDateHandler();

        fantasyDueDateHandler.setNextHandler(biographyDueDateHandler);
        biographyDueDateHandler.setNextHandler(mysteryDueDateHandler);
        mysteryDueDateHandler.setNextHandler(defaultDueDateHandler);

        return fantasyDueDateHandler;
    }
}