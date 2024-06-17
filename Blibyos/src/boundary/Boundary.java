package boundary;


import javafx.scene.layout.Pane;

public interface Boundary { 

    void setExecutor( Executor a );
    void clearExecutor();
    void executarComando( String comando );
    Pane render();

}