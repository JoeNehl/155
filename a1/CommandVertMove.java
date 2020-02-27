package a1;
import java.awt.event.*;
import javax.swing.*;


public class CommandVertMove extends AbstractAction{

    private static Starter bg;

    public CommandVertMove(Starter bg){

        CommandVertMove.bg=bg;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        bg.vertMove();
    }
}