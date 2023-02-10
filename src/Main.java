import java.util.Scanner;

public class Main {



    public static void main(String[] args) {
        final double flowAir = 40; // 40
        final double coefficientEasyReserve = 2.5; // 2.5
        final double coefficientHardReserve = 3; // 3
        final double stableWorkPressure = 10; // 10
        final double coefficientSqueeze = 1.1; // К сж
        Scanner scanner = new Scanner(System.in);
        System.out.print("Рmin,вкл: ");
        double minPressureOfStart = scanner.nextDouble (); // Р min, вкл
        System.out.print("Nб (1/2): ");
        double numberTank = scanner.nextDouble ();
        System.out.print("Условия сложные? (true/false): ");
        boolean buttonHardMode = scanner.nextBoolean();
        System.out.print("Vб (6,8/7): ");
        double capacityAirTank = scanner.nextDouble(); // Vб

        Calculate calc = new Calculate();


        System.out.println("Тобщ = " + Math.round(calc.calcTimeTotal (minPressureOfStart, stableWorkPressure, capacityAirTank, numberTank, flowAir, coefficientSqueeze)));

        double MaxDropPressure;
        if (buttonHardMode) {
            MaxDropPressure = calc.calcHardMaxDropPressure(minPressureOfStart, stableWorkPressure, coefficientHardReserve);
        } else {
            MaxDropPressure = calc.calcEasyMaxDropPressure(minPressureOfStart, stableWorkPressure, coefficientEasyReserve);
        }
        System.out.println("Рmax.пад. = " + MaxDropPressure);
        System.out.println("Рк.вых. = " + calc.calcPressureOfExit(minPressureOfStart, MaxDropPressure));
        System.out.println("Тдо подачи сигнала = " + calc.calcTimeBeforeSignalOfExit (MaxDropPressure, capacityAirTank, numberTank, flowAir, coefficientSqueeze));
    }
}
