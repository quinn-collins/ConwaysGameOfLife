import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameOfLife extends Application {

	private static final int TILE_SIZE = 80;
	private static final int W = 800;
	private static final int H = 600;

	private static final int X_TILES = W / TILE_SIZE;
	private static final int Y_TILES = H / TILE_SIZE;

	private Cell[][] grid = new Cell[X_TILES][Y_TILES];
	private Cell[][] nextFrame = new Cell[X_TILES][Y_TILES];
	private Scene scene;

	public Parent createContent() {
		
		Button start = new Button("Start");
		start.setOnAction(e -> {
			start();
		});
		Pane root = new Pane();
		root.setPrefSize(W, H);

		for (int y = 0; y < Y_TILES; y++) {
			for (int x = 0; x < X_TILES; x++) {
				Cell cell = new Cell(x, y);
				grid[x][y] = cell;
				root.getChildren().add(cell);
			}
		}

		for (int y = 0; y < Y_TILES; y++) {
			for (int x = 0; x < X_TILES; x++) {
			}
		}
		root.getChildren().addAll(start);
		return root;
	}
	

	private void start() {
		
		for (int y = 0; y < Y_TILES; y++) {
			for (int x = 0; x < X_TILES; x++) {
				nextFrame[x][y] = grid[x][y];
				
				
				int count = getCountOfLiveCells(grid[x][y]);
				System.out.println("count: " + count + "  x: " + x + "  y: " + y);

				
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
		for (int y = 0; y < Y_TILES; y++) {
			for (int x = 0; x < X_TILES; x++) {
				grid[x][y] = nextFrame[x][y];
			}
		}
		
		scene = new Scene(createContent());
		
	}
	

	public static int getTileSize() {
		return TILE_SIZE;
	}

	public int getCountOfLiveCells(Cell cell) {

		int[] points = new int[] { -1, -1, -1, 0, -1, 1, 0, -1, 0, 1, 1, -1, 1, 0, 1, 1 };
		int count = 0;
		for (int i = 0; i < points.length; i++) {
			int dx = points[i];
			int dy = points[++i];

			int newX = cell.getX() + dx;
			int newY = cell.getY() + dy;

			if (newX >= 0 && newX < X_TILES && newY >= 0 && newY < Y_TILES) {
				if(grid[newX][newY].isOpen()) {
					count++;
				}
			}
		}

		return count;
	}

	@Override
	public void start(Stage stage) throws Exception {
		scene = new Scene(createContent());

		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}