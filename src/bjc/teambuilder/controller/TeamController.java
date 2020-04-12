package bjc.teambuilder.controller;

import bjc.utils.data.IHolder;
import bjc.utils.data.Identity;
import bjc.utils.funcdata.FunctionalList;
import bjc.utils.funcdata.IList;
import bjc.utils.funcutils.ListUtils;

import bjc.teambuilder.model.Team;

public class TeamController {
	private IList<Team> teams;

	/**
	 * Add a partial team
	 * 
	 * @param teamName
	 *            The name of the partial team
	 * @param teamMembers
	 *            The members of the partial team
	 */
	public void addPartialTeam(String teamName, String... teamMembers) {
		teams.add(new Team(teamName, new FunctionalList<>(teamMembers)));
	}

	/**
	 * Build full teams from the partial teams
	 * 
	 * @param teamSize
	 *            The size of the full teams to build
	 * @return A list of teams of the specified size, or null if the
	 *         request couldn't be satisfied.
	 * @throws IllegalArgumentException
	 *             if full teams couldn't be built
	 */
	public IList<Team> buildFullTeams(int teamSize) {
		IList<IList<Team>> partitionedTeams = ListUtils
				.groupPartition(teams, Team::getMemberCount, teamSize);

		IHolder<Integer> teamCounter = new Identity<>(0);

		return partitionedTeams.map((partTeam) -> {
			IList<String> combTeam = new FunctionalList<>();

			partTeam.forEach((team) -> combTeam.addAll(team.getMembers()));

			String teamName = "Team " + teamCounter.getValue();
			teamCounter.transform((count) -> count + 1);

			return new Team(teamName, combTeam);
		});
	}
}
