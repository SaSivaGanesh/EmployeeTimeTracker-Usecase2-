/* package bank.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import bank.dao.WorkStatusDAO;
import bank.model.WorkStatus;

@WebServlet("/ViewWorkStatusServlet1")
public class ViewWorkStatusServlet1 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ViewWorkStatusServlet1() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String projectName = request.getParameter("projectName");

        WorkStatusDAO dao = new WorkStatusDAO();
        List<WorkStatus> workStatusList = dao.getWorkStatusByProject(projectName);

        if (workStatusList == null || workStatusList.isEmpty()) {
            response.getWriter().println("No data found for the project name: " + projectName);
            return;
        }

        DefaultPieDataset dataset = new DefaultPieDataset();
        for (WorkStatus workStatus : workStatusList) {
            dataset.setValue("Employee ID: " + workStatus.getEmployeeId() + " - " + workStatus.getWork(),
                    workStatus.getTotalTime());
        }

        JFreeChart chart = ChartFactory.createPieChart(
                "Work Status for Project: " + projectName,
                dataset,
                true,
                true,
                false);

        final PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionOutlinesVisible(true);
        plot.setNoDataMessage("No data available");

        // Ensure the images directory exists
        String imagesDirPath = getServletContext().getRealPath("/") + "images";
        File imagesDir = new File(imagesDirPath);
        if (!imagesDir.exists()) {
            imagesDir.mkdirs();
        }

        // Generate the chart image file
        String fileName = "chart.png";
        String filePath = imagesDirPath + File.separator + fileName;
        ChartUtils.saveChartAsPNG(new File(filePath), chart, 800, 600);

        // Set the chart URL in the request
        String chartUrl = request.getContextPath() + "/images/" + fileName;
        request.setAttribute("chartUrl", chartUrl);

        // Forward the request to the JSP page
        request.getRequestDispatcher("viewWorkStatus1.jsp").forward(request, response);
    }
} */
