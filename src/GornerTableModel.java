import javax.swing.*;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class GornerTableModel extends AbstractTableModel {
    private Double[] coefficients;
    private Double from;
    private Double to;
    private Double step;

    public GornerTableModel(Double from, Double to, Double step,
                            Double[] coefficients) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
    }

    public Double getFrom() {
        return from;
    }

    public Double getTo() {
        return to;
    }

    public Double getStep() {
        return step;
    }

    public int getColumnCount() {
// В данной модели два столбца
        return 3;
    }

    public int getRowCount() {
// Вычислить количество точек между началом и концом отрезка
// исходя из шага табулирования
        return new Double(Math.ceil((to - from) / step)).intValue() + 1;
    }

    public Object getValueAt(int row, int col) {
// Вычислить значение X как НАЧАЛО_ОТРЕЗКА + ШАГ*НОМЕР_СТРОКИ
        double x = from + step * row;
        Double result = 0.0;

        for (int i = 0; i < coefficients.length; i++) {
            result = coefficients[i] + x * result;
        }

        switch (col) {
            case 0 -> {
                return x;
            }
            case 1 -> {
// Если запрашивается значение 2-го столбца, то это значение
// многочлена
// Вычисление значения в точке по схеме Горнера.
// Вспомнить 1-ый курс и реализовать
// ...

                return result;
            }
            case 2 -> {
                return (result.intValue() == 0 ? true : false);
            }
        }
        return "";
    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
// Название 1-го столбца
                return "Значение X";
            case 1:
// Название 2-го столбца
                return "Значение многочлена по схеме Горнера";
            case 2:
                return "Малое число?";
        }
        return "";


    }

    public Class<?> getColumnClass(int col) {
// И в 1-ом и во 2-ом столбце находятся значения типа Double
        return (col == 2 ? Boolean.class : Double.class);
    }

    private Boolean isMinNumber(Object obj) {
        var temp = (Double) obj;
        int value = temp.intValue();
        if (value == 0)
            return true;
        return false;
    }
}

