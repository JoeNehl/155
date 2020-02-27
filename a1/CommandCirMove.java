package a1;
import java.awt.event.*;
import javax.swing.*;


public class CommandCirMove extends AbstractAction{

    private static Starter bg;

    public CommandCirMove(Starter bg){

        CommandCirMove.bg=bg;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        bg.cirMove();
    }
}