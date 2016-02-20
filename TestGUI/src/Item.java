
public class Item {

	String name;
	int turns, rounds;
	 Item(String a, int b, int c){
		 name = a;
		 turns = b;
		 rounds = c;
	
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTurns() {
		return turns;
	}
	public void setTurns(int turns) {
		this.turns = turns;
	}
	public int getRounds() {
		return rounds;
	}
	public void setRounds(int rounds) {
		this.rounds = rounds;
	}

	@Override
	public String toString() {
		return "Name = " + name + ", turns = " + turns + ", rounds = " + rounds;
	}
	 
	
	 
}
