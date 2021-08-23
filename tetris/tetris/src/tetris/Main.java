package tetris;
////////
import java.util.*;
import java.io.*;

////////
public class Main {
// ####
public static void main(String [] args) throws IOException, InterruptedException {
	int seed = new Random().nextInt(128);
	Random rand = new Random(seed);
	HW2_0123456789 tetris = new HW2_0123456789();
	int score = tetris.run(rand);
	System.out.println(tetris.id+": score="+score);
}
}
