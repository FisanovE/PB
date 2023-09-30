public class Main {
    public static void main(String[] args) {
        Team team = new Team(45, 7);
        team.calculateMaxDropPressure();
        team.calculatePressureOfExit();
        team.calculateTimeBeforeSignalOfReturn();
        team.calculateTimeOfExit();
        team.calculateTimeTotal();
        team.calculateTimeOfReturn();
    }
}
