package Library.Service.Helpers.Interface;

public interface IDueDateHandler {
    void setNextHandler(IDueDateHandler handler);

    int getNumberOfDaysAllowed(String genre);
}
