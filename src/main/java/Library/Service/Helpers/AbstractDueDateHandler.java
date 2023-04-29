package Library.Service.Helpers;

import Library.Service.Helpers.Interface.IDueDateHandler;

public abstract class AbstractDueDateHandler implements IDueDateHandler {
    protected IDueDateHandler nextHandler;

    public void setNextHandler(IDueDateHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}