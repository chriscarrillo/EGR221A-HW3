import org.junit.Assert;
import org.junit.Test;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by grayson on 1/17/2017.
 */

//Test constructor
public class AssassinManagerInstructorTest {

    @Test
    public void constructorTest(){
        List<String> list1= new ArrayList<String>();
        list1.add("Grayson");
        list1.add("Ocean");
        list1.add("Chris");
        list1.add("Dr.Han");

        AssassinManager manager = new AssassinManager(list1);

        manager.printKillRing();
    }

    @Test
    public void isGameOverTest(){
        List<String> list1= new ArrayList<String>();
        list1.add("Grayson");

        AssassinManager manager = new AssassinManager(list1);

        Assert.assertTrue(manager.isGameOver());
    }

    @Test
    public void winnerTest(){
        List<String> list1= new ArrayList<String>();
        list1.add("Grayson");

        AssassinManager manager = new AssassinManager(list1);

        Assert.assertTrue(manager.winner().equals("Grayson"));
    }

    @Test
    public void killRingContainsTest(){
        List<String> list1= new ArrayList<String>();
        list1.add("Grayson");
        list1.add("Ocean");
        list1.add("Chris");
        list1.add("Dr.Han");

        AssassinManager manager = new AssassinManager(list1);

        Assert.assertTrue(manager.killRingContains("grayson"));
        Assert.assertTrue(manager.killRingContains("dr.hAn"));
        Assert.assertTrue(manager.killRingContains("chriS"));
        Assert.assertTrue(manager.killRingContains("OCEAN"));
    }

    @Test
    public void killRingContainsNegtiveTest(){
        List<String> list1= new ArrayList<String>();
        list1.add("Grayson");
        list1.add("Ocean");
        list1.add("Chris");
        list1.add("Dr.Han");

        AssassinManager manager = new AssassinManager(list1);

        Assert.assertFalse(manager.killRingContains("grayso"));
        Assert.assertFalse(manager.killRingContains("drhAn"));
        Assert.assertFalse(manager.killRingContains("bob"));
        Assert.assertFalse(manager.killRingContains("sally"));
    }

    @Test
    public void killNegativeTest(){
        try{
            List<String> list1= new ArrayList<String>();
            list1.add("Grayson");
            AssassinManager manager = new AssassinManager(list1);
            manager.kill("Grayso");
            Assert.fail();
        }catch (IllegalStateException e){
        }
    }

    @Test
    public void killNegativeTest2(){
        try{
            List<String> list1= new ArrayList<String>();
            list1.add("Grayson");
            list1.add("George");
            AssassinManager manager = new AssassinManager(list1);
            manager.kill("Grayso");
            Assert.fail();
        }catch (IllegalArgumentException e){
        }
    }

    @Test
    public void killTest(){
        List<String> list1 = new ArrayList<String>();
        list1.add("Grayson");
        list1.add("Ocean");
        list1.add("Chris");
        list1.add("Dr.Han");
        list1.add("Greg");

        AssassinManager manager = new AssassinManager(list1);

        manager.kill("Grayson");
        Assert.assertFalse(manager.killRingContains("Grayson"));

        manager.kill("Dr.han");
        Assert.assertFalse(manager.killRingContains("dr.han"));

        manager.kill("Greg");
        Assert.assertFalse(manager.killRingContains("greg"));
    }

    @Test
    public void graveyardContainsTest(){
        List<String> list1= new ArrayList<String>();
        list1.add("Grayson");
        list1.add("Ocean");
        list1.add("Chris");
        list1.add("Dr.Han");

        AssassinManager manager = new AssassinManager(list1);

        //test for front of ring
        manager.printKillRing();
        manager.kill("grayson");
        manager.printGraveyard();
        Assert.assertTrue(manager.graveyardContains("grayson"));

        //test for middle of ring
        manager.printKillRing();
        manager.kill("Chris");
        manager.printGraveyard();
        Assert.assertTrue(manager.graveyardContains("Chris"));

        //test for end of ring
        manager.printKillRing();
        manager.kill("dr.han");
        manager.printGraveyard();
        Assert.assertTrue(manager.graveyardContains("dr.han"));

    }

    @Test
    public void graveyardContainsNegtiveTest(){
        List<String> list1= new ArrayList<String>();
        list1.add("Grayson");
        list1.add("Ocean");
        list1.add("Chris");
        list1.add("Dr.Han");

        AssassinManager manager = new AssassinManager(list1);
        manager.kill("Grayson");
        Assert.assertFalse(manager.graveyardContains("ocean"));
    }

    @Test
    public void constructorNegativeTest(){
        try{
            List<String> list1 = new ArrayList<String>();
            AssassinManager manager = new AssassinManager(list1);
            Assert.fail();
        }catch (IllegalArgumentException e) {
        }
    }
}