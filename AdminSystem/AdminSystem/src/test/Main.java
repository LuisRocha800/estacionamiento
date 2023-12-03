package test;

import DaosApp.dao.app.ConexionEstatica;
import DaosApp.dao.app.dto.pagos;
import DaosApp.dao.dao.derby.pagosDaoDerbyImp;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main extends JFrame {

    private static Connection conexion;

    public Main(String title, double sumaAmount) {
        super(title);

        CategoryDataset dataset = createDataset(sumaAmount);
        JFreeChart chart = ChartFactory.createBarChart(
                "Gráfico de la Suma de Amount",
                "Suma de Amount",
                "Valor",
                dataset
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(560, 370));
        setContentPane(chartPanel);
    }

    private CategoryDataset createDataset(double sumaAmount) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        double normalizedSumaAmount = (sumaAmount / 10000) * 100;

        dataset.addValue(sumaAmount, "Suma Amount", "");

        return dataset;
    }

     public static void main(String[] args) {
        String url = ConexionEstatica.CONEXION_CREDENTIALS;

        try {
            conexion = DriverManager.getConnection(url);

            pagosDaoDerbyImp pag = new pagosDaoDerbyImp();
            pag.setConexion(conexion);

            List<pagos> resultadoConsulta = pag.consultaEspecial(11, 2023);

            System.out.println(resultadoConsulta);

            double sumaOrigin = 0;

            for (pagos pago : resultadoConsulta) {
                sumaOrigin += pago.getAmount();
            }

            System.out.println("GANANCIAS GENERADAS EN ESE PERIODO: " + sumaOrigin);

            final double sumaFinal = sumaOrigin;  // Declarar la variable como final

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    Main example = new Main("Gráfico de Suma Amount", sumaFinal);
                    example.setSize(600, 400);
                    example.setLocationRelativeTo(null);
                    example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    example.setVisible(true);
                }
            });

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
