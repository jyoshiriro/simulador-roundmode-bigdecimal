<%@ page isELIgnored="false" contentType="application/json"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
{
    "results": {
        <c:if test="${up!=null}">"up": "${up}", </c:if><c:if test="${down!=null}">
        "down": "${down}", </c:if><c:if test="${ceiling!=null}">
        "ceiling": "${ceiling}", </c:if><c:if test="${floor!=null}">
        "floor": "${floor}", </c:if><c:if test="${half_up!=null}">
        "half_up": "${half_up}", </c:if><c:if test="${half_down!=null}">
        "half_down": "${half_down}", </c:if><c:if test="${half_even!=null}">
        "half_even": "${half_even}", </c:if><c:if test="${unnecessary!=null}">
        "unnecessary": "${unnecessary}"</c:if>
    }
}