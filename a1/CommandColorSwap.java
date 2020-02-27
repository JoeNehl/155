package a1;
import java.awt.event.*;
import javax.swing.*;


public class CommandColorSwap extends AbstractAction{

    private static Starter bg;

    public CommandColorSwap(Starter bg){

        CommandColorSwap.bg=bg;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        bg.gradColor();
    }
}