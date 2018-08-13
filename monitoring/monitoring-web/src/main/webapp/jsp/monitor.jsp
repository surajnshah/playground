<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <script src="../js/bootstrap.min.js"></script>
</head>

<body>
<div class="container">

    <div class="row">
        <div class="col-md-12">
            <h1>Monitoring</h1>
        </div>
    </div>

    <div id="root"></div>

    <script src="../js/scripts.js"></script>

    <table>
        <tr>
            <td> <label id="monitorLbl">Monitor Data:</label></td>
            <td> <label id="monitor">0</label></td>
        </tr>
    </table>

    <script src="../js/socketscript.js"></script>

</div>
</body>
</html>