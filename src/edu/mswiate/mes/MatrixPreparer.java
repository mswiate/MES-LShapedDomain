package edu.mswiate.mes;
import java.lang.Math;

public class MatrixPreparer {
	
	private double[][] matrix = new double[][] {{2.0/3.0, -1.0/6.0, -1.0/3.0, -1.0/6.0},
												{-1.0/6.0, 2.0/3.0, -1.0/6.0, -1.0/3.0},
												{-1.0/3.0, -1.0/6.0, 2.0/3.0, -1.0/6.0},
												{-1.0/6.0, -1.0/3.0, -1.0/6.0, 2.0/3.0}};
	
	public MatrixPreparer(){
		
	}
	
	public double[][] prepareMatrixA( int n ){
		int size = ( n + 1 ) * ( 3*n + 1); 
		double[][] result = zeros( size, size );
		
		for( int i = 0; i < n * (2*n + 1); ++i){
			if( i % (2*n + 1) != 2*n ){
				int _4 = i;
				int _3 = _4 + 1;
				int _2 = _3 + (2*n + 1);
				int _1 = _2 - 1;
				map(result, _1, _2, _3, _4);
			}
		}
			
			
		for( int i = n * (2*n + 1) + n; i < size - (n + 1); ++i){
			if( i % (n+1) != n ){
				int _4 = i;
				int _3 = _4 + 1;
				int _2 = _3 + n + 1;
				int _1 = _2 - 1;
				map(result, _1, _2, _3, _4);
			}
		}
		
		for(int i = n * (2*n + 1); i <= n * (2*n + 1) + n ; ++i){
			for(int j = 0; j < size ; ++j){
				result[i][j] = 0;
			}	
			result[i][i] = 1;
		}
		
		for(int i = (n + 1) * (2*n + 1); i <= size - n ; i += n + 1){
			for(int j = 0; j < size ; ++j){
				result[i][j] = 0;
			}
			result[i][i] = 1;
		}
		
		return result;
		
		
	}
	
	private double[][] zeros(int h, int w){
		double[][] result = new double[h][w]; 
		for(int i = 0; i < h; ++i)
			for(int j = 0 ; j < w; ++j)
				result[i][j] = 0.0;
		return result;
	}
	
	private void map(double result[][], int _1,int _2, int _3, int _4){
		result[_1][_1] += matrix[0][0];
		result[_2][_2] += matrix[1][1];
		result[_3][_3] += matrix[2][2];
		result[_4][_4] += matrix[3][3];
		result[_1][_2] = result[_2][_1] += matrix[0][1];
		result[_1][_3] = result[_3][_1] += matrix[0][2];
		result[_1][_4] = result[_4][_1] += matrix[0][3];
		result[_2][_3] = result[_3][_2] += matrix[1][2];
		result[_2][_4] = result[_4][_2]	+= matrix[1][3];	
		result[_3][_4] = result[_4][_3] += matrix[2][3];
	}

	public double[] prepareMatrixB(int n) {
		int size = ( n + 1 ) * ( 3*n + 1); 
		double[] result = new double[size];
		for(int i = 0; i < size; ++i)
			result[i] = 0.0;
		double h = 1.0 / (double) n;
		
		for(int i = 1; i < n; ++i){
			result[i * (2*n + 1)] = (1.0/2.0) * h * (g(-1, 1-((i-1)*h)) + g(-1, 1-((i+1)*h))); 
		}

		result[0] = (1.0/2.0) * h * (g(-1+h, 1) + g(-1, 1-h));
		
		for(int i = 1; i < 2*n; ++i){
			result[i] = (1.0/2.0) * h * (g(-1+((i-1)*h), 1) + g(-1+((i+1)*h), 1));
		}
		
		result[2*n] = (1.0/2.0) * h * (g(1-h, 1) + g(1, 1-h));
		
		for(int i = 1; i <= n; ++i){
			result[(i + 1) * (2*n + 1) - 1] = (1.0/2.0) * h * (g(1, 1-((i-1)*h)) + g(1, 1-((i+1)*h)));
		}
		
		for(int i = n + 1; i < 2*n; ++i){
			result[(n + 1) * (2*n + 1) - 1 + (i - n) * (n + 1) ] = (1.0/2.0) * h * (g(1, 1-((i-1)*h)) + g(1, 1-((i+1)*h)));
		}
		
		result[size - 1] = (1.0/2.0) * h * (g(1 - h, -1) + g(1, -1 + h));
		
		for(int i = 1; i < n; ++i){
			result[(size - (n + 1)) + i] = (1.0/2.0) * h * (g(((i-1)*h), -1) + g((i+1)*h, -1));
		}
		
		return result;
	}
	
	public double g(double x, double y){
		
		double r = Math.sqrt(x*x + y*y);
		double cos = Math.cos(Math.atan(y/x) - Math.PI/4);
		
		
		return Math.cbrt( (r*r) ) * Math.cbrt( (cos*cos) );
		
	}
	
	
}
