import java.util.*;
public class MonteCarlo {
    public static void main(String args[]) {
        int seed = 100; //determines seed for random number generator
        double inflation = 0.035; //inflation rate
        double year_0 = 100.0; //$100 according to year 0
        double year_20 = 100.0;//at year 20, will be equivalent to year 0 (inflation)
        for (int i = 0; i < 20; i++) {
            year_20 += (year_20 * inflation); //for loop is done to create the year 0 equivalent
        }

        Random random_number_generator = new Random(seed); //haven't specified seed
        double[] aggressive_simulation = new double[10000]; //array for 10000 aggressive simulations
        double[] conservative_simulation = new double[10000]; //array for 10000 conservative// simulations
        for (int i = 0; i < 10000; i++) { //focusing on the ith simulation
            double amount_aggressive = 100000;
            double amount_conservative = 100000;
            for (int j = 0; j < 20; j++) { //for each simulation, going through 20 years
                double random_percentage_aggressive = random_number_generator.nextGaussian() * 15.675 + 9.4324; //percentages are generated using gaussian distribution
                double random_percentage_conservative = random_number_generator.nextGaussian() * 6.3438 + 6.189;
                amount_aggressive += amount_aggressive * (random_percentage_aggressive / 100.0);//incrementing aggressive amount
                amount_conservative += amount_conservative * (random_percentage_conservative / 100.0);//incrementing conservative amount
            }
            //have to take into account inflation
            aggressive_simulation[i] = amount_aggressive * (year_0 / year_20);
            conservative_simulation[i] = amount_conservative * (year_0 / year_20);
        }

        Arrays.sort(aggressive_simulation); //sort arrays
        Arrays.sort(conservative_simulation);

        System.out.print("Aggressive simulation median: ");
        System.out.println((aggressive_simulation[4999] + aggressive_simulation[5000]) / 2.0);
        System.out.print("Aggressive simulation 10% worst case: ");
        System.out.println((aggressive_simulation[999] + aggressive_simulation[1000]) / 2.0);
        System.out.print("Aggressive simulation 10% best case: ");
        System.out.println((aggressive_simulation[8999] + aggressive_simulation[9000]) / 2.0);
        System.out.println();

        System.out.print("Conservative simulation median: ");
        System.out.println((conservative_simulation[4999] + conservative_simulation[5000]) / 2.0);
        System.out.print("Conservative simulation 10% worst case: ");
        System.out.println((conservative_simulation[999] + conservative_simulation[1000]) / 2.0);
        System.out.print("Conservative simulation 10% best case: ");
        System.out.println((conservative_simulation[8999] + conservative_simulation[9000]) / 2.0);

    }
}