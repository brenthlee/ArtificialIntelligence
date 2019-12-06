# Artificial Intelligence Project
Contributors: **Brent Lee (blee96)**, **Kaitlin Bleich (kbleich)**, **Nathan Trank (ntrank)**, **Calantha Tu (cvtu)**

Professor: *Hisham Assal (hhassal)*

---

## Instructions to Run ##

    1. Download and install Netbeans IDE with Java JDK from https://www.oracle.com/technetwork/java/javase/downloads/jdk-netbeans-jsp-3413139-esa.html

    2. Open downloaded/cloned project folder called ArtificialIntelligence

    3. When the project is opened, click the play button at the top

---

    * Start with Initial Code Base

    * IDE: Netbeans

    * Tools: IDE, Github repo, java8 JDK, Project Board

    * Player can move diagonally with by holding two arrow keys at once

    * Players can make smooth turns
    
    * Created a Mouse class because the regular typical pointer looks ugly

    * Created a Shot class which is a little circle that is drawn when the mouse is clicked

    * Can hold the mouse and there will be a line of shots
    
    * Created a tail for the player (just looks cool)

    * Created a bomb class that explodes when clicking space bar
    
    * Spinner class (just roams freely as an obstacle to avoid)
    
    * Diamond class (comes at you head on)

    * Square class (comes at you head on, when it breaks, comes in a wave frequency)

    * Avoid class (comes at you head on while dodging bullets [AI element])
    
    * start collision detection

---

## Elements of AI ##

    * spawning rate is rule-based

        * if the score is higher than certain numbers (threshold values), then newer and harder enemies will spawn

        * if a certain amount of time has elapsed, then newer and harder enemies will spawn, as well

        * it all depends on which comes first: the time elapsed (from running away the whole time) or the score (from killing every enemy)

    * Enemy Class: Avoid.java

        * for every bullet that is shot and on the screen, it will calculate the distance between the enemy and the bullets

        * if the distance is less than 70pixels, then the range of the enemy's movement will broaden and generate a random number such that it does not continue head on at the bullet and will either go left or right.

        * if there are multiple bullets in the enemy's range of movement, then the enemy will move away from all of them (the bullets' coordinates will be eliminated the the generation of random numbers)
