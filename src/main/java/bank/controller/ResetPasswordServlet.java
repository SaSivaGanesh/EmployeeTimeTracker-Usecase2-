package bank.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bank.dao.ResetPasswordDAO;
import bank.model.PasswordUtil;

@WebServlet("/ResetPasswordServlet")
public class ResetPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String employeeIdStr = (String) session.getAttribute("employeeId");
        int employeeId = Integer.parseInt(employeeIdStr);

        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        ResetPasswordDAO dao = new ResetPasswordDAO();
        String pass = dao.getStoredPassword(employeeId);
        boolean hashedPass = dao.isPasswordHashed(pass);
        
        response.setContentType("text/html");
        response.getWriter().println("<html>");
        response.getWriter().println("<head><title>Password Reset Result</title></head>");
        response.getWriter().println("<body>");
        
        if (hashedPass) {
            String encryptedCurrentPassword = PasswordUtil.encryptPassword(currentPassword);
            if (pass.equals(encryptedCurrentPassword)) {
                if (newPassword.equals(confirmPassword)) {
                    String encryptedNewPassword = PasswordUtil.encryptPassword(newPassword);
                    boolean update = dao.updatePassword(employeeId, encryptedNewPassword);
                    if (update) {
                        response.getWriter().println("<h1>Your password was successfully updated.</h1>");
                    } else {
                        response.getWriter().println("<h1>Your password was not successfully updated.</h1>");
                    }
                } else {
                    response.getWriter().println("<h1>New passwords do not match.</h1>");
                }
            } else {
                response.getWriter().println("<h1>Current password is incorrect.</h1>");
            }
        } else {
            if (pass.equals(currentPassword)) {
                if (newPassword.equals(confirmPassword)) {
                    String encryptedNewPassword = PasswordUtil.encryptPassword(newPassword);
                    boolean update = dao.updatePassword(employeeId, encryptedNewPassword);
                    if (update) {
                        response.getWriter().println("<h1>Your password was successfully updated.</h1>");
                    } else {
                        response.getWriter().println("<h1>Your password was not successfully updated.</h1>");
                    }
                } else {
                    response.getWriter().println("<h1>New passwords do not match.</h1>");
                }
            } else {
                response.getWriter().println("<h1>Current password is incorrect.</h1>");
            }
        }
        
        response.getWriter().println("<br><button onclick=\"window.location.href='EmployeeDashboard.html'\">Back to Dashboard</button>");
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
    }
}
