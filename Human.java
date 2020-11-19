public class Human extends Player{

    ChessUI ui;
    public Human(ChessUI ui, ChessColor color){
        super(color);
        this.ui = ui;
    }

    @Override
    public FromTo getFromTo() {
        return ui.waitForPlayerMove();
    }
    
}
