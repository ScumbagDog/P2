package a307a.program.GUI.guiComponents;

import a307a.program.GUI.MenuBar.settings.SettingsFile;

public class WindowSettings{
	private int windowHeight;
	private int windowWidth;
	private Boolean windowFullscreen;

	public WindowSettings(SettingsFile settings){
		windowWidth = settings.getWindowWidth();
		windowHeight = settings.getWindowHeight();
		windowFullscreen = settings.getIsWindowFullscreen();
	}

	public int getWindowHeight(){
		return windowHeight;
	}

	public int getWindowWidth(){
		return windowWidth;
	}

	public Boolean getWindowFullscreen(){
		return windowFullscreen;
	}
}
