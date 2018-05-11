package a307a.program.GUI.MenuBar;

import a307a.program.GUI.FileStorage;
import a307a.program.GUI.GraphicsManager;
import a307a.program.GUI.MenuBar.settings.SettingsMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class AccessBar {
    private MenuBar menuBar;
    private Menu menuFile;
    private MenuItem addSrcFile;
    private MenuItem removeFile;
    private MenuItem addCompFile;
    private Menu menuSetting;
    private MenuItem addSetting;



    public AccessBar() {
        menuBar = new MenuBar();
        menuFile = new Menu("Files");
        addSrcFile = new MenuItem("Add Source");
        removeFile = new MenuItem("Remove");
        addCompFile = new MenuItem("Add Comparison");
        menuSetting = new Menu("Window");
        addSetting = new MenuItem("Settings");
        menuBar.getMenus().addAll(menuFile, menuSetting);
        setSettingsMenuItemFunctionality();
    }

    public void setFileMenuItemFunctionality(FileStorage selectedFiles, GraphicsManager graphicsManager){
        addSrcFile.setOnAction(event -> { graphicsManager.loadFile(selectedFiles.getSrcFileList(), selectedFiles.getSrcMidiFiles()); });
        addCompFile.setOnAction(event -> { graphicsManager.loadFile(selectedFiles.getCompFileList(), selectedFiles.getCompMidiFiles()); });
        removeFile.setOnAction(event -> { new FileListEditor(
                selectedFiles.getSrcMidiFiles(),
                selectedFiles.getCompMidiFiles(),
                graphicsManager.getElementHolder()); });
        menuFile.getItems().addAll(addSrcFile, addCompFile, removeFile);
    }

    private void setSettingsMenuItemFunctionality(){
        addSetting.setOnAction(event -> {
            String version = "???";
            SettingsMenu.WindowSettings(version);
        });
        menuSetting.getItems().add(addSetting);
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }
}
