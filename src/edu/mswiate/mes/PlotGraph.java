package edu.mswiate.mes;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;

import net.ericaro.surfaceplotter.JSurfacePanel;
import net.ericaro.surfaceplotter.surface.ArraySurfaceModel;

public class PlotGraph implements MouseListener {
	private Mes mesSolution;
	private JSlider sliderNDivisions;
	private ArraySurfaceModel graphModel;

	public PlotGraph() {
		JSurfacePanel graphFrame = new JSurfacePanel();
		graphFrame.setTitleText("Wykres 3D przedstawiaj¹cy pole temperatur, wygenerowane po przez rozwi¹zanie równania ciep³a");
		graphFrame.setConfigurationVisible(false);
		
		JFrame mainApplicationWindow = new JFrame("Pole temperatur");
		mainApplicationWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainApplicationWindow.getContentPane().add(graphFrame, BorderLayout.CENTER);
		
		sliderNDivisions = new JSlider(JSlider.HORIZONTAL, 2, 20, 10);
		sliderNDivisions.addMouseListener(this);
		sliderNDivisions.setMajorTickSpacing(1);
		sliderNDivisions.setMinorTickSpacing(1);
		sliderNDivisions.setPaintTicks(true);
		sliderNDivisions.setPaintLabels(true);
		sliderNDivisions.setBorder(new TitledBorder("Dok³adnoœæ wykresu (iloœæ podzia³ów boku kwadratu)"));
		
		mainApplicationWindow.getContentPane().add(sliderNDivisions, BorderLayout.NORTH);
		mainApplicationWindow.setPreferredSize(new Dimension(800, 800));
		mainApplicationWindow.pack();

		graphModel = new ArraySurfaceModel();
		graphModel.setDisplayXY(true);
		graphModel.setDisplayZ(true);
		graphModel.setDisplayGrids(true);
		graphModel.setBoxed(true);
		graphFrame.setModel(graphModel);
		
		solveMesAndFillGraph(sliderNDivisions.getValue(), graphModel);
		
		mainApplicationWindow.setVisible(true);
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		PlotGraph test = new PlotGraph();
	}
	
	private void solveMesAndFillGraph(int n, ArraySurfaceModel graphModel){
		this.mesSolution = new Mes(n);
		
		int indexMatrix = 0;
		float[][] functionValues = new float[2 * n + 1][2 * n + 1];
		for (int i = 0; i < n + 1; i++) {
			for (int j = 0; j < 2 * n + 1; j++) {
				functionValues[i][j] = (float)this.mesSolution.getMatrixU().getEntry(indexMatrix++);
			}
		}
		for (int i = n + 1; i < 2 * n + 1; i++) {
			for (int j = 0; j < 2 * n + 1; j++) {
				if(j <  n){
					functionValues[i][j] = 0;
				}
				else {
					functionValues[i][j] = (float)this.mesSolution.getMatrixU().getEntry(indexMatrix++);
				}
			}
		}
		graphModel.setValues(0, 2 * n + 1, 0, 2 * n + 1, 2 * n + 1, functionValues, null);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		solveMesAndFillGraph(sliderNDivisions.getValue(), graphModel);
	}
}