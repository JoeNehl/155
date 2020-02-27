package a1;
import java.nio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static com.jogamp.opengl.GL4.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.GLContext;
import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.util.*;

public class Starter extends JFrame implements GLEventListener, MouseWheelListener, KeyListener {
    private GLCanvas myCanvas;
    private int renderingProgram;
    private int vao[] = new int[1];

    private float x = 0.0f;
    private float y = 0.0f;
    private float inc = 0.1f;
    private float incx = 0.01f;
    private float incy = 0.01f;
    private float theta = 0.0f;
    private float inctheta = 1.0f;
    private float size = 1.0f;
    private int motion=0;
    private int grad=0;

    public Starter() {
        setTitle("Assignment 1 - Joseph Nehl");

        CommandColorSwap ccs = new CommandColorSwap(this); // creates action to swap colors
        CommandVertMove cvm = new CommandVertMove(this); // creates action to move vertically
        CommandHorMove chm = new CommandHorMove(this); // creates action to move horizontally
        CommandCirMove ccm = new CommandCirMove(this); //creates action to move in circle

        JButton verticalB = new JButton("Vertical");
        verticalB.addActionListener(cvm);
        JButton horizontalB = new JButton("Horizontal");
        horizontalB.addActionListener(chm);
        JButton circularB = new JButton("Circular");
        circularB.addActionListener(ccm);
        JButton colorGradientB= new JButton("Gradient");
        colorGradientB.addActionListener(ccs); // the lines beforehand create buttons with assigned test and then assigns the actions created to each button

        JPanel bottom = new JPanel();
        this.add(bottom, BorderLayout.SOUTH);
        bottom.add(verticalB);
        bottom.add(horizontalB);
        bottom.add(circularB);
        bottom.add(colorGradientB);// assigns the position of the buttons to the south section of the screen

        this.addMouseWheelListener(this);// adds a mouse wheel listener for size increase and decrease

        JComponent screen = (JComponent) this.getContentPane();// returns the value of this opened window
        int screenName = JComponent.WHEN_IN_FOCUSED_WINDOW;// creates variable that checks to see if user has selected this screen
        InputMap iMap = screen.getInputMap(screenName);
        KeyStroke c = KeyStroke.getKeyStroke('c');// assigns the key stroke to look for
        iMap.put(c, "grad");//assigns it to the window
        ActionMap aMap = screen.getActionMap();
        aMap.put("grad", ccs);//assigns action to window

        setSize(1000, 1000);//sets size of the window
        myCanvas = new GLCanvas();
        myCanvas.addGLEventListener(this);
        this.add(myCanvas);
        this.setVisible(true);
        Animator animator = new Animator(myCanvas);
        animator.start();
    }

    public void display(GLAutoDrawable drawable)
    {	GL4 gl = (GL4) GLContext.getCurrentGL();
        gl.glClear(GL_COLOR_BUFFER_BIT);
        gl.glClear(GL_DEPTH_BUFFER_BIT);
        gl.glUseProgram(renderingProgram);

        switch(motion){
            case 0: {//if motion is 0 then triangle moves horizontally
                x += incx;
                if (x > 1.0f) incx = -0.01f;
                if (x < -1.0f) incx = 0.01f;
                int motionSet = gl.glGetUniformLocation(renderingProgram, "motion");
                gl.glProgramUniform1i(renderingProgram, motionSet, motion);
                int offsetLoc = gl.glGetUniformLocation(renderingProgram, "incx");
                gl.glProgramUniform1f(renderingProgram, offsetLoc, x);
                break;
            }
            case 1: {//if motion is 1 then triangle moves vertically
                y += incy;
                if (y > 1.0f) incy = -0.01f;
                if (y < -1.0f) incy = 0.01f;
                int motionSet = gl.glGetUniformLocation(renderingProgram, "motion");
                gl.glProgramUniform1i(renderingProgram, motionSet, motion);
                int offsetLoc = gl.glGetUniformLocation(renderingProgram, "incy");
                gl.glProgramUniform1f(renderingProgram, offsetLoc, y);
                break;
            } case 2: {//if motion is 2 then triangle moves in circle
                theta += inctheta;
                if (x > 1.0f) incx = -0.01f;
                if (y < -1.0f) incy = 0.01f;
                int motionSet = gl.glGetUniformLocation(renderingProgram, "motion");
                gl.glProgramUniform1i(renderingProgram, motionSet, motion);
                int offsetLoc = gl.glGetUniformLocation(renderingProgram, "inctheta");
                gl.glProgramUniform1f(renderingProgram, offsetLoc, theta);
                break;
            }
        }
        int sizeLoc = gl.glGetUniformLocation(renderingProgram, "scale");//sets the scale of the triangle (how zoomed in)
        gl.glProgramUniform1f(renderingProgram, sizeLoc, size);
        int offsetLoc = gl.glGetUniformLocation(renderingProgram, "grad");//sets the color of the triangle
        gl.glProgramUniform1i(renderingProgram, offsetLoc, grad);
        gl.glDrawArrays(GL_TRIANGLES,0,3);
    }

    public void gradColor(){//switchs the color back and forth between gradient and non for each button/key press
        if (grad==0){
            grad=1;
        }else{
            grad=0;
        }
    }

    public void cirMove(){//command calls this to move in circle
        motion=2;
    }

    public void vertMove(){//command calls this to move vertically
        motion=1;
    }

    public void horMove(){//command calls this to move horizontally
        motion=0;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {//mouse wheel event calls this to get mouse rotation and returns the size the image hould be

        if(e.getWheelRotation() > 0  && size < 1.9f)
            size += inc;
        else if (e.getWheelRotation() < 0 && size > 0.1f)
            size -= inc;
    }

    public void keyTyped(KeyEvent k){//calls the gradient changes when key is pressed
        if (k.getKeyCode()== KeyEvent.VK_G){
            gradColor();
        }
    }

    public void keyPressed(KeyEvent e) {//must be included or throws error

    }

    public void keyReleased(KeyEvent e) {//must be included or throws error

    }

    public void init(GLAutoDrawable drawable)
    {	GL4 gl = (GL4) GLContext.getCurrentGL();

        System.out.println("OpenGL Version: " + gl.glGetString(gl.GL_VERSION));
        System.out.println("JOGL Version: " + Package.getPackage("com.jogamp.opengl").getImplementationVersion());
        System.out.println("Java Version: " + System.getProperty("java.version"));//the set of prior codes displays the version numbers of java, jogl and open gl

        renderingProgram = Utils.createShaderProgram("a1/vertShader.glsl", "a1/fragShader.glsl");
        gl.glGenVertexArrays(vao.length, vao, 0);
        gl.glBindVertexArray(vao[0]);
    }

    public static void main(String[] args) { new Starter(); }
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {}
    public void dispose(GLAutoDrawable drawable) {}
}