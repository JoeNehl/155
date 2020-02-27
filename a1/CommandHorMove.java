package a1;
import java.awt.event.*;
import javax.swing.*;


public class CommandHorMove extends AbstractAction{

    private static Starter bg;

    public CommandHorMove(Starter bg){

        CommandHorMove.bg=bg;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        bg.horMove();
    }
}