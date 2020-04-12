package bjc.teambuilder.model;

import bjc.utils.funcdata.IList;

public class Team {
	private String			name;
	private IList<String>	members;

	public Team(String name, IList<String> members) {
		this.name = name;
		this.members = members;
	}

	public int getMemberCount() {
		return members.getSize();
	}

	public IList<String> getMembers() {
		return members;
	}

	public String getName() {
		return name;
	}
}
