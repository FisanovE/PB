public class Calculate {



    public double calcTimeTotal(double minPressureOfStart, double stableWorkPressure, double capacityAirTank, double numberTank, double flowAir, double coefficientSqueeze) {  // Т общ = ((Р min, вкл – Р уст. раб) • Vб • Nб) / (40 • К сж)
        return Math.round(((minPressureOfStart - stableWorkPressure) * capacityAirTank * numberTank) / (flowAir * coefficientSqueeze));
    }


    public double calcEasyMaxDropPressure(double minPressureOfStart, double stableWorkPressure, double coefficientEasyReserve) {  // Рmax, пад. = (Рmin. вкл. – Руст.раб) / 2,5
        return Math.ceil((minPressureOfStart - stableWorkPressure) / coefficientEasyReserve);
    }


    public double calcHardMaxDropPressure(double minPressureOfStart, double stableWorkPressure, double coefficientHardReserve) {  // Рmax, пад. = (Рmin. вкл. – Руст.раб) / 3
        return Math.ceil((minPressureOfStart - stableWorkPressure) / coefficientHardReserve);
    }

    public double calcPressureOfExit(double minPressureOfStart, double MaxDropPressure) { // Рк.вых = Рmin.вкл. − Рmax.пад.
        return minPressureOfStart - MaxDropPressure;
    }

    public double calcTimeBeforeSignalOfExit (double MaxDropPressure, double capacityAirTank, double numberTank, double flowAir, double coefficientSqueeze) {  // Т = (Р max, пад • Vб) / (40 • К сж)
        return Math.round((MaxDropPressure * capacityAirTank * numberTank) / (flowAir * coefficientSqueeze));
    }



}
