package org.ce1103.gos.util;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicPlayer {
	
	public static final Media backgroundMusicFile = new Media("file:///C:/Users/OscarAraya/Desktop/Eclipse%20Workspace/Proyecto2_GameofSorts/src/org/ce1103/gos/view/graphicResources/Music.mp3");
	public static final MediaPlayer backgroundMusicPlayer = new MediaPlayer(backgroundMusicFile);
	
	public static final Media bossBackgroundMusicFile = new Media("file:///C:/Users/OscarAraya/Desktop/Eclipse%20Workspace/Proyecto2_GameofSorts/src/org/ce1103/gos/view/graphicResources/boss.mp3");
	public static final MediaPlayer bossBackgroundMusicPlayer = new MediaPlayer(bossBackgroundMusicFile);
	
	public static final Media pauseSoundFile = new Media("file:///C:/Users/OscarAraya/Desktop/Eclipse%20Workspace/Proyecto2_GameofSorts/src/org/ce1103/gos/view/graphicResources/PauseSound.wav");
	public static final MediaPlayer pauseSoundPlayer = new MediaPlayer(pauseSoundFile);
	
	public static final Media shootSoundFile = new Media("file:///C:/Users/OscarAraya/Desktop/Eclipse%20Workspace/Proyecto2_GameofSorts/src/org/ce1103/gos/view/graphicResources/ShootSound.wav");
	public static final MediaPlayer shootSoundPlayer = new MediaPlayer(shootSoundFile);
	
	public static final Media enemyKilledSoundFile = new Media("file:///C:/Users/OscarAraya/Desktop/Eclipse%20Workspace/Proyecto2_GameofSorts/src/org/ce1103/gos/view/graphicResources/EnemyKilledSound.wav");
	public static final MediaPlayer enemyKilledSoundPlayer = new MediaPlayer(enemyKilledSoundFile);

	public static final Media levelUpSoundFile = new Media("file:///C:/Users/OscarAraya/Desktop/Eclipse%20Workspace/Proyecto2_GameofSorts/src/org/ce1103/gos/view/graphicResources/LevelUpSound.mp3");
	public static final MediaPlayer levelUpSoundPlayer = new MediaPlayer(levelUpSoundFile);
	
	public static final Media cancelSoundFile = new Media("file:///C:/Users/OscarAraya/Desktop/Eclipse%20Workspace/Proyecto2_GameofSorts/src/org/ce1103/gos/view/graphicResources/CancelSound.mp3");
	public static final MediaPlayer cancelSoundPlayer = new MediaPlayer(cancelSoundFile);
	
	
	
	public MusicPlayer(String soundType) {
	}
}
