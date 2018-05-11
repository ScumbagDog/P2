package a307a.program.GUI.guiComponents;

import a307a.program.GUI.MenuBar.settings.SettingsFile;

public class WindowSettings{
	public int getWindowHeight(){
		return windowHeight;
	}

	public int getWindowWidth(){
		return windowWidth;
	}

	public Boolean getWindowFullscreen(){
		return windowFullscreen;
	}

	private int windowHeight;
	private int windowWidth;
	private Boolean windowFullscreen;

	public WindowSettings(SettingsFile settings){
			windowWidth = settings.getWidth();
			windowHeight = settings.getHeight();
			windowFullscreen = settings.getIsWindowFullscreen();
	}
}
