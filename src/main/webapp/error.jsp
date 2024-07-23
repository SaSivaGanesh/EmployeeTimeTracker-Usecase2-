<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin: 50px;
        }
        .error-message {
            color: #d9534f;
            font-size: 1.5em;
        }
        .error-details {
            margin-top: 20px;
            font-size: 1em;
            color: #5bc0de;
        }
        button {
            padding: 10px 20px;
            font-size: 1em;
            color: #fff;
            background-color: #0275d8;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #025aa5;
        }
    </style>
</head>
<body>
    <h1 class="error-message">Something Went Wrong!</h1>
    <p class="error-details">
        An unexpected error has occurred. Please try again later or contact support if the problem persists.
    </p>
    
</body>
</html>
