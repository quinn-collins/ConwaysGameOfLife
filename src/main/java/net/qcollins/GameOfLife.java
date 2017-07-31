package net.qcollins;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

@SuppressWarnings("restriction")
public class GameOfLife extends Application {

	private static int tile_size = 20;
	private final int W = 800;
	private final int H = 600;
	private final int X_TILES = W / tile_size;
	private final int Y_TILES = H / tile_size;

	private Cell[][] grid;
	private Cell[][] nextFrame;
	private Scene scene;
	private Pane root;
	
	private boolean gliderGun;

	public static void main(String[] args) {
		launch(args); //Launches Application class
	}

	@Override
	public void start(Stage stage) throws Exception { 	// Application class gives us a stage when it calls start method
		scene = new Scene(createContent()); 			// scene is created with a pane layout
		stage.setScene(scene); 							// we give scene to stage
		stage.show(); 									// we show the stage/window
	}

	public Parent createContent() { // Gives us a layout with our grid
		grid = new Cell[X_TILES][Y_TILES];
		root = new Pane();
		root.setPrefSize(W, H);

		for (int y = 0; y < Y_TILES; y++) {
			for (int x = 0; x < X_TILES; x++) {
				grid[x][y] = new Cell(x, y);
				root.getChildren().add(grid[x][y]);
			}
		}
		root.getChildren().add(createButtons());

		return root;
	}

	public HBox createButtons() { // creates buttons and adds them to the layout
		HBox hbox = new HBox();

		Button next = new Button("Next"); // Moves game to next frame
		next.setOnAction(e -> {
			root.getChildren().clear();
			next();
			root.getChildren().add(createButtons());
		});

		 Button startCycling = new Button("Start"); // Starts game on infinite
		 startCycling.setOnAction(e -> {
			 root.getChildren().clear();
			 startCycling();
			 root.getChildren().add(createButtons());
		 });
		
		 Button stopCycling = new Button("Stop"); // Ends the infinite loop
		 stopCycling.setOnAction(e -> {
			 root.getChildren().clear();
			 stopCycling();
			 root.getChildren().add(createButtons());
		 });
		Button clear = new Button("Clear"); // Clears the game board
		clear.setOnAction(e -> {
			root.getChildren().clear();
			clear();
			root.getChildren().add(createButtons());
		});

		Button seed = new Button("Seed"); // Updates game board from file
		seed.setOnAction(e -> {
			root.getChildren().clear();
			seed();
			root.getChildren().add(createButtons());
		});

		Button random = new Button("Random"); // Randomly generates game board
		random.setOnAction(e -> {
			root.getChildren().clear();
			random();
			root.getChildren().add(createButtons());
		});

		 hbox.getChildren().add(startCycling);
		 hbox.getChildren().add(stopCycling);
		hbox.getChildren().add(next);
		hbox.getChildren().add(clear);
		hbox.getChildren().add(seed);
		hbox.getChildren().add(random);

		return hbox;
	}

	private void addEachCellToRoot(Cell cell) { // when changing game board this
												// method takes each cell and adds it to root
		root.getChildren().addAll(cell);
	}

	// LOGICAL UNITS
	public static int getTileSize() {
		return tile_size;
	}

	public int getCountOfLiveCells(Cell cell) { // checks the neighboring cells for boolean value
		int[] points = new int[] { -1, -1, -1, 0, -1, 1, 0, -1, 0, 1, 1, -1, 1, 0, 1, 1 };
		int count = 0;

		for (int i = 0; i < points.length; i++) {
			int dx = points[i];
			int dy = points[++i];

			int newX = cell.getX() + dx;
			int newY = cell.getY() + dy;

			if (newX >= 0 && newX < X_TILES && newY >= 0 && newY < Y_TILES) {
				if (grid[newX][newY].isOpen()) {
					count++;
				}
			}
		}
		return count;
	}

	public void updateNextFrame() {

		for (int y = 0; y < Y_TILES; y++) {
			for (int x = 0; x < X_TILES; x++) {

				nextFrame[x][y] = new Cell(x, y);
				int count = getCountOfLiveCells(grid[x][y]);

				if (grid[x][y].isOpen() && count < 2) {
					nextFrame[x][y].close();
				}
				if (grid[x][y].isOpen() && count > 3) {
					nextFrame[x][y].close();
				}
				if (grid[x][y].isOpen() && count == 2 || count == 3) {
					nextFrame[x][y].open();
				}
				if (!grid[x][y].isOpen() && count == 3) {
					nextFrame[x][y].open();
				}

			}
		}
	}

	 public void startCycling() {
		next();
	}

	 public void stopCycling() {
	 // Turn some boolean off
	 }

	public void next() {
		nextFrame = new Cell[X_TILES][Y_TILES];
		updateNextFrame();

		for (int y = 0; y < Y_TILES; y++) {
			for (int x = 0; x < X_TILES; x++) {
				grid[x][y] = nextFrame[x][y];
				addEachCellToRoot(grid[x][y]);
			}
		}
	}

	public void clear() {
		nextFrame = new Cell[X_TILES][Y_TILES];

		for (int y = 0; y < Y_TILES; y++) {
			for (int x = 0; x < X_TILES; x++) {
				Cell cell = new Cell(x, y);
				nextFrame[x][y] = cell;
			}
		}
		for (int y = 0; y < Y_TILES; y++) {
			for (int x = 0; x < X_TILES; x++) {
				grid[x][y] = nextFrame[x][y];
				addEachCellToRoot(grid[x][y]);
			}
		}
	}

	public void random() {
		nextFrame = new Cell[X_TILES][Y_TILES];
		for (int y = 0; y < Y_TILES; y++) {
			for (int x = 0; x < X_TILES; x++) {
				nextFrame[x][y] = new Cell(x, y);
				if (Math.random() > .5) {
					nextFrame[x][y].open();
				}
			}
		}
		for (int y = 0; y < Y_TILES; y++) {
			for (int x = 0; x < X_TILES; x++) {
				grid[x][y] = nextFrame[x][y];
				addEachCellToRoot(grid[x][y]);
			}
		}
	}

	public void seed() {
		
		nextFrame = new Cell[X_TILES][Y_TILES];
		for (int y = 0; y < Y_TILES; y++) {
			for (int x = 0; x < X_TILES; x++) {
				nextFrame[x][y] = new Cell(x, y);
			}
		}
		if(gliderGun) {
			// glider gun
			gliderGun = false;
			nextFrame[0][6].open();
			nextFrame[1][6].open();
			nextFrame[0][7].open();
			nextFrame[1][7].open();
			nextFrame[10][6].open();
			nextFrame[10][7].open();
			nextFrame[10][8].open();
			nextFrame[11][5].open();
			nextFrame[12][4].open();
			nextFrame[13][4].open();
			nextFrame[11][9].open();
			nextFrame[12][10].open();
			nextFrame[13][10].open();
			nextFrame[14][7].open();
			nextFrame[17][7].open();
			nextFrame[15][5].open();
			nextFrame[16][6].open();
			nextFrame[16][7].open();
			nextFrame[16][8].open();
			nextFrame[15][9].open();
			nextFrame[16][7].open();
			nextFrame[20][6].open();
			nextFrame[20][5].open();
			nextFrame[20][4].open();
			nextFrame[21][6].open();
			nextFrame[21][5].open();
			nextFrame[21][4].open();
			nextFrame[22][7].open();
			nextFrame[22][3].open();
			nextFrame[24][2].open();
			nextFrame[24][3].open();
			nextFrame[24][7].open();
			nextFrame[24][8].open();
			nextFrame[34][4].open();
			nextFrame[34][5].open();
			nextFrame[35][4].open();
			nextFrame[35][5].open();
		}
		else {
		 // Pulsar (period 3)
			gliderGun = true;
		 nextFrame[5][2].open();
		 nextFrame[6][2].open();
		 nextFrame[7][2].open();
		 nextFrame[11][2].open();
		 nextFrame[12][2].open();
		 nextFrame[13][2].open();
		 nextFrame[3][4].open();
		 nextFrame[8][4].open();
		 nextFrame[10][4].open();
		 nextFrame[15][4].open();
		 nextFrame[3][5].open();
		 nextFrame[8][5].open();
		 nextFrame[10][5].open();
		 nextFrame[15][5].open();
		 nextFrame[3][6].open();
		 nextFrame[8][6].open();
		 nextFrame[10][6].open();
		 nextFrame[15][6].open();
		 nextFrame[5][7].open();
		 nextFrame[6][7].open();
		 nextFrame[7][7].open();
		 nextFrame[11][7].open();
		 nextFrame[12][7].open();
		 nextFrame[13][7].open();
		 nextFrame[5][9].open();
		 nextFrame[6][9].open();
		 nextFrame[7][9].open();
		 nextFrame[11][9].open();
		 nextFrame[12][9].open();
		 nextFrame[13][9].open();
		 nextFrame[3][10].open();
		 nextFrame[8][10].open();
		 nextFrame[10][10].open();
		 nextFrame[15][10].open();
		 nextFrame[3][11].open();
		 nextFrame[8][11].open();
		 nextFrame[10][11].open();
		 nextFrame[15][11].open();
		 nextFrame[3][12].open();
		 nextFrame[8][12].open();
		 nextFrame[10][12].open();
		 nextFrame[15][12].open();
		 nextFrame[5][14].open();
		 nextFrame[6][14].open();
		 nextFrame[7][14].open();
		 nextFrame[11][14].open();
		 nextFrame[12][14].open();
		 nextFrame[13][14].open();
		
		 // Pentadecathlon (period 15)
		 nextFrame[23][8].open();
		 nextFrame[24][8].open();
		 nextFrame[26][8].open();
		 nextFrame[27][8].open();
		 nextFrame[28][8].open();
		 nextFrame[29][8].open();
		 nextFrame[31][8].open();
		 nextFrame[32][8].open();
		 nextFrame[25][7].open();
		 nextFrame[25][9].open();
		 nextFrame[30][7].open();
		 nextFrame[30][9].open();
		
		 // Lightweight spaceship
		 nextFrame[36][20].open();
		 nextFrame[39][20].open();
		 nextFrame[35][21].open();
		 nextFrame[35][22].open();
		 nextFrame[39][22].open();
		 nextFrame[35][23].open();
		 nextFrame[36][23].open();
		 nextFrame[37][23].open();
		 nextFrame[38][23].open();
		
		 // glider
		 nextFrame[15][17].open();
		 nextFrame[16][18].open();
		 nextFrame[14][19].open();
		 nextFrame[15][19].open();
		 nextFrame[16][19].open();
		}

		for (int y = 0; y < Y_TILES; y++) {
			for (int x = 0; x < X_TILES; x++) {
				grid[x][y] = nextFrame[x][y];
				addEachCellToRoot(grid[x][y]);
			}
		}

	}
}