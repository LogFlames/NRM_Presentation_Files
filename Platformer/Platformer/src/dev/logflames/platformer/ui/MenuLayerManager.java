package dev.logflames.platformer.ui;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

public class MenuLayerManager {

	private ArrayList<MenuLayer> layers;
	private UIManager uiManager;

	public MenuLayerManager(UIManager uiManager) {
		this.uiManager = uiManager;
		layers = new ArrayList<MenuLayer>();
	}

	public void tick() {
		ArrayList<MenuLayer> newLayers0 = new ArrayList<MenuLayer>();
		ArrayList<MenuLayer> newLayers1 = new ArrayList<MenuLayer>();
		ArrayList<MenuLayer> newLayers2 = new ArrayList<MenuLayer>();
		ArrayList<MenuLayer> newLayers3 = new ArrayList<MenuLayer>();

		// TODO: Make this system much better (support unlimited "prio" numbers)

		for (MenuLayer ml : layers) {
			if (ml.getPrio() == 0) {
				newLayers0.add(ml);
			} else if (ml.getPrio() == 1) {
				newLayers1.add(ml);
			} else if (ml.getPrio() == 2) {
				newLayers2.add(ml);
			} else if (ml.getPrio() == 3) {
				newLayers3.add(ml);
			}
		}
		
		clearLayers();
		
		for (MenuLayer ml : newLayers0) {
			layers.add(ml);
		}
		for (MenuLayer ml : newLayers1) {
			layers.add(ml);
		}
		for (MenuLayer ml : newLayers2) {
			layers.add(ml);
		}
		for (MenuLayer ml : newLayers3) {
			layers.add(ml);
		}
	}

	public void render(Graphics g) {
		int prio = -1;
		for (MenuLayer ml : layers) {
			if (ml.getPrio() > prio) {
				prio = ml.getPrio();
			}
			if (uiManager != null && prio == uiManager.getPrio()) {
				uiManager.render(g);
			}
			ml.render(g);
		}
		if (prio == -1 && uiManager != null) {
			uiManager.render(g);
		}
	}
	
	public void render(Graphics g, int prio) {
		for (MenuLayer ml : layers) {
			if (ml.getPrio() < prio) {
				continue;
			} else if (ml.getPrio() == prio) {
				ml.render(g);
			} 
			if (ml.getPrio() > prio) {
				return;
			}
		}
		if (uiManager != null && uiManager.getPrio() == prio) {
			uiManager.render(g);
		}
	}

	public void addLayer(MenuLayer ml) {
		tick();
		layers.add(ml);
	}

	public void removeLayer(int prio) {
		Iterator<MenuLayer> it = layers.iterator();
		while (it.hasNext()) {
			MenuLayer ml = it.next();

			if (ml.getPrio() == prio) {
				it.remove();
			}
		}
	}

	public void clearLayers() {
		layers = new ArrayList<MenuLayer>();
	}

}
