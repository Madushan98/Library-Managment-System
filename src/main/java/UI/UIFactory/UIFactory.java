package UI.UIFactory;

import Enums.UIType;
import Library.Service.LibraryService;
import UI.CLI.LibraryCLI;
import UI.GUI.LibraryGUI;
import UI.UI;

public class UIFactory {

    private final LibraryService bookLibraryService;

    public UIFactory(LibraryService bookLibraryService) {
        this.bookLibraryService = bookLibraryService;
    }

    public UI getUI(UIType type) {
        if (type == UIType.GUI) {
            return new LibraryGUI(bookLibraryService);
        }
        return new LibraryCLI(bookLibraryService);

    }
}
