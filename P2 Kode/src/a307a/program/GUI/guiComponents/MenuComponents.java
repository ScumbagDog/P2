package a307a.program.GUI.guiComponents;

import a307a.program.GUI.FileCollectionComponents;
import a307a.program.GUI.IStatusBar;
import a307a.program.GUI.MenuBar.FileListEditor;
import a307a.program.GUI.MenuBar.MidiFile;
import a307a.program.GUI.MenuBar.settings.SettingsMenu;
import a307a.program.GUI.Splits.FileList;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import javax.sound.midi.InvalidMidiDataException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class MenuComponents implements IStatusBar{
	private FileCollectionComponents fileCollectionComponents;
	private MenuBar menuBar = new MenuBar();
	private Menu menuFileButton = new Menu("Files");
	private MenuItem addSrcFileButton = new MenuItem("Add Source");
	private MenuItem removeFileButton = new MenuItem("Remove");
	private MenuItem addCompFileButton = new MenuItem("Add Comparison");
	private Menu menuWindowsButtons = new Menu("Window");
	private MenuItem settingsButton = new MenuItem("Settings");

	private Text statusBar;

	public MenuComponents(
			FileCollectionComponents fileCollectionComponents,
			Text statusBar
	){
		this.statusBar = statusBar;
		this.fileCollectionComponents = fileCollectionComponents;
		setFileMenuButtonFunctionality();
		menuWindowsButtons.getItems()
				.add(settingsButton);
	}

	public Menu getMenuFileButton(){
		return menuFileButton;
	}

	public MenuBar getMenuBar(){
		return menuBar;
	}

	public MenuItem getAddSrcFileButton(){
		return addSrcFileButton;
	}

	public MenuItem getRemoveFileButton(){
		return removeFileButton;
	}

	public MenuItem getAddCompFileButton(){
		return addCompFileButton;
	}

	public Menu getMenuWindowsButtons(){
		return menuWindowsButtons;
	}

	public MenuItem getSettingsButton(){
		return settingsButton;
	}

	@Override
	public void setStatus(String status){
		statusBar.setText(status);
	}

	private void setFileMenuButtonFunctionality(){
		menuBar.getMenus()
				.addAll(menuFileButton, menuWindowsButtons);
		menuFileButton.getItems()
				.addAll(addSrcFileButton, addCompFileButton, removeFileButton);
		settingsButton.setOnAction(event->SettingsMenu.WindowSettings("???"));

		setAddSrcFileButtonOnAction();
		setAddCompFileButtonOnAction();
	}

	public void setAddSrcFileButtonOnAction(){
		fileCollectionComponents.getElementPlaceHolder()
				.setCenter(FileList.ListsOfFiles(fileCollectionComponents.getSrcMidiFiles(),
						fileCollectionComponents.getCompMidiFiles()
				));
		addSrcFileButton.setOnAction(event->loadFile(fileCollectionComponents.getSrcFiles(),
				fileCollectionComponents.getSrcMidiFiles()
		));
	}

	public void setAddCompFileButtonOnAction(){
		fileCollectionComponents.getElementPlaceHolder()
				.setCenter(FileList.ListsOfFiles(fileCollectionComponents.getSrcMidiFiles(),
						fileCollectionComponents.getCompMidiFiles()
				));
		addCompFileButton.setOnAction(event->loadFile(fileCollectionComponents
						.getCompFiles(),
				fileCollectionComponents.getCompMidiFiles()
		));
	}

	private void loadFile(List<File> listOfFiles, List<MidiFile> listOfMidis){
		listOfFiles.addAll(FileListEditor.AddFile());
		initiateMidiFileList(listOfFiles, listOfMidis);
		setStatus("File \"" + listOfMidis.get(listOfMidis.size() - 1)
				.getFilePath()
				.getName() + "\" has been added!");
		fileCollectionComponents.getElementPlaceHolder()
				.setCenter(FileList.ListsOfFiles(fileCollectionComponents.getSrcMidiFiles(),
						fileCollectionComponents.getCompMidiFiles()
				));
	}


	private void initiateMidiFileList(List<File> listOfFiles, List<MidiFile> listOfMidis){
		for(File file : listOfFiles)
			try{
				listOfMidis.add(new MidiFile(file));
			}catch(InvalidMidiDataException | IOException e){
				e.printStackTrace();
			}
		listOfFiles.clear();
	}
}