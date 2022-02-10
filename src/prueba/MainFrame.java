import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.util.Random;

public class MainFrame extends JFrame {
	final int Width = 700;
	final int Height = 590;
	final float factor = 10;
	final int HalfWidth = Width / 2;
	final int HalfHeight = Height / 2;

	private JPanel contentPanel;
	private JPanel panel = new JPanel();
	private JTextField txtW1;
	private JTextField txtW2;
	private JTextField txtTheta;
        private JTextField txtEpoca;
        private JTextField txtMaxI;
        private JTextField txtLrate;
	private JLabel lblX1;
	private JLabel lblX2;
        private JLabel lblEpocas;
        private JLabel lblMaxI;
        private JLabel lblLrate;

	private boolean running = false;

	ArrayList<DataPoint> points = new ArrayList<>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
                
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 720);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(276, 49, 700, 590);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		lblX2 = new JLabel("Y");
		lblX2.setBounds(622, 30, 22, 13);
		contentPanel.add(lblX2);
		lblX2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lblX1 = new JLabel("X");
		lblX1.setBounds(986, 336, 22, 13);
		contentPanel.add(lblX1);
		lblX1.setFont(new Font("Tahoma", Font.PLAIN, 14));

		txtW1 = new JTextField();
		txtW1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtW1.setBounds(10, 183, 168, 32);
		contentPanel.add(txtW1);
		txtW1.setColumns(10);

		txtW2 = new JTextField();
		txtW2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtW2.setBounds(10, 243, 168, 32);
		contentPanel.add(txtW2);
		txtW2.setColumns(10);

		txtTheta = new JTextField();
		txtTheta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTheta.setBounds(10, 302, 168, 32);
		contentPanel.add(txtTheta);
		txtTheta.setColumns(10);
                
                txtEpoca = new JTextField();
                txtEpoca.setFont(new Font("Tahoma", Font.PLAIN, 14));
                txtEpoca.setBounds(10, 350, 168, 32);
                contentPanel.add(txtEpoca);
		txtEpoca.setColumns(10);
                
                txtMaxI = new JTextField();
                txtMaxI.setFont(new Font("Tahoma", Font.PLAIN, 14));
                txtMaxI.setBounds(10, 400, 168, 32);
                contentPanel.add(txtMaxI);
		txtMaxI.setColumns(10);
                
                txtLrate = new JTextField();
                txtLrate.setFont(new Font("Tahoma", Font.PLAIN, 14));
                txtLrate.setBounds(10, 450, 168, 32);
                contentPanel.add(txtLrate);
		txtLrate.setColumns(10);
                
		JLabel lblW1 = new JLabel("W1");
		lblW1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblW1.setBounds(188, 194, 45, 13);
		contentPanel.add(lblW1);
		
		JLabel lblW2 = new JLabel("W2");
		lblW2.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblW2.setBounds(188, 254, 45, 13);
		contentPanel.add(lblW2);
		
		JLabel lblTheta = new JLabel("\u0398");
		lblTheta.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblTheta.setBounds(188, 313, 45, 13);
		contentPanel.add(lblTheta);
                
                JLabel lblEpoca = new JLabel("Epocas");
                lblEpoca.setFont(new Font("Dialog", Font.PLAIN, 14));
                lblEpoca.setBounds(188, 360, 45, 13);
                contentPanel.add(lblEpoca);
              
                JLabel lblMaxI = new JLabel("MaxI");
                lblMaxI.setFont(new Font("Dialog", Font.PLAIN, 14));
                lblMaxI.setBounds(188, 410, 45, 13);
                contentPanel.add(lblMaxI);

                JLabel lblIrate = new JLabel("Irate");
                lblIrate.setFont(new Font("Dialog", Font.PLAIN, 14));
                lblIrate.setBounds(188, 460, 45, 13);
                contentPanel.add(lblIrate);
                
		JButton btnNewButton = new JButton("Correr Random");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!running) {
					Runner runner = new Runner();
					running = true;
					runner.runRandom();
				}
			}
		});
		btnNewButton.setBounds(54, 30, 124, 45);
		contentPanel.add(btnNewButton);
                
                btnNewButton = new JButton("Correr");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!running) {
					Runner runner = new Runner();
					running = true;
					runner.run();
				}
			}
		});
		btnNewButton.setBounds(54, 90, 124, 45);
		contentPanel.add(btnNewButton);

		btnNewButton = new JButton("Limpiar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!running) {
					points.clear();
					Graphics g = panel.getGraphics();
					g.clearRect(1, 1, Width - 2, Height - 2);

					g.setColor(Color.GREEN);
					g.drawLine(350, 10, 350, 580);
					g.drawLine(10, 295, 690, 295);
					
					txtW1.setText("");
					txtW2.setText("");
					txtTheta.setText("");
                                        txtEpoca.setText("");
					
				}
			}
		});
		btnNewButton.setBounds(10, 641, 96, 32);
		contentPanel.add(btnNewButton);
		
	
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics g2 = panel.getGraphics();
		g2.setColor(Color.GREEN);
		g2.drawLine(350, 10, 350, 580);
		g2.drawLine(10, 295, 690, 295);
		
		panel.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				Graphics g3 = panel.getGraphics();
				float type = e.getButton() == MouseEvent.BUTTON1 ? 1 : 0;
				DataPoint point = new DataPoint(
					graphToChartX(e.getX()),
					graphToChartY(e.getY()),
					type
				);
				points.add(point);

				g3.setColor(type == 1 ? Color.RED : Color.BLUE);
				g3.drawOval(e.getX() - 2, e.getY() - 2, 5, 5);
			}

			@Override
			public void mouseExited(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseClicked(MouseEvent e) {}
		});
	}

	private float chartToGraphY(float val) {
		return HalfHeight - val * factor;
	}

	private float chartToGraphX(float val) {
		return val * factor + HalfWidth;
	}

	private float graphToChartX(int val) {
		return (val - HalfWidth) / factor;
	}

	private float graphToChartY(int val) {
		return (-val + HalfHeight) / factor;
	}

	private class Runner extends Thread {
		private Perceptron perceptron;

		public Runner() {
			perceptron = new Perceptron();
		}

                public void runRandom() {
			RedrawGraph();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
                        Random rand = new Random();
                        double peso1 = rand.nextDouble();
                        double peso2 = rand.nextDouble();
                        int MaxIterations = Integer.parseInt(txtMaxI.getText());
                        double learnrate = Double.parseDouble(txtLrate.getText());
                        perceptron.setW1(peso1);
                        perceptron.setW2(peso2);
                        perceptron.setLearnRate(learnrate);
                        
			for (int i = 0; i < MaxIterations; i++) {
				perceptron.nextIteration(points);

				RedrawGraph();

				if (perceptron.hasLearned()) {
					break;
				}

				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			running = false;
			txtW1.setText(String.valueOf(perceptron.getWeight1()));
			txtW2.setText(String.valueOf(perceptron.getWeight2()));
			txtTheta.setText(String.valueOf(perceptron.getTheta()));
                        txtEpoca.setText(String.valueOf(perceptron.getIterations()));
		}
                
		public void run() {
			RedrawGraph();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
                        int MaxIterations = Integer.parseInt(txtMaxI.getText());
                        double learnrate = Double.parseDouble(txtLrate.getText());
                        double w1 = Double.parseDouble(txtW1.getText());
                        double w2 = Double.parseDouble(txtW2.getText());
                        perceptron.setW1(w1);
                        perceptron.setW2(w2);
                        perceptron.setLearnRate(learnrate);
			for (int i = 0; i < MaxIterations; i++) {
				perceptron.nextIteration(points);

				RedrawGraph();

				if (perceptron.hasLearned()) {
					break;
				}

				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			running = false;
			txtW1.setText(String.valueOf(perceptron.getWeight1()));
			txtW2.setText(String.valueOf(perceptron.getWeight2()));
			txtTheta.setText(String.valueOf(perceptron.getTheta()));
                        txtEpoca.setText(String.valueOf(perceptron.getIterations()));
		}

		private void RedrawGraph()
		{
			Graphics g = panel.getGraphics();
			g.clearRect(1, 1, Width - 2, Height - 2);

			g.setColor(Color.GREEN);
			g.drawLine(350, 10, 350, 580);
			g.drawLine(10, 295, 690, 295);
			

			for (DataPoint point : points) {
				if (point.y > perceptron.getSlope(point.x)) {
					g.setColor(Color.RED);
				} else {
					g.setColor(Color.BLUE);
				}
				g.drawOval((int)chartToGraphX(point.x) - 2, (int)chartToGraphY(point.y) - 2, 5, 5);
			}

			float x1 = graphToChartX(0);
			float x2 = graphToChartX(Width);
			float y1 = perceptron.getSlope(x1);
			float y2 = perceptron.getSlope(x2);

			g.setColor(Color.CYAN);
			g.drawLine(0, (int)chartToGraphY(y1), Width, (int)chartToGraphY(y2));
		}
	}
}

