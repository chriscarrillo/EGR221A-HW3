import java.util.List;

/**
 * @author Chris Carrillo
 * AssassinManager for EGR221A
 * A game that creates AssassinNodes of the killRing and graveyard
 * Contains various methods to accomplish tasks in the game such as kill()
 */
public class AssassinManager {
    private AssassinNode frontKillRing; // reference to the front node of the kill ring
    private AssassinNode frontGraveyard; // reference to the front node of the graveyard

    /**
     * Builds AssassinManager. Fills kill ring from names list.
     * The killer is set to null for each one.
     * @param names is the list of people playing the game
     * @throws IllegalArgumentException if names is null or names is empty
     */
    public AssassinManager(List<String> names) throws IllegalArgumentException {
        if (names == null || names.isEmpty()) {
            throw new IllegalArgumentException("No players exist.");
        }

        frontKillRing = new AssassinNode(names.get(0));
        AssassinNode current = frontKillRing;

        for (int i = 1; i < names.size(); i++) {
            current.next = new AssassinNode(names.get(i), null);
            current = current.next;
        }
    }

    /**
     * Prints who's stalking who from the kill ring.
     * If the kill ring's next is null, that means the game is over,
     * so it will print who won the game.
     */
    public void printKillRing() {
        if (this.frontKillRing.next == null) {
            System.out.println(frontKillRing.name + " won the game!");
        } else {
            AssassinNode current = frontKillRing;
            while (current.next != null) {
                System.out.println("    " + current.name + " is stalking " + current.next.name);
                current = current.next;
            }
            System.out.println("    " + current.name + " is stalking " + frontKillRing.name);
        }
    }

    /**
     * Prints who was killed by who from the graveyard by looping through the
     * graveyard and retrieving the name and its killer.
     * If the graveyard is null, then it will return;
     */
    public void printGraveyard() {
        if (frontGraveyard == null)
            return;

        AssassinNode current = frontGraveyard;
        while (current != null) {
            System.out.println("    " + current.name + " was killed by " + current.killer);
            current = current.next;
        }
    }

    /**
     * Helper method that will check the ring given from current and name
     * @param current is the AssassinNode it is checking (frontKillRing or frontGraveyard)
     * @param name is the name it is checking equality with
     * @return true if the name given equals the current's name
     * @return false if they are not equal
     */
    private boolean checkRing(AssassinNode current, String name) {
        while (current != null) {
            if (current.name.equalsIgnoreCase(name)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Checks if the killRingContains the given name
     * @param name is the name that it checks if the killRing contains
     * @return true or false based on the checkRing helper method
     */
    public boolean killRingContains(String name) {
        return checkRing(frontKillRing, name);
    }

    /**
     * Checks if the graveyardContains the given name
     * @param name is the name that it checks if the graveyard contains
     * @return true or false based on the checkRing helper method
     */
    public boolean graveyardContains(String name) {
        return checkRing(frontGraveyard, name);
    }

    /**
     * Checks if the game is over by checking if there is more than 1 element in the killRing
     * @return true if there is no more than 1 element in the killRing
     * @return false if there is an element after the first element in the killRing
     */
    public boolean isGameOver() {
        return (frontKillRing.next == null);
    }

    /**
     * Returns the winner of the game
     * @return the winner as a String if isGameOver returns true
     * @return null if isGameOver returns false
     */
    public String winner() {
        return isGameOver() ? frontKillRing.name : null;
    }

    /**
     * Kills the person from the list of the given name
     * @param name is the person that is going to be killed
     * @throws IllegalStateException if the game is already over (this one takes precedence)
     * @throws IllegalArgumentException if the name given is not in the killRing
     */
    public void kill(String name) throws IllegalStateException, IllegalArgumentException {
        if (isGameOver()) {
            throw new IllegalStateException("The game is over.");
        } else if (!killRingContains(name)) {
            throw new IllegalArgumentException("That name is not in the kill ring.");
        }

        AssassinNode victim = frontKillRing; // the victim is initially the frontKillRing
        AssassinNode culprit = victim; // the culprit is initially the victim

        // checks if the victim name matches the name given
        if (victim.name.equalsIgnoreCase(name)) {
            // while the culprit's next is not null, set the culprit to culprit's next
            while (culprit.next != null) {
                culprit = culprit.next;
            }
            // sets the frontKillRing to the next in the kill ring
            frontKillRing = frontKillRing.next;
        } else {
            victim = victim.next; // sets the victim to the victim's next
            while (!victim.name.equalsIgnoreCase(name)) { // while the victim's name is not equal to the name given
                culprit = culprit.next; // advance the culprit
                victim = victim.next; // advance the victim
            }
            culprit.next = victim.next; // set the culprit's next to the victim's next
        }
        victim.killer = culprit.name; // set the victim's killer to the culprit's name
        victim.next = frontGraveyard; // set the next victim to the frontGraveyard
        frontGraveyard = victim; // set the frontGraveyard to the victim
    }

    //////// DO NOT MODIFY AssassinNode.  You will lose points if you do. ////////
    /**
     * Each AssassinNode object represents a single node in a linked list
     * for a game of Assassin.
     */
    private static class AssassinNode {
        public final String name;  // this person's name
        public String killer;      // name of who killed this person (null if alive)
        public AssassinNode next;  // next node in the list (null if none)
        
        /**
         * Constructs a new node to store the given name and no next node.
         */
        public AssassinNode(String name) {
            this(name, null);
        }

        /**
         * Constructs a new node to store the given name and a reference
         * to the given next node.
         */
        public AssassinNode(String name, AssassinNode next) {
            this.name = name;
            this.killer = null;
            this.next = next;
        }
    }
}
