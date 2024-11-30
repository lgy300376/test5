import java.io.*;
import java.util.*;

public class LeagueManager {
    private static final String FILE_NAME = "league_table.txt";
    private List<Team> teams = new ArrayList<>();

    public LeagueManager() throws FileNotFoundException {
        loadTeams();
    }

    // 加载球队数据
    private void loadTeams() throws FileNotFoundException {
        try (Scanner fileScanner = new Scanner(new File(FILE_NAME))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] parts = line.split(",");
                    if (parts.length == 8) { // 确保有足够的数据字段
                        String name = parts[0].trim();
                        int played = Integer.parseInt(parts[1].trim());
                        int won = Integer.parseInt(parts[2].trim());
                        int drawn = Integer.parseInt(parts[3].trim());
                        int lost = Integer.parseInt(parts[4].trim());
                        int goalsFor = Integer.parseInt(parts[5].trim());
                        int goalsAgainst = Integer.parseInt(parts[6].trim());
                        int points = Integer.parseInt(parts[7].trim());

                        Team team = new Team(name);
                        team.setPlayed(played);
                        team.setWon(won);
                        team.setDrawn(drawn);
                        team.setLost(lost);
                        team.setGoalsFor(goalsFor);
                        team.setGoalsAgainst(goalsAgainst);
                        team.setPoints(points);

                        teams.add(team);
                    }
                }
            }
        }
    }

    // 保存球队数据
    public void saveTeams() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Team team : teams) {
                writer.println(team.getName() + "," + team.getPlayed() + "," + team.getWon() + "," + team.getDrawn() + "," + team.getLost() + "," + team.getGoalsFor() + "," + team.getGoalsAgainst() + "," + team.getPoints());
            }
        }
    }

    // 根据名字查找队伍
    public Team findTeam(String name) {
        for (Team team : teams) {
            if (team.getName().equals(name)) {
                return team;
            }
        }
        return null;
    }

    // 更新比赛结果
    public void updateMatchResult(String homeTeamName, String awayTeamName, int homeScore, int awayScore) {
        Team homeTeam = findTeam(homeTeamName);
        Team awayTeam = findTeam(awayTeamName);
        if (homeTeam != null && awayTeam != null) {
            homeTeam.updateResult(homeScore, awayScore);
            awayTeam.updateResult(awayScore, homeScore);
        }
    }

    // 排序并打印联赛表
    public void printLeagueTable() {
        Collections.sort(teams);
        System.out.println("排名 | 队名          | 比赛场次 |  胜  |  平  |  负  | 进球 | 失球 | 积分");
        int rank = 1;
        for (Team team : teams) {
            System.out.printf("%-4d| %-11s| %2d     | %3d  | %3d  | %2d  | %2d  | %2d  | %2d\n",
                              rank++,
                              team.getName(),
                              team.getPlayed(),
                              team.getWon(),
                              team.getDrawn(),
                              team.getLost(),
                              team.getGoalsFor(),
                              team.getGoalsAgainst(),
                              team.getPoints());
        }
    }
}