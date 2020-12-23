public class Main{
    public static void main(String[] args) {

    	ChessUI ui = new ChessUI();
	GameUI g = new GameUI(ui, "boardConfigurationFiles/FullBoard.txt", new Human(ui, ChessColor.WHITE), new ChessBot(ui, ChessColor.BLACK));

	g.play();
    }

}
