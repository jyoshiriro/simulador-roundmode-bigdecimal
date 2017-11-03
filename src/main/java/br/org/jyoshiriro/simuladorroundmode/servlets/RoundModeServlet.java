package br.org.jyoshiriro.simuladorroundmode.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet(value="/round/*")
public class RoundModeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");

        String uri = request.getRequestURI();
        String[] params = uri.substring(uri.indexOf("/")+1).split("/");
        String number = params[1];
        int scale = Integer.parseInt(params[2]);
        String round = params.length == 4 ? params[3].toUpperCase() : null;

        this.setRoundsValues(request, number, scale, round);
        requestDispatcher.forward(request, response);
    }

    private void setRoundsValues(HttpServletRequest request, String number, int scale, String round) {
        for (Map.Entry<String, String> entry : this.getRoundsMap(number, scale, round).entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }
    }

    private Map<String, String> getRoundsMap(String number, int scale, String round) {
        Map<String, String> map = new LinkedHashMap<>();

        if (round == null) {
            for (RoundingMode roundingMode : RoundingMode.values()) {
                if (roundingMode == RoundingMode.UNNECESSARY) {
                    map.put(roundingMode.name().toLowerCase(), "throw ArithmeticException");
                } else {
                    map.put(roundingMode.name().toLowerCase(), new BigDecimal(number).setScale(scale, roundingMode).toString());
                }
            }
        } else {
            RoundingMode roundingMode = RoundingMode.valueOf(round);
            map.put(roundingMode.name().toLowerCase(), new BigDecimal(number).setScale(scale, roundingMode).toString());
        }
        return map;
    }
}
