
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Cell extends StackPane {
        private int x, y;
        
        private boolean isOpen = false;
        
        public boolean isOpen() {
			return isOpen;
		}

		private Rectangle border = new Rectangle(GameOfLife.getTileSize() , GameOfLife.getTileSize());
        private Text text = new Text();

		

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
            
            border.setStroke(Color.LIGHTGREY);
            border.setFill(Color.BLACK);

            
         

            getChildren().addAll(border, text);

            setTranslateX(x * GameOfLife.getTileSize());
            setTranslateY(y * GameOfLife.getTileSize());

            setOnMouseClicked(e -> open());
            
            
        }

        public void open() {
        	isOpen = true;
        	border.setFill(Color.WHITE);
        }

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		public void close() {
			isOpen = false;
			border.setFill(Color.BLACK);
			
		}
}