import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LeagueManager leagueManager;

        try {
            leagueManager = new LeagueManager();
        } catch (FileNotFoundException e) {
            System.err.println("无法加载联赛表。请检查文件: " + e.getMessage());
            return;
        }

        while (true) {
            System.out.println("请输入比赛结果 (主队, 客队, 主队得分, 客队得分) 或输入 'exit' 退出:");
            String input = scanner.nextLine();
            if ("exit".equalsIgnoreCase(input)) break;

            // 将中文逗号替换为英文逗号
            input = input.replace('，', ',');

            String[] parts = input.split(",");
            if (parts.length != 4) continue; // 无效输入

            String homeTeamName = parts[0].trim();
            String awayTeamName = parts[1].trim();
            int homeScore = Integer.parseInt(parts[2].trim());
            int awayScore = Integer.parseInt(parts[3].trim());

            leagueManager.updateMatchResult(homeTeamName, awayTeamName, homeScore, awayScore);
        }

        try {
            leagueManager.saveTeams();
        } catch (IOException e) {
            System.err.println("无法保存联赛表。请检查文件: " + e.getMessage());
        }

        leagueManager.printLeagueTable();
    }
}