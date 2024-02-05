public class Board {
    private char[] slot;

    public Board() {
        this.slot = new char[] {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
    }

    public char[] getSlot() {
        return slot;
    }

    public void setSlot(int index, char key) {
        this.slot[index] = key;
    }

    public void printBoard() {
        System.out.println(slot[6] + " | " + slot[7] + " | " + slot[8]);
        System.out.println("---------");
        System.out.println(slot[3] + " | " + slot[4] + " | " + slot[5]);
        System.out.println("---------");
        System.out.println(slot[0] + " | " + slot[1] + " | " + slot[2]);
    }
    
}
