 

public enum Rank {
	ACE(1, "Ace"),
	TWO(2, "2"),
	THREE(3, "3"),
	FOUR(4, "4"),
	FIVE(5, "5"),
	SIX(6, "6"),
	SEVEN(7, "7"),
	EIGHT(8, "8"),
	NINE(9, "9"),
	TEN(10, "10"),
	JACK(11, "Jack"),
	QUEEN(12, "Queen"),
	KING(13, "King");
	
	private int id;
	private String name;
	private Rank(int i, String n){
		id = i;
		name = n;
	}

	public boolean gt(Rank s, boolean aceHigh){
		int id1 = this.id;
		int id2 = s.id;
		
		if (aceHigh){
			if (id1 == 1) id1 = 14;
			if (id2 == 1) id2 = 14;
		}
		
		return id1 > id2;
	}

	public boolean lt(Rank s, boolean aceHigh){
		int id1 = this.id;
		int id2 = s.id;
		
		if (aceHigh){
			if (id1 == 1) id1 = 14;
			if (id2 == 1) id2 = 14;
		}
		
		return id1 < id2;
	}

	public boolean equals(Rank s){
		return this.id == s.id;
	}
	
	public int compare(Rank s){
		return (this.gt(s, true)? 1:(this.lt(s, true)? -1:0));
	}

	public int compare(Rank s, boolean aceHigh){
		return (this.gt(s, aceHigh)? 1:(this.lt(s, aceHigh)? -1:0));
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
	
	public static Rank fromInt(int i){
		switch (i){
			case 1: return ACE;
			case 2: return TWO;
			case 3: return THREE;
			case 4: return FOUR;
			case 5: return FIVE;
			case 6: return SIX;
			case 7: return SEVEN;
			case 8: return EIGHT;
			case 9: return NINE;
			case 10: return TEN;
			case 11: return JACK;
			case 12: return QUEEN;
			case 13: return KING;
			default: return null;
		}
	}
	
	static public Rank random(){
		int r = (int) (Math.random()*KING.id)+1;
		
		return fromInt(r);
	}
	
	
	static public Rank[] getList(){
		Rank[] list = new Rank[13];
		list[0] = ACE;
		list[1] = TWO;
		list[2] = THREE;
		list[3] = FOUR;
		list[4] = FIVE;
		list[5] = SIX;
		list[6] = SEVEN;
		list[7] = EIGHT;
		list[8] = NINE;
		list[9] = TEN;
		list[10] = JACK;
		list[11] = QUEEN;
		list[12] = KING;
		
		return list;
	}
}
