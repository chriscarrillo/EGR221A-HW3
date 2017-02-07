import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 2/4/17.
 */
public class AssassinManagerTest {
    private AssassinManager killerHelper() {
        List<String> list = new ArrayList<String>();
        list.add("Chris Carrillo");
        list.add("Dr. Han");
        list.add("Dr. Im");
        list.add("Dr. Jones");
        list.add("Dr. Kolta");
        list.add("Dr. Perkins");
        AssassinManager a = new AssassinManager(list);
        return a;
    }

    @Test
    public void constructorTest() {
        AssassinManager a = killerHelper();
        a.printKillRing();
    }

    @Test
    public void constructorNegativeTest() {
        try {
            List<String> list = new ArrayList<>();
            AssassinManager a = new AssassinManager(list);
            Assert.fail("The list is empty!");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void killRingContainsTest() {
        AssassinManager a = killerHelper();
        Assert.assertTrue(a.killRingContains("ChRis CarRiLlo"));
        Assert.assertTrue(a.killRingContains("Dr. han"));
        Assert.assertTrue(a.killRingContains("dr. Im"));
        Assert.assertTrue(a.killRingContains("dR. jOnES"));
        Assert.assertTrue(a.killRingContains("Dr. KoLTa"));
        Assert.assertTrue(a.killRingContains("DR. PERkins"));
    }

    @Test
    public void killRingContainsNegativeTest() {
        AssassinManager a = killerHelper();
        Assert.assertFalse(a.killRingContains("Chris Fernandes"));
        Assert.assertFalse(a.killRingContains("Chris Turner"));
        Assert.assertFalse(a.killRingContains("Curtis Hohl"));
        Assert.assertFalse(a.killRingContains("Dr. Willett"));
        Assert.assertFalse(a.killRingContains("Dr. Cordero"));
        Assert.assertFalse(a.killRingContains("Dr. Donaldson"));
    }

    @Test
    public void graveyardContainsTest() {
        AssassinManager a = killerHelper();
        a.printKillRing();
        a.kill("Dr. Han");
        a.printGraveyard();
        Assert.assertTrue(a.graveyardContains("Dr. Han"));

        System.out.println();
        a.printKillRing();
        a.kill("Dr. Kolta");
        a.printGraveyard();
        Assert.assertTrue(a.graveyardContains("Dr. Kolta"));
    }

    @Test
    public void graveyardContainsNegativeTest() {
        AssassinManager a = killerHelper();
        a.kill("Dr. Han");
        a.kill("Dr. Perkins");
        a.graveyardContains("Dr. Jones");
        a.graveyardContains("Dr. Im");
    }

    @Test
    public void isGameOverTest() {
        List<String> list = new ArrayList<>();
        list.add("Chris Carrillo");

        AssassinManager a = new AssassinManager(list);
        Assert.assertTrue(a.isGameOver());
    }

    @Test
    public void isGameOverNegativeTest() {
        List<String> list = new ArrayList<>();
        list.add("Chris Carrillo");
        list.add("Kris Hopper");

        AssassinManager a = new AssassinManager(list);
        Assert.assertFalse(a.isGameOver());
    }

    @Test
    public void winnerTest() {
        List<String> list = new ArrayList<>();
        list.add("Chris Carrillo");

        AssassinManager a = new AssassinManager(list);
        Assert.assertTrue(a.winner().equals("Chris Carrillo"));
    }

    @Test
    public void winnerNegativeTest() {
        List<String> list = new ArrayList<>();
        list.add("Chris Carrillo");

        AssassinManager a = new AssassinManager(list);
        Assert.assertFalse(a.winner().equals("Dr. Han"));
    }

    @Test
    public void killTest() {
        AssassinManager a = killerHelper();
        a.kill("Chris Carrillo");
        a.kill("Dr. Han");
        a.kill("Dr. Im");
        a.kill("Dr. Jones");

        Assert.assertFalse(a.killRingContains("Chris Carrillo"));
        Assert.assertFalse(a.killRingContains("Dr. Han"));
        Assert.assertFalse(a.killRingContains("Dr. Jones"));
    }

    @Test
    public void killNegativeTest() {
        try {
            List<String> list = new ArrayList<>();
            list.add("Dr. Han");
            AssassinManager a = new AssassinManager(list);
            a.kill("Dr. Han");
            Assert.fail("IllegalStateException thrown. The game is over.");
        } catch (IllegalStateException e) {
        }
    }

    @Test
    public void killNegativeTest2() {
        try {
            AssassinManager a = killerHelper();
            a.kill("Chris Fernandes");
            Assert.fail("IllegalArugmentException thrown. That name is not in the list.");
        } catch (IllegalArgumentException e) {
        }
    }
}