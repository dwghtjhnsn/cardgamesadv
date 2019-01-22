 

public enum Suit {
	CLUBS(0, "Clubs"),
	DIAMONDS(1, "Diamonds"),
	HEARTS(2, "Hearts"),
	SPADES(3, "Spades");
	
	private int id;
	private String name;
	private Suit(int i, String n){
		id = i;
		name = n;
	}

	public boolean gt(Suit s){
		return this.id > s.id;
	}

	public boolean lt(Suit s){
		return this.id < s.id;
	}

	public boolean equals(Suit s){
		return this.id == s.id;
	}
	
	public int compare(Suit s){
		return (this.gt(s)? 1:(this.lt(s)? -1:0));
	}

	public String toString(){
		return name;
	}

	public String toShortString(){
		return name.substring(0, 1);
	}
	public int toInt(){
		return id;
	}
	
	public static Suit fromInt(int i){
		switch (i){
			case 0: return CLUBS;
			case 1: return DIAMONDS;
			case 2: return HEARTS;
			case 3: return SPADES;
			default: return null;
		}
	}
	
	static public Suit random(){
		int r = (int) (Math.random()*4);
		
		return fromInt(r);
	}
	
	static public Suit[] getList(){
		Suit[] list = new Suit[4];
		list[0] = CLUBS;
		list[1] = DIAMONDS;
		list[2] = HEARTS;
		list[3] = SPADES;
		
		return list;
	}
}
