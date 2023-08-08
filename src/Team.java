import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Team {
	final double flowAir = 40; // 40
	final double coefficientEasyReserve = 2.5; // 2.5
	final double coefficientHardReserve = 3; // 3
	final double stableWorkPressure = 10; // 10
	final double coefficientSqueeze = 1.1; // К сж
	double timeTotal;
	double pressureOfExit;
	double maxDropPressure;
	double timeBeforeSignalOfExit;
	LocalTime timeOfExit;
	double minPressureOfStart;
	double numberTank;
	boolean isHardModeFlag;
	double capacityAirTank;
	Scanner scanner = new Scanner (System.in);

	LocalTime currentTime = LocalTime.now();
	DateTimeFormatter formatter;
	String formattedTime;




	public void calculateTimeTotal() {  // Т общ = ((Р min, вкл – Р уст. раб) • Vб • Nб) / (40 • К сж)
		System.out.println ("Рmin,вкл: ");
		minPressureOfStart = scanner.nextDouble (); // Р min, вкл
		System.out.println ("Nб (1/2): ");
		numberTank = scanner.nextDouble ();
		System.out.print ("Vб (6,8/7): ");
		capacityAirTank = scanner.nextDouble (); // Vб
		timeTotal = Math.round (((minPressureOfStart - stableWorkPressure) * capacityAirTank * numberTank) / (flowAir * coefficientSqueeze));
		System.out.println ("Тобщ = " + timeTotal);
	}


	public void calculateMaxDropPressure() {
		System.out.print ("Условия сложные? (true/false): ");
		isHardModeFlag = scanner.nextBoolean ();
		if (!isHardModeFlag) {
			maxDropPressure = Math.ceil ((minPressureOfStart - stableWorkPressure) / coefficientEasyReserve);
			System.out.println ("Рmax.пад. = " + maxDropPressure);
		} else {
			maxDropPressure = Math.ceil ((minPressureOfStart - stableWorkPressure) / coefficientHardReserve);
			System.out.println ("Рmax.пад. = " + maxDropPressure);
		}
	}

	public void calculatePressureOfExit() { // Рк.вых = Рmin.вкл. − Рmax.пад.
		pressureOfExit = minPressureOfStart - maxDropPressure;
		System.out.println ("Рк.вых. = " + pressureOfExit);
	}

	public void calculateTimeBeforeSignalOfExit() {  // Т = (Р max, пад • Vб) / (40 • К сж)
		timeBeforeSignalOfExit = Math.round ((maxDropPressure * capacityAirTank * numberTank) / (flowAir * coefficientSqueeze));
		System.out.println ("Тдо подачи сигнала = " + timeBeforeSignalOfExit);
	}

	public void calculateTimeOfExit() {  // Твозвр = Твкл + Тобщ
		timeOfExit = currentTime.plusMinutes((long) timeTotal);
		formatter = DateTimeFormatter.ofPattern("HH:mm");
		formattedTime = currentTime.format(formatter);
		System.out.println ("Твозвр = " + formattedTime);
	}
}
