import java.util.Scanner;

public class Calculate {
	final double flowAir = 40; // 40
	final double coefficientEasyReserve = 2.5; // 2.5
	final double coefficientHardReserve = 3; // 3
	final double stableWorkPressure = 10; // 10
	final double coefficientSqueeze = 1.1; // К сж
	double PressureOfExit;
	double MaxDropPressure;
	double TimeBeforeSignalOfExit;
	double minPressureOfStart;
	double numberTank;
	boolean buttonHardMode;
	double capacityAirTank;
	Scanner scanner = new Scanner (System.in);


	public double calcTimeTotal () {  // Т общ = ((Р min, вкл – Р уст. раб) • Vб • Nб) / (40 • К сж)


		System.out.println ("Рmin,вкл: ");
		minPressureOfStart = scanner.nextDouble (); // Р min, вкл
		System.out.println ("Nб (1/2): ");
		numberTank = scanner.nextDouble ();
		System.out.print ("Vб (6,8/7): ");
		capacityAirTank = scanner.nextDouble (); // Vб
		long TimeTotal = Math.round (((minPressureOfStart - stableWorkPressure) * capacityAirTank * numberTank) / (flowAir * coefficientSqueeze));
		System.out.println ("Тобщ = " + TimeTotal);
		return TimeTotal;
	}


	public double MaxDropPressure () {
		System.out.print ("Условия сложные? (true/false): ");
		buttonHardMode = scanner.nextBoolean ();
		if (!buttonHardMode) {
			MaxDropPressure = Math.ceil ((minPressureOfStart - stableWorkPressure) / coefficientEasyReserve);
			System.out.println ("Рmax.пад. = " + MaxDropPressure);
			return MaxDropPressure; // Рmax, пад. = (Рmin. вкл. – Руст.раб) / 2,5
		} else {
			MaxDropPressure = Math.ceil ((minPressureOfStart - stableWorkPressure) / coefficientHardReserve);
			System.out.println ("Рmax.пад. = " + MaxDropPressure);
			return MaxDropPressure; // Рmax, пад. = (Рmin. вкл. – Руст.раб) / 3
		}
	}

	public double calcPressureOfExit () { // Рк.вых = Рmin.вкл. − Рmax.пад.
		PressureOfExit = minPressureOfStart - MaxDropPressure;
		System.out.println ("Рк.вых. = " + PressureOfExit);
		return PressureOfExit;
	}

	public double calcTimeBeforeSignalOfExit () {  // Т = (Р max, пад • Vб) / (40 • К сж)
		TimeBeforeSignalOfExit = Math.round ((MaxDropPressure * capacityAirTank * numberTank) / (flowAir * coefficientSqueeze));
		System.out.println ("Тдо подачи сигнала = " + TimeBeforeSignalOfExit);
		return TimeBeforeSignalOfExit;
	}
}
