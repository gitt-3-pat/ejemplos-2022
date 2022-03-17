import org.junit.Assert;
import uk.co.compendiumdev.fizzbuzz.FizzBuzzConverter;
import org.junit.Test;

public class FizzBuzzTest {


    // Write a program that prints the numbers from 1 to 100.
    // But for multiples of three print “Fizz” instead of the number
    // and for the multiples of five print “Buzz”.
    // For numbers which are multiples of both three and five print “FizzBuzz”






    @Test
    public void fizzBuzzConvertorLeavesNormalNumbersAlone(){

        FizzBuzzConverter fizzBuzz = new FizzBuzzConverter();

        Assert.assertEquals("1", fizzBuzz.convert(1));
        Assert.assertEquals("2", fizzBuzz.convert(2));

    }

    @Test
    public void fizzBuzzConvertorMultiplesOfThree(){

        FizzBuzzConverter fizzBuzz = new FizzBuzzConverter();

        Assert.assertEquals("Fizz", fizzBuzz.convert(3));

    }

    @Test
    public void fizzBuzzConvertorMultiplesOfFive(){

        FizzBuzzConverter fizzBuzz = new FizzBuzzConverter();

        Assert.assertEquals("Buzz", fizzBuzz.convert(5));

    }

    @Test
    public void multiplesOfBothThreeAndFive(){
        FizzBuzzConverter fizzBuzz = new FizzBuzzConverter();

        Assert.assertEquals("FizzBuzz", fizzBuzz.convert(15));
    }









}
