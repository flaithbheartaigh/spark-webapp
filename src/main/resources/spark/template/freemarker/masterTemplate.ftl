<#macro masterTemplate title="welcome">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
    <script language="JavaScript" src="/scripts/site.js"></script>
    <#nested/>
</body>
</html>
</#macro>