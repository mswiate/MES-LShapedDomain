package edu.mswiate.mes;

import org.apache.commons.math3.linear.*;

public class Mes {
	private RealVector matrixU;
	private int n;
	
	public RealVector getMatrixU() {
		return matrixU;
	}
	public int getN() {
		return n;
	}
	
	public Mes(int n){
		this.n = n;
		RealMatrix matrixA = new Array2DRowRealMatrix( new MatrixPreparer().prepareMatrixA( n ) );
		RealVector matrixB = new ArrayRealVector( new MatrixPreparer().prepareMatrixB( n ) );
//		System.out.println(matrixA);
//		System.out.println(matrixB);
		DecompositionSolver solver = new LUDecomposition(matrixA).getSolver();
		
		RealVector matrixU = solver.solve(matrixB);
		this.matrixU = matrixU;
//		System.out.println(matrixU);
	}
}