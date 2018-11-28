import javax.swing.*;

public class MainJFrameRunnableTest extends JFrame implements Runnable {
private boolean running;
private long timePlayed = 0;
private Thread thread;
private static JTabbedPane pane = new JTabbedPane();
public MainJFrameRunnableTest() {
}

public static void main(String[] args) {
	System.out.println("main method called");
	MainJFrameRunnableTest ohman = new MainJFrameRunnableTest();
	ohman.setTitle("Coockie Clicker");
	ohman.setSize(400,688);
	ohman.setResizable(false);
	ohman.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	ohman.setLocationRelativeTo(null);
	ohman.getFocusableWindowState();
	ohman.getContentPane().add(pane);
	ohman.setVisible(true);
	ohman.start();
}

public void run() {
	gameLoop();
}	
private void gameLoop() {
	long lastTime = System.nanoTime();
	long timer = System.currentTimeMillis();
	final double ns = 1000000000.0 /30.0;
	double delta = 0;
	int frames = 0, updates = 0;
	while(running) {
		long now = System.nanoTime();
		delta+=(now - lastTime)/ns;
		lastTime = now;
		while(delta >= 1){
            update();
            updates++;
            delta--;
        }
		render();
		frames++;
		if(System.currentTimeMillis()-timer >= 1000) {
			timePlayed++;
			timer += 1000;
			System.out.println("CookieCliker " + frames +  " FPS " + updates + " UPS " + timePlayed + " seconds played");
			updates = 0;
			frames = 0;
		}
	}
	stop();
}
public void update() {
}
public void render() {	
}
private synchronized void start() {
	running = true;
	thread = new Thread(this, "Display");
	thread.start();
}
private synchronized void stop() {
	running = false;
}
}

