package bank.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bank.dao.WorkStatusDAO1;
import bank.model.WorkStatus;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

@WebServlet("/ViewWorkStatusServlet")
public class ViewWorkStatusServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String projectName = request.getParameter("projectName");
        String period = request.getParameter("period");

        WorkStatusDAO1 dao = new WorkStatusDAO1();
        List<WorkStatus> workStatusList = dao.getWorkStatusByProject(projectName);

        if (workStatusList == null || workStatusList.isEmpty()) {
            response.getWriter().println("No data found for the project name: " + projectName);
            return;
        }

        if ("today".equals(period)) {
            generatePieChart(response, workStatusList);
        } else if ("weekly".equals(period)) {
            generateWeeklyBarCharts(response, workStatusList);
        } else if ("monthly".equals(period)) {
            generateMonthlyBarCharts(response, workStatusList);
        }
    }

    private void generatePieChart(HttpServletResponse response, List<WorkStatus> workStatusList) throws IOException {
        DefaultPieDataset dataset = new DefaultPieDataset();
        Date today = new Date(System.currentTimeMillis());

        for (WorkStatus workStatus : workStatusList) {
            if (isSameDay(workStatus.getStartTime(), today)) {
                String label = String.format("%s: %s (%s)", 
                        workStatus.getEmployeeName(), 
                        workStatus.getWork(), 
                        workStatus.getTotalTime());
                dataset.setValue(label, parseTotalTime(workStatus.getTotalTime()));
            }
        }

        JFreeChart chart = ChartFactory.createPieChart(
                "Work Status for Today",
                dataset,
                true,
                true,
                false);

        final PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionOutlinesVisible(true);
        plot.setNoDataMessage("No data available");

        response.setContentType("image/png");
        ChartUtils.writeChartAsPNG(response.getOutputStream(), chart, 800, 600);
    }

    private void generateWeeklyBarCharts(HttpServletResponse response, List<WorkStatus> workStatusList) throws IOException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Calendar today = Calendar.getInstance();
        Calendar monthStart = (Calendar) today.clone();
        monthStart.set(Calendar.DAY_OF_MONTH, 1);

        Calendar monthEnd = (Calendar) today.clone();
        monthEnd.set(Calendar.DAY_OF_MONTH, monthEnd.getActualMaximum(Calendar.DAY_OF_MONTH));

        addWeeklyDataToDataset(dataset, workStatusList, monthStart, monthEnd, "Current Month");

        JFreeChart chart = ChartFactory.createBarChart(
                "Weekly Work Status",
                "Week",
                "Total Time",
                dataset);

        response.setContentType("image/png");
        ChartUtils.writeChartAsPNG(response.getOutputStream(), chart, 800, 600);
    }

    private void generateMonthlyBarCharts(HttpServletResponse response, List<WorkStatus> workStatusList) throws IOException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Calendar today = Calendar.getInstance();
        Calendar monthStart = (Calendar) today.clone();
        monthStart.set(Calendar.DAY_OF_MONTH, 1);

        Calendar lastMonth = (Calendar) today.clone();
        lastMonth.add(Calendar.MONTH, -1);
        Calendar lastMonthStart = (Calendar) lastMonth.clone();
        lastMonthStart.set(Calendar.DAY_OF_MONTH, 1);

        addMonthlyDataToDataset(dataset, workStatusList, monthStart, today, "Current Month");
        addMonthlyDataToDataset(dataset, workStatusList, lastMonthStart, getEndOfMonth(lastMonth), "Last Month");

        JFreeChart chart = ChartFactory.createBarChart(
                "Monthly Work Status",
                "Month",
                "Total Time",
                dataset);

        response.setContentType("image/png");
        ChartUtils.writeChartAsPNG(response.getOutputStream(), chart, 800, 600);
    }

    private void addWeeklyDataToDataset(DefaultCategoryDataset dataset, List<WorkStatus> workStatusList, Calendar start, Calendar end, String periodLabel) {
        Calendar weekStart = (Calendar) start.clone();

        while (weekStart.before(end)) {
            Calendar weekEnd = (Calendar) weekStart.clone();
            weekEnd.add(Calendar.DAY_OF_WEEK, 7);
            if (weekEnd.after(end)) {
                weekEnd = (Calendar) end.clone();
            }

            int totalMinutes = 0;
            StringBuilder workDescription = new StringBuilder();

            for (WorkStatus workStatus : workStatusList) {
                Calendar workDate = Calendar.getInstance();
                workDate.setTime(workStatus.getStartTime());

                if (workDate.after(weekStart) && workDate.before(weekEnd)) {
                    totalMinutes += parseTotalTime(workStatus.getTotalTime());
                    workDescription.append(String.format("%s (%s); ", workStatus.getWork(), workStatus.getTotalTime()));
                }
            }

            dataset.addValue(totalMinutes, periodLabel, "Week " + getWeekOfMonth(weekStart) + ": " + workDescription.toString());
            weekStart.add(Calendar.DAY_OF_MONTH, 7);  // Move to the next week
        }
    }

    private void addMonthlyDataToDataset(DefaultCategoryDataset dataset, List<WorkStatus> workStatusList, Calendar start, Calendar end, String periodLabel) {
        Calendar monthStart = (Calendar) start.clone();

        while (monthStart.before(end)) {
            Calendar monthEnd = (Calendar) monthStart.clone();
            monthEnd.set(Calendar.DAY_OF_MONTH, monthEnd.getActualMaximum(Calendar.DAY_OF_MONTH));

            if (monthEnd.after(end)) {
                monthEnd = (Calendar) end.clone();
            }

            int totalMinutes = 0;
            StringBuilder workDescription = new StringBuilder();

            for (WorkStatus workStatus : workStatusList) {
                Calendar workDate = Calendar.getInstance();
                workDate.setTime(workStatus.getStartTime());

                if (workDate.after(monthStart) && workDate.before(monthEnd)) {
                    totalMinutes += parseTotalTime(workStatus.getTotalTime());
                    workDescription.append(String.format("%s (%s); ", workStatus.getWork(), workStatus.getTotalTime()));
                }
            }

            dataset.addValue(totalMinutes, periodLabel, getMonthOfYear(monthStart) + ": " + workDescription.toString());
            monthStart.add(Calendar.MONTH, 1);
            monthStart.set(Calendar.DAY_OF_MONTH, 1);
        }
    }

    private boolean isSameDay(java.sql.Timestamp date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
               cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    private int parseTotalTime(String totalTime) {
        // Convert time format (e.g., "1H 40M") to minutes for charting
        String[] parts = totalTime.split(" ");
        int minutes = 0;
        for (String part : parts) {
            if (part.endsWith("H")) {
                minutes += Integer.parseInt(part.replace("H", "")) * 60;
            } else if (part.endsWith("M")) {
                minutes += Integer.parseInt(part.replace("M", ""));
            }
        }
        return minutes;
    }

    private int getWeekOfMonth(Calendar date) {
        return date.get(Calendar.WEEK_OF_MONTH);
    }

    private String getMonthOfYear(Calendar date) {
        return new SimpleDateFormat("MMMM yyyy").format(date.getTime());
    }

    private Calendar getEndOfMonth(Calendar calendar) {
        Calendar endOfMonth = (Calendar) calendar.clone();
        endOfMonth.set(Calendar.DAY_OF_MONTH, endOfMonth.getActualMaximum(Calendar.DAY_OF_MONTH));
        return endOfMonth;
    }
}
