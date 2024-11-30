public class Team implements Comparable<Team> {
    private String name;
    private int played, won, drawn, lost, goalsFor, goalsAgainst, points;

    public Team(String name) {
        this.name = name;
        this.played = 0;
        this.won = 0;
        this.drawn = 0;
        this.lost = 0;
        this.goalsFor = 0;
        this.goalsAgainst = 0;
        this.points = 0;
    }

    // 更新比赛结果
    public void updateResult(int ourScore, int theirScore) {
        played++;
        goalsFor += ourScore;
        goalsAgainst += theirScore;

        if (ourScore > theirScore) {
            won++;
            points += 3;
        } else if (ourScore == theirScore) {
            drawn++;
            points++;
        } else {
            lost++;
        }
    }

    // 用于比较排名
    @Override
    public int compareTo(Team other) {
        if (this.points != other.points) {
            return Integer.compare(other.points, this.points);
        } else if (this.getGoalDifference() != other.getGoalDifference()) {
            return Integer.compare(other.getGoalDifference(), this.getGoalDifference());
        } else {
            return Integer.compare(other.goalsFor, this.goalsFor);
        }
    }

    // 计算净胜球
    public int getGoalDifference() {
        return goalsFor - goalsAgainst;
    }

    // 打印队伍信息
    public void print() {
        System.out.printf("%-15s %2d %2d %2d %2d %2d %2d %2d\n", name, played, won, drawn, lost, goalsFor, goalsAgainst, points);
    }

    // 获取队伍名称
    public String getName() {
        return name;
    }

    // Getter 和 Setter 方法
    public int getPlayed() {
        return played;
    }

    public void setPlayed(int played) {
        this.played = played;
    }

    public int getWon() {
        return won;
    }

    public void setWon(int won) {
        this.won = won;
    }

    public int getDrawn() {
        return drawn;
    }

    public void setDrawn(int drawn) {
        this.drawn = drawn;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(int goalsFor) {
        this.goalsFor = goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}