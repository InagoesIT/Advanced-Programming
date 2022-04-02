<html>
<head>
  <title>${catalog.getName()}</title>
</head>
<body>
  <h1>The items from the catalog: ${catalog.getName()}</h1>

  <ul>
    <#list items as item>
      <li>id = ${item.getId()}, title = ${item.getTitle()}, location = <a href='${item.getLocation()}'>${item.getLocation()}</a></li>
    </#list>
  </ul>

</body>
</html>
