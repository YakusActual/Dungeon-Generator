/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeongenerator;

/**
 *
 * @author YakusActual
 */
public class RNEngine {
	public static int rand(int min, int max){
		return (int) (Math.random() * (max + 1 - min)) + min;
	}

	public static void main (String args[]){
		for (int i = 0; i < 20; i++){
			System.out.println(RNEngine.rand(0, 2));
		}
	}
}
