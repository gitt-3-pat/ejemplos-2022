import org.junit.Assert;
import org.junit.Test;
import uk.co.compendiumdev.fizzbuzz.FizzBuzzConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FizzBuzzAcceptanceTest {


    // Since we only have 100 numbers, how many ways could you automate the assertion of the full range of numbers used?
    //
    // - sample
    // - different algorithm to calculate result
    // - hard-coded precanned list of expected values
    // - algorithmically calculate the precanned list of expected values
    // - compare against a file
    // - any more?


    /*
    1 == 1
    2 == 2
    3 == fizz
    4 == 4
    5 == Buzz
    6 == Fizz
    7 == 7
    8 == 8
    9 == Fizz
    10 == Buzz
    ... 12
    15 == FizzBuzz
    100 == Buzz
 */


    // this uses a sampling model based on the agreed acceptance criteria
    @Test
    public void checkAcceptanceCriteria(){

        FizzBuzzConverter fizzBuzz = new FizzBuzzConverter();

        Assert.assertEquals("1", fizzBuzz.convert(1));
        Assert.assertEquals("2", fizzBuzz.convert(2));
        Assert.assertEquals("Fizz", fizzBuzz.convert(3));
        Assert.assertEquals("4", fizzBuzz.convert(4));
        Assert.assertEquals("Buzz", fizzBuzz.convert(5));
        Assert.assertEquals("Fizz", fizzBuzz.convert(6));
        Assert.assertEquals("7", fizzBuzz.convert(7));
        Assert.assertEquals("8", fizzBuzz.convert(8));
        Assert.assertEquals("Fizz", fizzBuzz.convert(9));
        Assert.assertEquals("Buzz", fizzBuzz.convert(10));
        Assert.assertEquals("11", fizzBuzz.convert(11));
        Assert.assertEquals("Fizz", fizzBuzz.convert(12));
        Assert.assertEquals("13", fizzBuzz.convert(13));
        Assert.assertEquals("14", fizzBuzz.convert(14));
        Assert.assertEquals("FizzBuzz", fizzBuzz.convert(15));
        Assert.assertEquals("16", fizzBuzz.convert(16));
        Assert.assertEquals("Buzz", fizzBuzz.convert(100));

    }

    // Write a program that prints the numbers from 1 to 100.
    // But for multiples of three print “Fizz” instead of the number
    // and for the multiples of five print “Buzz”.
    // For numbers which are multiples of both three and five print “FizzBuzz”

    // this uses an algorithmic model
    @Test
    public void checkAllNumbers(){

        boolean isMultipleOfThree=false;
        boolean isMultipleOfFive=false;
        FizzBuzzConverter fizzBuzz = new FizzBuzzConverter();

        for(int checkThis=1; checkThis<=100; checkThis++){

            String expectedVal = String.valueOf(checkThis);

            isMultipleOfFive = checkThis%5==0 ? true: false;
            isMultipleOfThree = checkThis%3==0 ? true: false;

            if(isMultipleOfFive && isMultipleOfThree){
                expectedVal="FizzBuzz";
            }else{
                if(isMultipleOfFive){
                    expectedVal="Buzz";
                }else{
                    if(isMultipleOfThree){
                        expectedVal="Fizz";
                    }
                }
            }

            System.out.println(fizzBuzz.convert(checkThis));
            Assert.assertEquals(expectedVal, fizzBuzz.convert(checkThis));

        }

    }



    /*

        -ve
        0  == ?
        101 == 101

     */



    // comparison against pre-identified lists of numbers
    // Fizz Buzz = 15, 30, 45, 60, 75, 90
    // Buzz 5, 10,
    // Fizz 3, 6, 9, 12
    @Test
    public void checkAllNumbersPreCanned(){

        Integer fizzBuzz[] = {15, 30, 45, 60, 75, 90};
        // creating this manually was prone to error - I missed out 35 and 100 originally
        Integer buzz[] = {5, 10, 20, 25, 35, 40, 50, 55, 65, 70, 80, 85, 95, 100};
        // creating this array was a pain of mental arithmetic
        Integer fizz[] = {  3, 6, 9, 12, 18, 21, 24, 27, 33, 36, 39,
                        42, 48, 51, 54, 57, 63, 66, 69, 72, 78,
                        81, 84, 87, 93, 96, 99};

        FizzBuzzConverter fizzBuzzer = new FizzBuzzConverter();

        for(int checkThis=1; checkThis<=100; checkThis++){

            boolean checkedIt=false;

            if(Arrays.asList(fizz).contains(checkThis)){
                Assert.assertEquals("Fizz", fizzBuzzer.convert(checkThis));
                checkedIt=true;
            }

            if(Arrays.asList(buzz).contains(checkThis)){
                Assert.assertEquals("Buzz", fizzBuzzer.convert(checkThis));
                checkedIt=true;
            }

            if(Arrays.asList(fizzBuzz).contains(checkThis)){
                Assert.assertEquals("FizzBuzz", fizzBuzzer.convert(checkThis));
                checkedIt=true;
            }

            if(!checkedIt){
                Assert.assertEquals(String.valueOf(checkThis), fizzBuzzer.convert(checkThis));
                checkedIt=true;
            }

            Assert.assertEquals(true, checkedIt);

        }
    }


    // alternative way to compare all numbers by writing an algorithm to generate 3 different lists of multiples
    // i.e. multiples of 3*5, 3, and 5
    //
    // because calculating the numbers in advance was a pain I decided to write code to
    // generate them - this seemed simpler than recreating the algorithm for fizz buzz in a different way
    // also has the ability to scale up if I Want to do 1-1000 or 1- 1000000 by changing the value for `maxVal`
    @Test
    public void checkAllNumbersAlgorithmicallyGenerated(){

        List<Integer> fizzBuzz = new ArrayList<Integer>();
        List<Integer>  buzz = new ArrayList<Integer>();
        List<Integer> fizz = new ArrayList<Integer>();

        // use an algorithm to calculate the values in advance
        int maxVal = 100;
        for(int i=1; i<maxVal; i++){

            // For numbers which are multiples of both three and five print “FizzBuzz”
            int fizzBuzzVal = i*(3*5);
            if(fizzBuzzVal<=maxVal){
                fizzBuzz.add(fizzBuzzVal);
            }


            // and for the multiples of five print “Buzz”.
            int buzzVal = i*5;
            if(buzzVal<=maxVal){

                if(!fizzBuzz.contains(buzzVal)) {
                    buzz.add(buzzVal);
                }
                /*
                // do not add if also multiple of 3
                if(buzzVal%3!=0) {
                    buzz.add(buzzVal);
                }
                */

            }

            // But for multiples of three print “Fizz” instead of the number
            int fizzVal = i*3;
            if(fizzVal<=maxVal) {
                if (!fizzBuzz.contains(fizzVal)) {
                    fizz.add(fizzVal);
                }
                /*
                   // do not add if also multiple of 5
                   if(fizzVal%5!=0) {
                    fizz.add(fizzVal);
                   }
                */

            }

        }

        FizzBuzzConverter fizzBuzzer = new FizzBuzzConverter();

        for(int checkThis=1; checkThis<=maxVal; checkThis++){

            boolean checkedIt=false;

            if(fizz.contains(checkThis)){
                Assert.assertEquals("Fizz", fizzBuzzer.convert(checkThis));
                checkedIt=true;
            }

            if(buzz.contains(checkThis)){
                Assert.assertEquals("Buzz", fizzBuzzer.convert(checkThis));
                checkedIt=true;
            }

            if(fizzBuzz.contains(checkThis)){
                Assert.assertEquals("FizzBuzz", fizzBuzzer.convert(checkThis));
                checkedIt=true;
            }

            if(!checkedIt){
                Assert.assertEquals(String.valueOf(checkThis), fizzBuzzer.convert(checkThis));
                checkedIt=true;
            }

            Assert.assertEquals(true, checkedIt);
            System.out.println(fizzBuzzer.convert(checkThis));

        }
    }

    // TODO: have a file with the expected output and compare a generated output against the contents of the file


}
