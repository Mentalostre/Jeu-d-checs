public class Main{
    public static void main(String[] args) {
    	ChessUI ui = new ChessUI();
	GameUI g = new GameUI(ui, "boardConfigurationFiles/KingsOnly.txt", new Human(ui, ChessColor.WHITE), new Human(ui, ChessColor.BLACK));

	g.play();
    }

}
