import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Team {
	final double coefficientReserve = 3; // 3
	double flowAir; // 45/2
	double timeTotal;
	double pressureOfExit;
	double maxDropPressure;
	double timeBeforeSignalOfReturn;
	LocalTime timeOfExit;
	LocalTime timeOfReturn;
	double minPressureOfStart;
	double capacityAirTank;
	Scanner scanner = new Scanner (System.in);
	LocalTime currentTime = LocalTime.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

	public Team(double flowAir, double capacityAirTank) {
		this.flowAir = flowAir;
		this.capacityAirTank = capacityAirTank;
	}


	public void calculateMaxDropPressure() { // Рmax.пад. = Р min. вкл / 3
		System.out.println ("Рmin,вкл: ");
		minPressureOfStart = scanner.nextDouble (); // Р min, вкл
		maxDropPressure = Math.ceil (minPressureOfStart / coefficientReserve);
		System.out.println ("Рmax.пад. = " + maxDropPressure);
	}

	public void calculatePressureOfExit() { // Рк.вых = Рmin.вкл. − Рmax.пад.
		pressureOfExit = minPressureOfStart - maxDropPressure;
		System.out.println ("Рк.вых. = " + pressureOfExit);
	}

	public void calculateTimeBeforeSignalOfReturn() {  // Т = (Р max, пад • Vб) / 45 or 2
		timeBeforeSignalOfReturn = Math.round ((maxDropPressure * capacityAirTank) / flowAir);
		System.out.println ("Тдо подачи команды на возвращение = " + timeBeforeSignalOfReturn);
	}

	public void calculateTimeOfExit() {  // Твых = Твкл + Т
		timeOfExit = currentTime.plusMinutes((long) timeBeforeSignalOfReturn);
		String formattedTime = timeOfExit.format(formatter);
		System.out.println ("Твых = " + formattedTime);
	}

	public void calculateTimeTotal() {  // Т общ = (Р min. вкл • Vб) / 45 or 2
		timeTotal = Math.round ((minPressureOfStart * capacityAirTank) / flowAir);
		System.out.println ("Тобщ = " + timeTotal);
	}

	public void calculateTimeOfReturn() {  // Твозвр = Твкл + Тобщ
		timeOfReturn = currentTime.plusMinutes((long) timeTotal);
		String formattedTime = timeOfReturn.format(formatter);
		System.out.println ("Твозвр = " + formattedTime);
	}
}
