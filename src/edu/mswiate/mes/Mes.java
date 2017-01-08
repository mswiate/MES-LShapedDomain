package edu.mswiate.mes;

import org.apache.commons.math3.linear.*;

public class Mes {

	public static void main(String[] args) {
		int n = 3;
		RealMatrix matrixA = new Array2DRowRealMatrix( new MatrixPreparer().prepareMatrixA( n ) );
		RealVector matrixB = new ArrayRealVector( new MatrixPreparer().prepareMatrixB( n ) );
		System.out.println(matrixA);
		System.out.println(matrixB);
		DecompositionSolver solver = new LUDecomposition(matrixA).getSolver();
		
		RealVector matrixU = solver.solve(matrixB);
		System.out.println(matrixU);
		

	}

}
