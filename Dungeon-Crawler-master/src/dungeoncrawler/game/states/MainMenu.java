package dungeoncrawler.game.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import dungeoncrawler.framework.gamestates.GameState;
import dungeoncrawler.framework.gamestates.GameStateManager;
import dungeoncrawler.framework.gui.WindowManager;

public class MainMenu extends GameState {

	private String[] optionsMenu;
	private static final String START_GAME = "Start Game";
	private static final String QUIT_GAME = "Quit Game";
	private int selected;

	public MainMenu(GameStateManager manager) {
		super(manager);
		this.optionsMenu = new String[]{START_GAME, QUIT_GAME};
		this.selected = 0;
	}

	@Override
	protected void loop() {
		// Nothing to update per frame
	}

	@Override
	protected void render(Graphics graphics) {
		// Background
		graphics.setColor(new Color(20, 20, 40));
		graphics.fillRect(0, 0, WindowManager.WIDTH, WindowManager.HEIGHT);

		// Title
		graphics.setFont(new Font("Arial", Font.BOLD, 48));
		graphics.setColor(new Color(255, 215, 0));
		String title = "DUNGEON QUEST";
		int titleWidth = graphics.getFontMetrics().stringWidth(title);
		graphics.drawString(title, (WindowManager.WIDTH - titleWidth) / 2, 150);

		// Menu options
		graphics.setFont(new Font("Arial", Font.PLAIN, 32));
		for (int i = 0; i < this.optionsMenu.length; i++) {
			String text = this.optionsMenu[i];
			int textWidth = graphics.getFontMetrics().stringWidth(text);

			if (i == this.selected) {
				graphics.setColor(Color.YELLOW);
				graphics.drawString("▶ " + text, (WindowManager.WIDTH - textWidth) / 2 - 30, 250 + i * 50);
			} else {
				graphics.setColor(Color.WHITE);
				graphics.drawString(text, (WindowManager.WIDTH - textWidth) / 2, 250 + i * 50);
			}
		}
	}

	@Override
	protected void keyPressed(int keyCode) {
		switch (keyCode) {
			case KeyEvent.VK_UP:
			case KeyEvent.VK_W:
				if (this.selected > 0) this.selected--;
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_S:
				if (this.selected < this.optionsMenu.length - 1) this.selected++;
				break;
			case KeyEvent.VK_ENTER:
				switch (this.optionsMenu[selected]) {
					case START_GAME:
						super.gameStateManager.stackState(new PlayingState(gameStateManager));
						break;
					case QUIT_GAME:
						System.exit(0);
						break;
				}
				break;
		}
	}

	@Override
	protected void keyReleased(int keyCode) {
		// Not used
	}
}

